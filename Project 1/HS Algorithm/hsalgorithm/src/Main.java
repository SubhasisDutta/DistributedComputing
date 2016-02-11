

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {
	public static final String INPUT_FILE="input.dat";//"C:\\Workspace\\Github\\DistributedComputing\\Project 1\\HS Algorithm\\hsalgorithm\\src\\hsalgorithm\\biginput.dat";
	public static final String OUTPUT_FILE="output.dat";//"C:\\Workspace\\Github\\DistributedComputing\\Project 1\\HS Algorithm\\hsalgorithm\\src\\hsalgorithm\\output.dat";
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
			
			bufferedReader.close();
						
			//SharedData.startWriter(OUTPUT_FILE);
			
			processNodes = new ProcessNode[noOfNodes];
			
			SharedData.status=new boolean[noOfNodes];
						
			for(int i=0;i<noOfNodes;i++){
				int nUID=Integer.parseInt(stringUIDs[i]);
				
				int nplus_UID=Integer.parseInt(stringUIDs[(i+1)%noOfNodes]);
				int minusIndex=(i-1)%noOfNodes;
				if(minusIndex<0){
					minusIndex=noOfNodes-1;
				}
				int nminus_UID=Integer.parseInt(stringUIDs[minusIndex]);
				NodeData nodeData=new NodeData(nUID,i, nplus_UID, nminus_UID);
				processNodes[i]=new ProcessNode(nodeData);
				processNodes[i].start();				
			}
			
			while(SharedData.runPocess){
				Thread.sleep(100);
				if(SharedData.isRoundComplate()){
					SharedData.status=new boolean[noOfNodes];
					SharedData.roundNo++;
					for(int i=0;i<noOfNodes;i++){
						synchronized(processNodes[i]){						
							try{
								processNodes[i].notifyAll();
							}
							catch(Exception ex){
								
							}
						}
					}
					//System.out.println("In main Thread");
				}
			}
			for(int i=0;i<noOfNodes;i++){
				processNodes[i].stop();
			}
			
			
			SharedData.writeLine("Main Ending");
			
			 try{
		          File fileOutput =new File(OUTPUT_FILE);
		          fileOutput.createNewFile();
		          
		    	  FileWriter fw = new FileWriter(fileOutput,true);
		    	  BufferedWriter bw = new BufferedWriter(fw);
		    	  PrintWriter pw = new PrintWriter(bw);
		    	  
		    	  //System.out.println(SharedData.outputString.toString());
		    	  
		    	  //bw.write(SharedData.outputString.toString());
		    	  
		    	  pw.print(SharedData.outputString.toString());
		    	  pw.close();

		    	  System.out.println("Data successfully appended at the end of file");

		       }catch(IOException ioe){
		    	   System.out.println("Exception occurred:");
		    	   ioe.printStackTrace();
		      }
			
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}
