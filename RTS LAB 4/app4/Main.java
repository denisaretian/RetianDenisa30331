package app4;


public class Main {
	public static void main(String[] args) throws InterruptedException {
		Integer monitor1 = new Integer(1);
		Integer monitor2 = new Integer(2);
		MainThread t1 = new MainThread(monitor1, monitor2,7, 2, 3);
		ExecutionThread t2 = new ExecutionThread(monitor1,3, 5, t1);
		ExecutionThread t3 = new ExecutionThread(monitor2,4, 6, t1);
		
		t1.start();
		t2.start();
		t3.start();
	}
}