package example3;

public class Main {
	
	public static int sum;
	
	public static void main(String[] args){
		
		sum = 0;
		
		JoinTestThread w1 = new JoinTestThread("Thread 1",null,50000);
		JoinTestThread w2 = new JoinTestThread("Thread 2",w1,20000);
		w1.start();
		
		/*try {
			w1.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		w2.start();
		//System.out.println(sum);
		}


}
