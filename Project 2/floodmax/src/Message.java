/**
* @author Subhasis Dutta, Ram Hariesh,Vibin Daniel
* @email-id sxd150830@utdallas.edu,rxc142330@utdallas.edu,vxd141730@utdallas.edu,
* @version 1.0
* 
* Program to simulate Floodmax algorithm for leader election in Asynchronous general networks
*/

public class Message {
	private int maxUID;
	private int roundNo;
	private int sourceId;
	private boolean isReject;
	private boolean isAccept;
	
	public Message(int maxUID,int roundNo,int sourceId,boolean isReject,boolean isAccept){
		this.maxUID=maxUID;
		this.roundNo = roundNo;	
		this.sourceId = sourceId;
		this.isReject = isReject;
		this.isAccept = isAccept;
	}

	public int getRoundNo() {
		return roundNo;
	}

	public void setRoundNo(int roundNo) {
		this.roundNo = roundNo;
	}

	public boolean isReject() {
		return isReject;
	}

	public void setReject(boolean isReject) {
		this.isReject = isReject;
	}

	public boolean isAccept() {
		return isAccept;
	}

	public void setAccept(boolean isAccept) {
		this.isAccept = isAccept;
	}

	public int getMaxUID() {
		return maxUID;
	}

	public void setMaxUID(int maxUID) {
		this.maxUID = maxUID;
	}

	public int getSourceId() {
		return sourceId;
	}

	public void setSourceId(int sourceId) {
		this.sourceId = sourceId;
	}
	
}
