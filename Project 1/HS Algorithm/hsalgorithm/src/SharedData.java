/**
* @author Subhasis Dutta, Ram Hariesh,Vibin Daniel
* @email-id sxd150830@utdallas.edu,rxc142330@utdallas.edu,vxd141730@utdallas.edu,
* @version 1.0
* 
* Program to simulate SynchGHS algorithm for leader election in Synchronous general networks
*/
import java.util.HashMap;
import java.util.Map;

public class SharedData {
	public static Map<String,Message> buffer=new HashMap<String, Message>();
	public static boolean[] status;
	public static boolean runPocess=true;
	public static int roundNo=0;
	
	public static StringBuilder outputString = new StringBuilder();
	

	public static synchronized void sendMessage(int round,int source,int destination,Message message){
		String key=round+"|"+source+"|"+destination;
		buffer.put(key, message);
	}
	
	public static synchronized Message receiveMessage(int round,int source,int destination){
		String key=round+"|"+source+"|"+destination;
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
	
	public static synchronized void writeLine(String str){
		System.out.println(str);
		outputString.append(str+'\n');	
	}
}
