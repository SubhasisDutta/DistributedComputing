/**
* @author Subhasis Dutta, Ram Hariesh,Vibin Daniel
* @email-id sxd150830@utdallas.edu,rxc142330@utdallas.edu,vxd141730@utdallas.edu,
* @version 1.0
* 
* Program to simulate SynchGHS algorithm for leader election in Synchronous general networks
*/

public class Message {
	private int UID;
	private boolean isForwardMessage;
	
	private int hops;
	
	public Message() {
		
	}
	
	public Message(int UID,boolean isForwadMessage,int hops){
		this.UID=UID;
		this.isForwardMessage=isForwadMessage;
		this.hops=hops;				
	}
	
	public int getUID() {
		return UID;
	}
	public void setUID(int uID) {
		UID = uID;
	}
	public boolean isForwardMessage() {
		return isForwardMessage;
	}
	public void setForwardMessage(boolean isForwardMessage) {
		this.isForwardMessage = isForwardMessage;
	}
	public int getHops() {
		return hops;
	}
	public void setHops(int hops) {
		this.hops = hops;
	}
	
}
