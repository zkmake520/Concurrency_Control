import java.util.*;
public class Log{
	static Queue<String> msgQueue = new LinkedList<>();
	public static synchronized void out(String msg){
		msgQueue.add(msg);
	}
	public static void outPutAllMsg(){
		while(!msgQueue.isEmpty()){
			System.out.println(msgQueue.poll());
		}
	}
}