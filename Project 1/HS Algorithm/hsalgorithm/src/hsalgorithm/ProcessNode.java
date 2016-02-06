package hsalgorithm;

public class ProcessNode extends Thread {
	
	private NodeData nodeData;
	private int phaseNo;
	private int roundNo;
	
	public ProcessNode() {
		//Nothing
	}
	
	ProcessNode(NodeData nodeData){
		this.nodeData=nodeData;
	}
	
	
	/**
	 * Need to implement the HS Algorithm in this.
	 */
	@Override
	public void run(){
		
		//Loop until the leader is elected and all are aware of the leader
		while(true){
			//Message Generation function applied to current state or NodeData
			
			//Generate mssage to send
		}
	}
}
