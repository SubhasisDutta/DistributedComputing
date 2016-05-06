/**
* @author Subhasis Dutta, Ram Hariesh,Vibin Daniel
* @email-id sxd150830@utdallas.edu,rxc142330@utdallas.edu,vxd141730@utdallas.edu,
* @version 1.0
* 
* Program to simulate Floodmax algorithm for leader election in Asynchronous general networks
*/

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


public class Main {

	//public static final String INPUT_FILE="input.dat";
	public static String INPUT_FILE;	

	private int noOfNodes;
	private ProcessNode[] processNodes;
	private String[] stringUIDs;
	private String[][] adjacencyMatrix;
	
	public static void main(String[] args){
		if(args.length < 1 ){
			INPUT_FILE ="C:\\Workspace\\Github\\DistributedComputing\\Project 2\\floodmax\\src\\connectivity.txt";			
		} else {
			INPUT_FILE = args[0];			
		}
		Main main=new Main();
		//load data from file and start N process threads
		main.loadData();
		main.initilize();
		main.start();
		
		main.process();
		
	}
	
	public void loadData(){
		FileReader fileReader=null;
		BufferedReader bufferedReader=null;
		try{
			File file=new File(INPUT_FILE);
			fileReader=new FileReader(file);
			bufferedReader=new BufferedReader(fileReader);
			//Read First Line
			noOfNodes=Integer.parseInt(bufferedReader.readLine());
			//Read Second Line
			stringUIDs=bufferedReader.readLine().trim().split(" ");
			adjacencyMatrix = new String[noOfNodes][noOfNodes];
			
			for(int i=0;i<noOfNodes;i++){				
				adjacencyMatrix[i] = bufferedReader.readLine().trim().split(" ");				
			}
			bufferedReader.close();
			
			for(int i=0;i<noOfNodes;i++){				
				for(int j=0;j<adjacencyMatrix[i].length;j++){
					
				}					
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public void initilize(){
		System.out.println("Execution Started ...");
		processNodes = new ProcessNode[noOfNodes];
		System.out.println("Initializing Process Graph...");
		SharedData.status=new boolean[noOfNodes];
		//Create blank nodes
		for(int i=0;i<noOfNodes;i++){
			int nUID=Integer.parseInt(stringUIDs[i]);
			NodeData nodeData=new NodeData(nUID,i);
			processNodes[i]=new ProcessNode(nodeData);
		}
		//Initialize the neighbors
		for(int i=0;i< noOfNodes;i++){	
			//set the neighbors
			NodeData n=processNodes[i].getNodeData();
			n.setConectedNeighbours(getNeighbours(adjacencyMatrix[i]));					
			//set the initial send message
			setInitialSendMessages(n);						
		}
		System.out.println("Data Initializing");
	}
	
	private List<Integer> getNeighbours(String[] strArr){
		List<Integer> neighbours = new ArrayList<Integer>();
				
		for(int i=0;i<strArr.length;i++){
			int r = Integer.parseInt(strArr[i]);
			if(r == 1){
				neighbours.add(processNodes[i].getNodeData().getUID());
			}
		}
		return neighbours;
	}
	private void setInitialSendMessages(NodeData node){
		int sourceId = node.getUID();		
		Map<Integer,Message> sendMessage = new HashMap<Integer, Message>();
		Map<Integer,Integer> lastNeighbourRoundSend = new HashMap<Integer, Integer>();
		Map<Integer,Boolean> receivedAckOrNack = new HashMap<Integer, Boolean>();
		Map<Integer,Boolean> receivedAck = new HashMap<Integer, Boolean>();
		for(Integer neighbourId : node.getConectedNeighbours()){			
			int nextRound =1 + SharedData.generateRandomNumber();
			lastNeighbourRoundSend.put(neighbourId, nextRound);
			sendMessage.put(neighbourId, new Message(sourceId, nextRound,sourceId, false, false));
			receivedAckOrNack.put(neighbourId, false);
			receivedAck.put(neighbourId, false);
		}
		node.setLastNeighbourRoundSend(lastNeighbourRoundSend);
		node.setSendMessage(sendMessage);
		//set all ack flags to false
		node.setReceivedAckOrNack(receivedAckOrNack);
		node.setReceivedAck(receivedAck);
	}
	
	private void start(){
		for(int i=0;i<noOfNodes;i++){
			processNodes[i].start();
		}		
	}	
	
	
	public void process(){
		try{
			System.out.println("Start Process.");
			SharedData.status=new boolean[noOfNodes];
			while(!isConvergecastComplete()&& SharedData.timeUnit < SharedData.checkConverge(noOfNodes)){				
				Thread.sleep(100);
				if(SharedData.isRoundComplate()){
					SharedData.status=new boolean[noOfNodes];
					SharedData.timeUnit++;					
					Thread.sleep(2);
					for(int i=0;i<noOfNodes;i++){
						synchronized(processNodes[i]){						
							try{
								processNodes[i].notifyAll();
							}
							catch(Exception ex){
								ex.printStackTrace();
							}
						}
					}
				}				
			}
			SharedData.check(processNodes);
			for(int i=0;i<noOfNodes;i++){
				int leaderID = processNodes[i].getNodeData().markLeader();
				System.out.println(processNodes[i].getNodeData().getUID()+" knows leader is : "+leaderID);				
			}
			for(int i=0;i<noOfNodes;i++){
				try{
					processNodes[i].stop();
				}catch(Exception e){
					//consume interupt exception
					e.printStackTrace();
				}
			}				
			System.out.println("Process Completed.");
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	//change termination condition to parent=-1 and receive ACK from all neighbors
	private boolean isConvergecastComplete(){		
		for(int i=0;i<noOfNodes;i++){
			Map<Integer,Boolean> receivedAckOrNack = processNodes[i].getNodeData().getReceivedAckOrNack();
			for(Entry<Integer, Boolean> e : receivedAckOrNack.entrySet()){			
				if(e.getValue() == false){
					return false;
				}
			}			
		}		
		return true;				
	}
	
	private boolean isConvergecastAckComplete(){		
		for(int i=0;i<noOfNodes;i++){
			if(processNodes[i].getNodeData().getParentId() == -1){
				Map<Integer,Boolean> receivedAck = processNodes[i].getNodeData().getReceivedAck();
				for(Entry<Integer, Boolean> e : receivedAck.entrySet()){			
					if(e.getValue() == false){
						return false;
					}
				}	
			}
		}		
		return true;				
	}
	
}
