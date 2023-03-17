package example3;

public class JoinTestThread extends Thread{
	
	Thread t;
	String name;
	int number;
	
	
	JoinTestThread(String n, Thread t, int number){
		this.name = n;
		this.t=t;
		this.number = number;
		
	}
	
	public void run() {
		System.out.println("Thread "+this.name+" has entered the run() method");
		try {
			if (t != null)
				t.join();
			System.out.println("Thread "+this.name+" executing operation.");
			//int sum = 0;
			for(int i=1; i<=number;i++) {
				if(number % i == 0) {
					Main.sum = Main.sum+i;
					
					}
				}
			System.out.println(Main.sum + " by thread " + this.name);
			Thread.sleep(3000);
			
			
			System.out.println("Thread "+this.name+" has terminated operation.");
		} catch(Exception e){e.printStackTrace();}
	}
}
