package l7a3;

import java.util.concurrent.CountDownLatch;

public class Main {
	public static void main(String[] args) throws InterruptedException {
		Integer monitor1 = new Integer(1);
		Integer monitor2 = new Integer(2);
		
		CountDownLatch latch = new CountDownLatch(3);
		
		MainThread t1 = new MainThread(latch, monitor1, monitor2,7, 2, 3);
		ExecutionThread t2 = new ExecutionThread(latch, monitor1,3, 5, t1);
		ExecutionThread t3 = new ExecutionThread(latch, monitor2,4, 5, t1);
		
		t1.start();
		t2.start();
		t3.start();
	}
}