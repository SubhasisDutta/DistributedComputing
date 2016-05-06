import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

/**
* @author Subhasis Dutta, Ram Hariesh,Vibin Daniel
* @email-id sxd150830@utdallas.edu,rxc142330@utdallas.edu,vxd141730@utdallas.edu,
* @version 1.0
* 
* Program to simulate Floodmax algorithm for leader election in Asynchronous general networks
*/


public class SharedData {	
	public static Map<String, Message> buffer = new HashMap<String, Message>();
	public static boolean[] status;
	public static int timeUnit=0;
	public static int x =10;
	
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
	
	public static synchronized int checkConverge(int a){
		if(a<10)return a *20;
		if(a<15)return a*15;
		else 
			return a * 10;
	}
	
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
		return null;
	}
	
	public static synchronized void check(ProcessNode[] processNodes){
		int m=0;
		for(int i=0;i<processNodes.length;i++){
			if(processNodes[i].getNodeData().getMaxUID()>m){
				m=processNodes[i].getNodeData().getMaxUID();
			}
		}
		for(int i=0;i<processNodes.length;i++){
			if(processNodes[i].getNodeData().getMaxUID()<m){
				processNodes[i].getNodeData().setMaxUID(m);
			}
		}
	}
}
