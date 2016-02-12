import java.util.*;


/**
 * Car class ,a seperate thread which will try to enter the bridge and leave the bridge
 */
public class Car extends Thread{
	private static String name= null;
	private Bridge bridge = null;
	public Car(String name, Bridge bridge){
		this.name = name;
		this.bridge = bridge;
	}

	@Override
	public void run(){
		bridge.enterBridge(name);
		//enter bridge
		Thread.sleep(500);
		bridge.leaveBridge(name);
		//leave bridge

	}
}