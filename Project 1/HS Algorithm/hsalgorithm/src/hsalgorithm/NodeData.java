package hsalgorithm;

public class NodeData {
	private int id;
	private int UID;
	private String send_plus_message;
	private String send_minus_message;
	private boolean isLeader;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUID() {
		return UID;
	}
	public void setUID(int uID) {
		UID = uID;
	}
	public String getSend_plus_message() {
		return send_plus_message;
	}
	public void setSend_plus_message(String send_plus_message) {
		this.send_plus_message = send_plus_message;
	}
	public String getSend_minus_message() {
		return send_minus_message;
	}
	public void setSend_minus_message(String send_minus_message) {
		this.send_minus_message = send_minus_message;
	}
	public boolean isLeader() {
		return isLeader;
	}
	public void setLeader(boolean isLeader) {
		this.isLeader = isLeader;
	}
	
}
