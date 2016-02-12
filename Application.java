import java.util.Random;
public class Application{
	public static void main(String[] args){
		//Test the Car-Bridge emulation
		int carSize = 0;
		int capacity = 0;
		if(args.length < 2){
			System.out.println("Format: java Application (carSize) 50 (bridge capacity) 3");
			return;
		}
		else{
			carSize = Integer.parseInt(args[0]);
			capacity = Integer.parseInt(args[1]);
		}
		Bridge bridge = new Bridge(capacity,carSize);
		Random rand = new Random();
		for(int i=0; i < carSize; i++){
			try{
				Thread.sleep((rand.nextInt(3)+1)* 100);
			}catch(Exception e){
				e.printStackTrace();
			}
			if(i % 2== 0){
				new Car("Leftcar_"+i,bridge,i).start();
			}

			else{
				new Car("Rightcar_"+i,bridge,i).start();
			}
		}	

	}

}