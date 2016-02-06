package hsalgorithm;

import java.util.HashMap;
import java.util.Map;

public class SharedData {
	public static Map<String,Message> buffer=new HashMap<String, Message>();
	public static boolean[] status;
	public static boolean runPocess=true;
	
	public static void sendMessage(int round,int source,int destination,Message message){
		String key=round+"|"+source+"|"+destination;
		buffer.put(key, message);
	}
	
	public static Message receiveMessage(int round,int source,int destination){
		String key=round+"|"+source+"|"+destination;
		if(buffer.containsKey(key)){
			return buffer.get(key);
		}
		return null;
	}
	
	public static void removeMessage(int round,int source,int destination){
		String key=round+"|"+source+"|"+destination;
		buffer.remove(key);
	}
	
	public static boolean isRoundComplate(){
		for(boolean b:status){
			if(b== false){
				return false;						
			}
		}
		return true;
	}
}
