package app2;

public class ExecutionThread extends Thread {
	Integer monitor, monitor2;
	int activity_min0, activity_max0, activity_min, activity_max, sleep;

	public ExecutionThread(Integer monitor, Integer monitor2, int act_min, int act_max, int activity_min,
			int activity_max, int sleep) {
		this.monitor = monitor;
		this.monitor2 = monitor2;
		this.activity_min0 = act_min;
		this.activity_max0 = act_max;
		this.activity_min = activity_min;
		this.activity_max = activity_max;
		this.sleep = sleep;
	}

	public void run() {
		System.out.println(this.getName() + " - STATE 1");
		int k = (int) Math.round(Math.random() * (activity_max0 - activity_min0) + activity_min0);
		for (int i = 0; i < k * 100000; i++) {
			i++;
			i--;
		}

		/*
		 * synchronized (monitor) { System.out.println(this.getName() + " - STATE 2"); k
		 * = (int) Math.round(Math.random() * (activity_max - activity_min) +
		 * activity_min); for (int i = 0; i < k * 100000; i++) { i++; i--; }
		 * 
		 * synchronized (monitor2) { //this leads to deadlock
		 * 
		 * System.out.println(this.getName() + " - STATE 3");
		 * 
		 * try { Thread.sleep(sleep * 500); } catch (InterruptedException e) {
		 * e.printStackTrace(); } } }
		 */

		synchronized (monitor) {
			synchronized (monitor2) {
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
			}
		}
		System.out.println(this.getName() + " - STATE 4");
	}
}
