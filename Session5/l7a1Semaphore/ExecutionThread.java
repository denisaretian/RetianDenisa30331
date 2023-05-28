package l7a1Semaphore;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ExecutionThread extends Thread {
	int activity_min0, activity_max0, activity_min, activity_max, sleep;
	Semaphore s1, s2;
	CyclicBarrier bar;

	public ExecutionThread(CyclicBarrier bar, Semaphore s1, Semaphore s2, int act_min, int act_max, int activity_min,
			int activity_max, int sleep) {
		this.s1 = s1;
		this.s2 = s2;
		this.activity_min0 = act_min;
		this.activity_max0 = act_max;
		this.activity_min = activity_min;
		this.activity_max = activity_max;
		this.sleep = sleep;
		this.bar = bar;
	}

	public void run() {
		while (true) {
			activity();
			try {
				bar.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (BrokenBarrierException e) {
				e.printStackTrace();
			}
		}
	}

	public void activity() {
		System.out.println(this.getName() + " - STATE 1");
		int k = (int) Math.round(Math.random() * (activity_max0 - activity_min0) + activity_min0);
		for (int i = 0; i < k * 100000; i++) {
			i++;
			i--;
		}

		boolean locked1 = false;
		boolean locked2 = false;

		while (!locked1 || !locked2) {
			locked1 = s1.tryAcquire(); // System.out.println(this.getName() + " - acquired s1");
			locked2 = s2.tryAcquire(); // System.out.println(this.getName() + " - acquired s2");
			if (!locked1 || !locked2) {
				if (locked1) {
					System.out.println(this.getName() + " - failed to acquire l2");
					s1.release();
					System.out.println(this.getName() + " - unlocked l1");
				}
				if (locked2) {
					System.out.println(this.getName() + " - failed to acquire l1");
					s2.release();
					System.out.println(this.getName() + " - unlocked l2");
				}
			}
		}

		System.out.println(this.getName() + " - STATE 2");
		k = (int) Math.round(Math.random() * (activity_max - activity_min) + activity_min);
		for (int i = 0; i < k * 100000; i++) {
			i++;
			i--;
		}

		System.out.println(this.getName() + " - STATE 3");

		try {
			Thread.sleep(sleep * 500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		s1.release();
		System.out.println(this.getName() + " - unlocked l1");
		s2.release();
		System.out.println(this.getName() + " - unlocked l2");

		System.out.println(this.getName() + " - STATE 4");
	}
}
