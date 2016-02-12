import java.util.*;


/**
 * Car class ,a seperate thread which will try to enter the bridge and leave the bridge
 */
public class Car extends Thread{
	private static String name= null;
	private Bridge bridge = null;
	private int number;
	public Car(String name, Bridge bridge,int number){
		this.name = name;
		this.bridge = bridge;
		this.number = number;
	}

	@Override
	public void run(){
		bridge.enterBridge(name);
		//enter bridge
		// System.out.println(name+" is entering bridge2");
		try{
			Thread.sleep(500);
			// System.out.println(name+" is entering bridge3");
		}catch(Exception e){
			// System.out.println(name+"  FFFF");
			e.printStackTrace();
		}
		// System.out.println(name+" is entering bridge4");
		bridge.leaveBridge(name);
		//leave bridge
		if(number == bridge.carSize-1){
			Log.outPutAllMsg();
		}

	}
}