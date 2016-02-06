package hsalgorithm;

public class Message {
	private int UID;
	private boolean isForwardMessage;
	private int hops;
	
	
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
