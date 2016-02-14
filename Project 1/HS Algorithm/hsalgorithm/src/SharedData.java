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
	public static Map<String, Message> buffer = new HashMap<String, Message>();
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
	public static boolean runPocess = true;
	public static int roundNo = 0;
	public static final String OUTPUT_FILE = "output.dat";
	public static Boolean defaultAppend = true;

	public static synchronized void sendMessage(int round, int source, int destination, Message message) {
		String key = round + "|" + source + "|" + destination;
		// System.out.println(key);
		/*
		 * if(message!=null){ System.out.println(key+" "+message.getUID()+" "
		 * +message.getHops()+" "+message.isForwardMessage()); }
		 */

		buffer.put(key, message);
	}

	public static synchronized Message receiveMessage(int round, int source, int destination) {
		// System.out.println("Map Size :"+buffer.size());
		String key = round + "|" + source + "|" + destination;
		// System.out.println("Receiving Message for :"+key);
		if (buffer.containsKey(key)) {
			Message r = buffer.get(key);
			buffer.remove(key);
			return r;
		}
		buffer.remove(key);
		return null;
	}

	public static synchronized void removeMessage(int round, int source, int destination) {
		String key = round + "|" + source + "|" + destination;
		buffer.remove(key);
	}

	public static boolean isRoundComplate() {
		for (boolean b : status) {
			if (b == false) {
				return false;
			}
		}
		return true;
	}
	
	public static synchronized void writeLine(String str){
		System.out.println(str);
		outputString.append(str+'\n');	

	public static void startWriter(String OUTPUT_FILE) {
		try {
			File outputFile = new File(OUTPUT_FILE);
			if (!outputFile.exists())
				outputFile.createNewFile();
			defaultAppend = false;
			writeLine("");
			defaultAppend = true;

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public static synchronized void writeLine(String str) {

		try {
			FileWriter fw = new FileWriter(new File(OUTPUT_FILE), defaultAppend);
			BufferedWriter bufferWriter = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bufferWriter);
			// File outputFile = new File(OUTPUT_FILE);
			System.out.println(str);
			// FileWriter fw = new FileWriter(OUTPUT_FILE,true);
			// BufferedWriter bufferWriter = new BufferedWriter(fw);

			// bufferWriter.append(str);
			// bufferWriter.newLine();
			bufferWriter.write(str);
			bufferWriter.newLine();
			
			bufferWriter.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
