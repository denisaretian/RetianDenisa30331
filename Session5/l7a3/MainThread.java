package l7a3;

import java.util.concurrent.CountDownLatch;

public class MainThread extends Thread {
	Integer monitor1, monitor2;
	CountDownLatch latch;
	int sleep, activity_min, activity_max;

	public MainThread(CountDownLatch latch, Integer monitor1, Integer monitor2, int sleep, int activity_min, int activity_max) {
		this.sleep = sleep;
		this.activity_min = activity_min;
		this.activity_max = activity_max;
		this.monitor1 = monitor1;
		this.monitor2 = monitor2;
		this.latch = latch;
	}

	public void run() {
		while(true) {
			activity();
			latch.countDown();
			try {
				latch.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void activity() {
		System.out.println(this.getName() + " - STATE 1");

		try {
			Thread.sleep(sleep * 500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println(this.getName() + " - STATE 2");
		int k = (int) Math.round(Math.random() * (activity_max - activity_min) + activity_min);
		for (int i = 0; i < k * 100000; i++) {
			i++;
			i--;
		}
		synchronized (monitor1) {
			synchronized (monitor2) {

				monitor2.notify();
			}
			monitor1.notify();
		}
		System.out.println(this.getName() + " - STATE 3");
	}
}
