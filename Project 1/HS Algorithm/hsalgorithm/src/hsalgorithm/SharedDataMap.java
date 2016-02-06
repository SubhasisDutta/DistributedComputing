package hsalgorithm;

import java.util.HashMap;
import java.util.Map;

public class SharedDataMap {
	public static Map<String,Message> buffer=new HashMap<String, Message>();
	
	public static void sendMessage(int round,int phase,int source,int destination,Message message){
		String key=round+"|"+phase+"|"+source+"|"+destination;
		buffer.put(key, message);
	}
	
	public static Message receiveMessage(int round,int phase,int source,int destination){
		String key=round+"|"+phase+"|"+source+"|"+destination;
		if(buffer.containsKey(key)){
			return buffer.get(key);
		}
		return null;
	}
	
	public static void removeMessage(int round,int phase,int source,int destination){
		String key=round+"|"+phase+"|"+source+"|"+destination;
		buffer.remove(key);
	}
}
