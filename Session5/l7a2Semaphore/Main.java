package l7a2Semaphore;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class Main {
	public static void main(String[] args) {
		Semaphore s1 = new Semaphore(1);
        Semaphore s2 = new Semaphore(1);
		
		CyclicBarrier bar = new CyclicBarrier(3, new Runnable() {
			public void run() {
				System.out.println("Barrier runtime");
			}
		});
		new ExecutionThread(bar, s1, 2, 4, 4).start();
		new ExecutionThread(bar, s1, s2, 3, 6, 3).start();
		new ExecutionThread(bar, s2, 2, 5, 5).start();
	}
}
