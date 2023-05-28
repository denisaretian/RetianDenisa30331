package l7a1;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
	public static void main(String[] args) {
		ReentrantLock l1 = new ReentrantLock();
		ReentrantLock l2 = new ReentrantLock();
		
		CyclicBarrier bar = new CyclicBarrier(3, new Runnable() {
			public void run() {
				System.out.println("Barrier runtime");
			}
		});
		while(true) {
		
		new ExecutionThread(bar, l1, l2, 2, 4, 4, 6, 4).start();
		new ExecutionThread(bar, l2, l1,  3, 5, 5, 7, 5).start();
		try {
			bar.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		bar.reset();
		}
	}
}
