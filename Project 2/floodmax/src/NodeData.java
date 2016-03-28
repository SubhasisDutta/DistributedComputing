/**
* @author Subhasis Dutta, Ram Hariesh,Vibin Daniel
* @email-id sxd150830@utdallas.edu,rxc142330@utdallas.edu,vxd141730@utdallas.edu,
* @version 1.0
* 
* Program to simulate SynchGHS algorithm for leader election in Synchronous general networks
*/
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class NodeData {
	private int UID;
	private int maxUID;
	private int index;		
	
	private String isLeader;	
	private int leaderUID;	
	
	private int parentId;
	
	private List<NodeData> conectedNeighbours;
	private Map<Integer,Integer> lastNeighbourRoundSend;
	private Map<Integer,Message> sendMessage;
	private Map<Integer,Boolean> receivedAckOrNack;
	
	public NodeData(int UID,int index) {		
		this.leaderUID=-1;
		this.parentId= -1;		
		this.UID=UID;
		this.maxUID=UID;
		this.index=index;
		this.isLeader="UNKNOWN";
	}
	
	public void resetAckOrNack(){
		Map<Integer,Boolean> receivedAckOrNack = new HashMap<Integer, Boolean>();
		for(NodeData node:conectedNeighbours ){
			int neighbourId = node.getUID();
			receivedAckOrNack.put(neighbourId, false);
		}
		this.setReceivedAckOrNack(receivedAckOrNack);
	}
	public boolean checkAckNAck(){		
		for(Entry<Integer,Boolean> e : receivedAckOrNack.entrySet()){
			if(e.getValue() == false && e.getKey() != parentId){
				return false;
			}
		}		
		return true;
	}
	
	public int markLeader(){
		if(UID == maxUID){
			this.isLeader="LEADER";
		}else{
			this.isLeader="NOT LEADER";
		}
		return maxUID;
	}
	public void markTrueAckNack(int nId){
		receivedAckOrNack.put(nId, true);
	}

	public int getUID() {
		return UID;
	}


	public void setUID(int uID) {
		UID = uID;
	}

	public int getMaxUID() {
		return maxUID;
	}

	public void setMaxUID(int maxUID) {
		this.maxUID = maxUID;
	}
	public int getParentId() {
		return parentId;
	}


	public void setParentId(int parentId) {
		this.parentId = parentId;
	}


	public int getIndex() {
		return index;
	}


	public void setIndex(int index) {
		this.index = index;
	}

	public String getIsLeader() {
		return isLeader;
	}


	public void setIsLeader(String isLeader) {
		this.isLeader = isLeader;
	}


	public int getLeaderUID() {
		return leaderUID;
	}


	public void setLeaderUID(int leaderUID) {
		this.leaderUID = leaderUID;
	}


	public List<NodeData> getConectedNeighbours() {
		return conectedNeighbours;
	}


	public void setConectedNeighbours(List<NodeData> conectedNeighbours) {
		this.conectedNeighbours = conectedNeighbours;
	}

	public Map<Integer, Boolean> getReceivedAckOrNack() {
		return receivedAckOrNack;
	}




	public void setReceivedAckOrNack(Map<Integer, Boolean> receivedAckOrNack) {
		this.receivedAckOrNack = receivedAckOrNack;
	}




	public Map<Integer, Message> getSendMessage() {
		return sendMessage;
	}




	public void setSendMessage(Map<Integer, Message> sendMessage) {
		this.sendMessage = sendMessage;
	}




	public Map<Integer, Integer> getLastNeighbourRoundSend() {
		return lastNeighbourRoundSend;
	}




	public void setLastNeighbourRoundSend(
			Map<Integer, Integer> lastNeighbourRoundSend) {
		this.lastNeighbourRoundSend = lastNeighbourRoundSend;
	}
	

		
}
