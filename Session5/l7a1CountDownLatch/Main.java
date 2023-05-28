package l7a1CountDownLatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
	public static void main(String[] args) {
		ReentrantLock l1 = new ReentrantLock();
		ReentrantLock l2 = new ReentrantLock();

		CountDownLatch latch = new CountDownLatch(2);

		new ExecutionThread(latch, l1, l2, 2, 4, 4, 6, 4).start();
		new ExecutionThread(latch, l2, l1, 3, 5, 5, 7, 5).start();
	}
}
