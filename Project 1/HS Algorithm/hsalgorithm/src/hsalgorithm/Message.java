package hsalgorithm;

public class Message {
	private int UID;
	private boolean isForwardMessage;
	
	private int hops;
	
	public Message() {
		// TODO Auto-generated constructor stub
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
