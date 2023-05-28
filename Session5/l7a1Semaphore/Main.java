package l7a1Semaphore;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
	public static void main(String[] args) {
		Semaphore s1 = new Semaphore(1);
        Semaphore s2 = new Semaphore(1);
		
		CyclicBarrier bar = new CyclicBarrier(2, new Runnable() {
			public void run() {
				System.out.println("Barrier runtime");
			}
		});
		
		new ExecutionThread(bar, s1, s2, 2, 4, 4, 6, 4).start();
		new ExecutionThread(bar, s2, s1,  3, 5, 5, 7, 5).start();
	}
}
