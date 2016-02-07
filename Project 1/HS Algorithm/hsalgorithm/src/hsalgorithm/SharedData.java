package hsalgorithm;

import java.util.HashMap;
import java.util.Map;

public class SharedData {
	public static Map<String,Message> buffer=new HashMap<String, Message>();
	public static boolean[] status;
	public static boolean runPocess=true;
	
	public static synchronized void sendMessage(int round,int source,int destination,Message message){
		String key=round+"|"+source+"|"+destination;
		System.out.println(key);
		/*if(message!=null){
			System.out.println(key+" "+message.getUID()+" "+message.getHops()+" "+message.isForwardMessage());
		}*/
		
		buffer.put(key, message);
	}
	
	public static synchronized Message receiveMessage(int round,int source,int destination){
		System.out.println("Map Size :"+buffer.size());
		String key=round+"|"+source+"|"+destination;
		//System.out.println("Receiving Message for :"+key);
		if(buffer.containsKey(key)){
			Message r=buffer.get(key);
			buffer.remove(key);
			return r;
		}
		buffer.remove(key);
		return null;
	}
	
	public static synchronized void removeMessage(int round,int source,int destination){
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
