package l7a2Semaphore;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class ExecutionThread extends Thread {
	CyclicBarrier bar;
	Semaphore s1, s2 = null;
	int sleep, activity_min, activity_max;

	public ExecutionThread(CyclicBarrier bar, Semaphore s, int activity_min, int activity_max, int sleep) {
		this.s1 = s;
		this.sleep = sleep;
		this.activity_min = activity_min;
		this.activity_max = activity_max;
		this.bar = bar;
	}

	public ExecutionThread(CyclicBarrier bar, Semaphore s1, Semaphore s2, int activity_min, int activity_max,
			int sleep) {
		this.s1 = s1;
		this.s2 = s2;
		this.sleep = sleep;
		this.activity_min = activity_min;
		this.activity_max = activity_max;
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

		if (s2 == null) {

			boolean locked = false;
			while (!locked) {
				locked = s1.tryAcquire();
				if (locked) {
					System.out.println(this.getName() + " - failed to acquire l");
				}
				try {
					this.sleep(500);
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
			try {
				Thread.sleep(sleep * 500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			s1.release();
			System.out.println(this.getName() + " - unlocked l");
			System.out.println(this.getName() + " - STATE 3");
		}

		else {
			boolean locked1 = false;
			boolean locked2 = false;

			while (!locked1 || !locked2) {
				locked1 = s1.tryAcquire(); // System.out.println(this.getName() + " - acquired l1");
				locked2 = s2.tryAcquire(); // System.out.println(this.getName() + " - acquired l2");
				if (!locked1 || !locked2) {
					if (locked1) {
						System.out.println(this.getName() + " - failed to acquire l2");
						s1.release();
						System.out.println(this.getName() + " - unlocked l1");
						try {
							this.sleep(500);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					if (locked2) {
						System.out.println(this.getName() + " - failed to acquire l1");
						s2.release();
						System.out.println(this.getName() + " - unlocked l2");
						try {
							this.sleep(500);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
			System.out.println(this.getName() + " - STATE 2");
			int k = (int) Math.round(Math.random() * (activity_max - activity_min) + activity_min);
			for (int i = 0; i < k * 100000; i++) {
				i++;
				i--;
			}

			try {
				Thread.sleep(sleep * 500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			s1.release();
			System.out.println(this.getName() + " - unlocked l1");
			s2.release();
			System.out.println(this.getName() + " - unlocked l2");

			System.out.println(this.getName() + " - STATE 3");
		}
	}
}
