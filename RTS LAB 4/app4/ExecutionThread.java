package app4;

public class ExecutionThread extends Thread {
	Integer monitor;
	int sleep = 0, activity_min, activity_max;
	Thread t;

	public ExecutionThread(Integer i, int activity_min, int activity_max, Thread t) {
		this.monitor = i;
		this.activity_min = activity_min;
		this.activity_max = activity_max;
		this.t = t;
	}

	public void run() {
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
		if (this.t != null) {
			try {
				t.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
