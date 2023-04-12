package app3;

public class ExecutionThread extends Thread {
	Integer monitor;
	int activity_min, activity_max, sleep;

	public ExecutionThread(Integer monitor, int activity_min, int activity_max, int sleep) {
		this.monitor = monitor;
		this.sleep = sleep;
		this.activity_min = activity_min;
		this.activity_max = activity_max;
	}

	public void run() {
		while (true) {
			System.out.println(this.getName() + " - STATE 1");
			
			
			synchronized (monitor) {
				System.out.println(this.getName() + " - STATE 2");
				
				
				int k = (int) Math.round(Math.random() * (activity_max - activity_min) + activity_min);
				for (int i = 0; i < k * 100000; i++) {
					i++;
					i--;
				}
			}
			System.out.println(this.getName() + " - STATE 3");
			
			try {
				Thread.sleep(sleep * 500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(this.getName() + " - STATE 4");
		}
	}
}
