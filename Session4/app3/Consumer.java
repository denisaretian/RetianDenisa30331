package app3;

import java.util.concurrent.locks.Lock;

public class Consumer extends Thread{
	
	private Buffer bf;
	private Lock l;
	
	Consumer(Buffer bf, Lock lock){
		this.bf=bf;
		this.l = lock;
		}
	
	public void run() {
		while (true){
			l.lock();
			System.out.println("The thread " + this.getName() + " acquired the lock");
			criticalRegion();
			System.out.println("Consumer "+this.getName() + " received >> "+bf.get());
			l.unlock();
			System.out.println("The thread " + this.getName() + " released the lock");
		}
	}
	
	public void criticalRegion() {
		System.out.println("IS EXECUTING THE CRITICAL REGION!");
		try {
		Thread.sleep(3000);
		} catch (InterruptedException e) {
		e.printStackTrace();
		}
		}

}
