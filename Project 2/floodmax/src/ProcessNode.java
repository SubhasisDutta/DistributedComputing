import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
* @author Subhasis Dutta, Ram Hariesh,Vibin Daniel
* @email-id sxd150830@utdallas.edu,rxc142330@utdallas.edu,vxd141730@utdallas.edu,
* @version 1.0
* 
* Program to simulate SynchGHS algorithm for leader election in Synchronous general networks
*/

public class ProcessNode extends Thread {
	
	private NodeData nodeData;	
	
	
	public NodeData getNodeData() {
		return nodeData;
	}

	public void setNodeData(NodeData nodeData) {
		this.nodeData = nodeData;
	}

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
			for(Entry<Integer, Message> e : nodeData.getSendMessage().entrySet()){
				Message m = e.getValue();
				SharedData.sendMessage(m.getRoundNo(), nodeData.getUID(), e.getKey(), m);
			}
			//empty the send message so that it does not send again
			Map<Integer,Message> sendMessage = new HashMap<Integer, Message>();			
			//receive message from all neighbors if available for a particular round
			List<Message> receivedMessages = new ArrayList<Message>();
			for(Integer neighbourId : nodeData.getConectedNeighbours()){				
				Message m = SharedData.receiveMessage(SharedData.roundNo, neighbourId, nodeData.getUID());
				if(m!=null){
					receivedMessages.add(m);
				}
			}
			if(receivedMessages.size()> 0){
				//System.out.println("Recived Message Round="+SharedData.roundNo+" By Process "+nodeData.getUID());
				//System.out.println(nodeData.getReceivedAckOrNack().toString());
				
				boolean sendNewMessageFlag = false;
				for(Message m : receivedMessages){
					if(m.getMaxUID() == -1){
						//check is it an accept or reject message and do accordingly
						nodeData.markTrueAckNack(m.getSourceId());
						//check and send accept to parent
						boolean isFound=nodeData.checkAckNAck();
						if(isFound && nodeData.getParentId()!=-1){
							//send ack to parent							
							int nextRound = getNextRoundForNeighbour(m.getSourceId());
							sendMessage.put(nodeData.getParentId(), new Message(-1, nextRound, nodeData.getUID(), false, true));
							nodeData.markTrueAckNack(nodeData.getParentId());
						}
					}else{						
						if(nodeData.getMaxUID() < m.getMaxUID()){
							nodeData.setMaxUID(m.getMaxUID());
							nodeData.setParentId(m.getSourceId());							
							System.out.println("UID="+nodeData.getUID()+" got updated to MAXUID="+nodeData.getMaxUID()
									+" in Round "+SharedData.roundNo);
							sendNewMessageFlag=true;
						}else{
							//need to send a reject message							
							int nextRound = getNextRoundForNeighbour(m.getSourceId());
							//send reject message to source
							sendMessage.put(m.getSourceId(), new Message(-1, nextRound, nodeData.getUID(), true, false));
						}
						
					}
				}
				if(sendNewMessageFlag){
					nodeData.resetAckOrNack();
					for(Integer neighbourId : nodeData.getConectedNeighbours()){												
						int nextRound = getNextRoundForNeighbour(neighbourId);
						sendMessage.put(neighbourId, new Message(nodeData.getMaxUID(), nextRound,
								nodeData.getUID(), false, false));
					}					
				}				
			}/*else{
				for(Integer neighbourId : nodeData.getConectedNeighbours()){												
					int nextRound = getNextRoundForNeighbour(neighbourId);
					sendMessage.put(neighbourId, new Message(nodeData.getMaxUID(), nextRound,
							nodeData.getUID(), false, false));
				}
			}*/
			
			nodeData.setSendMessage(sendMessage);
			try{
				Thread.sleep(5);
			}catch(Exception e){
				e.printStackTrace();
			}
			//if no message received or message processing done go for wait
			synchronized (this){
				try{
					SharedData.status[this.nodeData.getIndex()]=true;
					this.wait();			
				} 
				catch(Exception e){
					e.printStackTrace();
				}
			}	   
		}
	}	
	
	private int getNextRoundForNeighbour(int neigh){
		Map<Integer,Integer> lastNeighbourRoundSend = nodeData.getLastNeighbourRoundSend();
		int lastRound = lastNeighbourRoundSend.get(neigh);
		int nextRound = Math.max(lastRound, SharedData.roundNo) + SharedData.generateRandomNumber();
		lastNeighbourRoundSend.put(neigh,nextRound);
		return nextRound;		
	}	
}
