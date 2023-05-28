package l7a2ReentrantLock;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
	public static void main(String[] args) {
		ReentrantLock r1 = new ReentrantLock();
		ReentrantLock r2 = new ReentrantLock();
		
		CyclicBarrier bar = new CyclicBarrier(3, new Runnable() {
			public void run() {
				System.out.println("Barrier runtime");
			}
		});
		new ExecutionThread(bar, r1, 2, 4, 4).start();
		new ExecutionThread(bar, r1, r2, 3, 6, 3).start();
		new ExecutionThread(bar, r2, 2, 5, 5).start();
	}
}
