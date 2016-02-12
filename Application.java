public class Application{
	public static void main(String[] args){
		//Test the Car-Bridge emulation
		Random rand = new Random();
		for(int i=0; i < 20; i++){
			Thread.sleep(rand.nextInt(3)+1) * 100);
			if(i % 2== 0){
				Car car = new Car("Leftcar_"+i);
				car.start();
			}

			else{
				Car car = new Car("Rightcar_"+i);
				car.start();
			}
		}	

	}

}