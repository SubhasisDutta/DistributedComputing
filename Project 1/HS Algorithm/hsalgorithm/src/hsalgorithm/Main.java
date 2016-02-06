package hsalgorithm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Main {
	public static final String INPUT_FILE="input.dat";
	private int noOfNodes;
	private ProcessNode[] processNodes;
	
	private int round=0;
	private int phase=0;
	
	public static void main(String[] args){
		Main main=new Main();
		//load data from file and start N process threads
		main.loadAndInitilize();
		
	}
	
	public void loadAndInitilize(){
		FileReader fileReader=null;
		BufferedReader bufferedReader=null;
		
		try{
			File file=new File(INPUT_FILE);
			fileReader=new FileReader(file);
			bufferedReader=new BufferedReader(fileReader);
			//Read First Line
			noOfNodes=Integer.parseInt(bufferedReader.readLine());
			//Read Second Line
			String[] stringUIDs=bufferedReader.readLine().split(" ");
			
			processNodes = new ProcessNode[noOfNodes];
			
			SharedData.status=new boolean[noOfNodes];
						
			for(int i=0;i<noOfNodes;i++){
				int nUID=Integer.parseInt(stringUIDs[i]);
				int nplus_UID=Integer.parseInt(stringUIDs[(i+1)%noOfNodes]);
				int nminus_UID=Integer.parseInt(stringUIDs[(i-1)%noOfNodes]);
				NodeData nodeData=new NodeData(nUID, nplus_UID, nminus_UID);
				processNodes[i]=new ProcessNode(nodeData);
				processNodes[i].start();				
			}
			
			while(SharedData.runPocess){
				Thread.sleep(100);
				if(SharedData.isRoundComplate()){
					SharedData.status=new boolean[noOfNodes];
					round++;
					for(int i=0;i<noOfNodes;i++){
						synchronized(processNodes[i]){						
							try{
								processNodes[i].notifyAll();
							}
							catch(Exception ex){
								
							}
						}
					}
					
				}
			}
			
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}
