import java.util.*;


/**
 * Car class ,a seperate thread which will try to enter the bridge and leave the bridge
 */
public class Car extends Thread{
	private String name= null;
	private Bridge bridge = null;
	private int number;
	public Car(String name, Bridge bridge,int number){

		this.name = name;
		this.bridge = bridge;
		this.number = number;
	}

	@Override
	public void run(){
		// System.out.println(this.name+" "+number+" is running");
		bridge.enterBridge(this.name);
		//enter bridge
		
		try{
			// System.out.println(name+" is running0");
			Thread.sleep(500);
			// System.out.println(name+" is running");
		}catch(Exception e){
			// System.out.println(name+"  FFFF");
			e.printStackTrace();
		}
		// System.out.println(name+" is entering bridge4");
		bridge.leaveBridge(this.name);
		//leave bridge
		if(number == bridge.carSize-1){
			Log.outPutAllMsg();
		}

	}
}