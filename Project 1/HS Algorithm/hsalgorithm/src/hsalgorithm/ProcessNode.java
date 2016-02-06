package hsalgorithm;

public class ProcessNode extends Thread {
	
	private NodeData nodeData;
	private int phaseNo;
	private int roundNo;
	
	public ProcessNode() {
		//Nothing
	}
	
	ProcessNode(NodeData nodeData){
		this.roundNo=0;
		this.phaseNo=0;
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
			//send the current value of send+ to process i + 1
			SharedData.sendMessage(this.roundNo+1, this.nodeData.getUID(), this.nodeData.getPlus_UID(), this.nodeData.getSend_plus_message());
			
			//send the current value of send- to process i - 1
			SharedData.sendMessage(this.roundNo+1, this.nodeData.getUID(), this.nodeData.getMinus_UID(), this.nodeData.getSend_minus_message());
			
			
			//Generate message to send to neighbors
			
			//send+ := null
			this.nodeData.setSend_plus_message(null);
			//send- := null
			this.nodeData.setSend_minus_message(null);
			
			
			//receive message and apply to current data to create new data
			Message receivedMessage_minus=SharedData.receiveMessage(this.roundNo, this.nodeData.getMinus_UID(), this.nodeData.getUID());			
			Message receivedMessage_plus=SharedData.receiveMessage(this.roundNo, this.nodeData.getPlus_UID(), this.nodeData.getUID());
						
			if(receivedMessage_minus!=null||receivedMessage_plus!=null){
				/*
				 * if the message from i - 1 is (v, out, h) then
					case
						v > u and h > 1: send+ := (v, out, h - 1)
						v > u and h = 1: send- : - (v, in, 1)
						v = u: status := leader
					endcase*/
				if(receivedMessage_minus.isForwardMessage()){
					int v=receivedMessage_minus.getUID();
					int u=this.nodeData.getUID();
					int h=receivedMessage_minus.getHops();
					
					if(v>u && h>1){
						this.nodeData.setSend_plus_message(new Message(v,true,h-1));						
					}else if(v>u && h=1){
						this.nodeData.setSend_minus_message(new Message(v,false,1));
					}else if(v==u){
						this.nodeData.setIsLeader("LEADER");
						//Need to think on how to propagate to all 
					}
				}
				/*
					if the message from i + 1 is (v, out, h) then
					case
						v > u and h > I: send- : - (v, out, h - I)
						v > u and h -- 1: send+ := (v, in, 1)
						v = u: status :-- leader
					endcase*/
				
				/*
					if the message from i - 1 is (v, in, 1) and v # u then
						send+ := (v, in, 1)
					if the message from i + 1 is (v, in, 1) and v # u then
						send- := (v, in, 1)
					if the messages from i - 1 and i + 1 are both (u, in, 1) then
						phase := phase + 1
						send+ := (u, out, 2 phase)
						send- := (u, out, 2 phase)
				 * 
				 */
			}
			synchronized (this){
				try{
					this.wait();
				} 
				catch(Exception e){
					e.printStackTrace();
				}
			}
			this.roundNo++;			
		}
	}
}
