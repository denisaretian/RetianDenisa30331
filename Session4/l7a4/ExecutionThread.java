package l7a4;

import java.util.concurrent.Semaphore;

public class ExecutionThread extends Thread {
	Semaphore semaphore;
	int activity_min, activity_max, sleep;

	public ExecutionThread(Semaphore semaphore, int activity_min, int activity_max, int sleep) {
		this.semaphore = semaphore;
		this.sleep = sleep;
		this.activity_min = activity_min;
		this.activity_max = activity_max;
	}

	public void run() {
		//while (true) {
			System.out.println(this.getName() + " - STATE 1");
			
			
			try {
				semaphore.acquire(2); System.out.println(this.getName() + " - aquired semaphore");
				System.out.println(this.getName() + " - STATE 2");
			}catch (InterruptedException e) {
                e.printStackTrace();
            }
				
				int k = (int) Math.round(Math.random() * (activity_max - activity_min) + activity_min);
				for (int i = 0; i < k * 100000; i++) {
					i++;
					i--;
				}
				
				semaphore.release(2); System.out.println(this.getName() + " - released semaphore");
				
			System.out.println(this.getName() + " - STATE 3");
			
			try {
				Thread.sleep(sleep * 500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			System.out.println(this.getName() + " - STATE 4");
		//}
	}
}
