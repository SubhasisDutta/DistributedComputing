import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

/**
* @author Subhasis Dutta, Ram Hariesh,Vibin Daniel
* @email-id sxd150830@utdallas.edu,rxc142330@utdallas.edu,vxd141730@utdallas.edu,
* @version 1.0
* 
* Program to simulate SynchGHS algorithm for leader election in Synchronous general networks
*/


public class SharedData {	
	public static Map<String, Message> buffer = new HashMap<String, Message>();
	public static boolean[] status;
	public static int timeUnit=0;
	
	public static synchronized int generateRandomNumber(){
		int n = new Random().nextInt(20) + 1;
		return n;
	}
	
	public static synchronized boolean isRoundComplate() {
		for (boolean b : status) {
			if (b == false) {
				return false;
			}
		}
		return true;
	}
	
	public static synchronized void sendMessage(int round,int source,int destination,Message message){
		String key=round+"|"+source+"|"+destination;
		/*if(message!=null && (source ==444 || destination == 444)){
			System.out.println(key+" MAXID="+message.getMaxUID()+" R="+message.isReject()+" A="+message.isAccept());
		}*/		
		buffer.put(key, message);
	}
	
	public static synchronized Message receiveMessage(int round,int source,int destination){
		String key=round+"|"+source+"|"+destination;
		if(buffer.containsKey(key)){
			Message r=buffer.get(key);			
			buffer.remove(key);
			return r;
		}		
		return null;
	}
}
