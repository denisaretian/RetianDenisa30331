package l7a4;

import java.util.concurrent.Semaphore;

public class Main {
	public static void main(String[] args) {
		Semaphore semaphore = new Semaphore(2);
		new ExecutionThread(semaphore, 4, 7, 3).start();
		new ExecutionThread(semaphore, 5, 7, 6).start();
		new ExecutionThread(semaphore, 3, 6, 5).start();
	}
}
