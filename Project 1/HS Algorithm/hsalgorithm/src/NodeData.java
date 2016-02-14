/**
* @author Subhasis Dutta, Ram Hariesh,Vibin Daniel
* @email-id sxd150830@utdallas.edu,rxc142330@utdallas.edu,vxd141730@utdallas.edu,
* @version 1.0
* 
* Program to simulate SynchGHS algorithm for leader election in Synchronous general networks
*/

public class NodeData {
	private int UID;
	private int index;		
	private Message send_plus_message;
	private Message send_minus_message;		
	private String isLeader;	
	private int leaderUID;	
	private int plus_UID;	
	private int minus_UID;
	
	public NodeData(int UID,int index,int plus_UID,int minus_UID) {		
		this.leaderUID=-1;
		this.UID=UID;
		this.index=index;		
		this.send_minus_message=new Message(UID,true,1);
		this.send_plus_message=new Message(UID,true,1);	
		this.isLeader="UNKNOWN";		
		this.plus_UID=plus_UID;
		this.minus_UID=minus_UID;		
	}

	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public int getLeaderUID() {
		return leaderUID;
	}
	public void setLeaderUID(int leaderUID) {
		this.leaderUID = leaderUID;
	}
	public int getUID() {
		return UID;
	}	
	public void setUID(int uID) {
		UID = uID;
	}
	public Message getSend_plus_message() {
		return send_plus_message;
	}
	public void setSend_plus_message(Message send_plus_message) {
		this.send_plus_message = send_plus_message;
	}
	public Message getSend_minus_message() {
		return send_minus_message;
	}
	public void setSend_minus_message(Message send_minus_message) {
		this.send_minus_message = send_minus_message;
	}
	public String getIsLeader() {
		return isLeader;
	}
	public void setIsLeader(String isLeader) {
		this.isLeader = isLeader;
	}
	public int getPlus_UID() {
		return plus_UID;
	}
	public void setPlus_UID(int plus_UID) {
		this.plus_UID = plus_UID;
	}
	public int getMinus_UID() {
		return minus_UID;
	}
	public void setMinus_UID(int minus_UID) {
		this.minus_UID = minus_UID;
	}	
}
