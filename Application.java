import java.util.Random;
public class Application{
	public static void main(String[] args){
		//Test the Car-Bridge emulation
		Bridge bridge = new Bridge(3,10);
		Random rand = new Random();
		for(int i=0; i < 10; i++){
			try{
				Thread.sleep((rand.nextInt(3)+1)* 100);
			}catch(Exception e){
				e.printStackTrace();
			}
			if(i % 2== 0){
				Car car = new Car("Leftcar_"+i,bridge,i);
				car.start();
			}

			else{
				Car car = new Car("Rightcar_"+i,bridge,i);
				car.start();
			}
		}	

	}

}