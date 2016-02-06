package hsalgorithm;

public class NodeData {
	//private int id;
	private int UID;
	
	private Message send_plus_message;
	private Message send_minus_message;
	
	private Message receive_plus_message;
	private Message receive_minus_message;
	
	private String isLeader;
	
	private int plus_UID;	
	private int minus_UID;
	
	public NodeData(int UID,int plus_UID,int minus_UID) {
		this.UID=UID;
		
		this.send_minus_message=null;
		this.send_plus_message=null;
		this.receive_minus_message=null;
		this.receive_plus_message=null;
		
		this.isLeader="UNKNOWN";
		
		this.plus_UID=plus_UID;
		this.minus_UID=minus_UID;		
	}
	
	
	/*public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}*/
	
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
	public Message getReceive_plus_message() {
		return receive_plus_message;
	}
	public void setReceive_plus_message(Message receive_plus_message) {
		this.receive_plus_message = receive_plus_message;
	}
	public Message getReceive_minus_message() {
		return receive_minus_message;
	}
	public void setReceive_minus_message(Message receive_minus_message) {
		this.receive_minus_message = receive_minus_message;
	}
	
}
