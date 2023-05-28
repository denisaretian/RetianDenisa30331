package l6a2;


public class Calculator extends Thread{
	private int sum;
	private int iterations;
	private int apelation;
	FileService service;
	
	public Calculator(FileService service) {
		sum = 0;
		iterations = 0;
		apelation = 0;
		this.service = service;
	}
	
	public void run(){
		while(!Main.isStopThreads()){
			
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void calc(int number) {
		sum += number;
		apelation += 1;
		if(apelation == 3) {
			iterations += 1;
			String msg = String.valueOf(sum + " " + iterations);
			System.out.println("calculator: " + sum + " " + iterations);
			service.write(msg);
			sum = 0;
			apelation = 0;
		}
	}
	
	
}
