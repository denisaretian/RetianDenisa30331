package l7a3;

import java.util.concurrent.CountDownLatch;

public class ExecutionThread extends Thread {
	CountDownLatch latch;
	Integer monitor;
	int sleep = 0, activity_min, activity_max;
	Thread t;

	public ExecutionThread(CountDownLatch latch, Integer i, int activity_min, int activity_max, Thread t) {
		this.monitor = i;
		this.activity_min = activity_min;
		this.activity_max = activity_max;
		this.t = t;
		this.latch = latch;
	}

	public void run() {
		while(true) {
			activity();
			latch.countDown();
			try {
				latch.await();
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void activity() {
		System.out.println(this.getName() + " - STATE 1");

		synchronized (monitor) {
			try {
				monitor.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(this.getName() + " - STATE 2");
		int k = (int) Math.round(Math.random() * (activity_max - activity_min) + activity_min);
		for (int i = 0; i < k * 100000; i++) {
			i++;
			i--;
		}

		System.out.println(this.getName() + " - STATE 3");
		/*if (this.t != null) {
			try {
				t.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/
	}
}
