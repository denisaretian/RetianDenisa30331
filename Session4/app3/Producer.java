package app3;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class Producer implements Runnable{
	
	private Buffer bf;
	private Thread thread;
	private Lock l;
	
	
	Producer(Buffer bf){
		this.bf=bf;
		//this.l = l;
		}
	
	public void start(){
		if (thread==null){
			thread = new Thread(this);
			thread.start();
		}
	}
	
	public void run(){
		
		while (true){
			this.l.lock();
			System.out.println("The Producer acquired the lock");
			criticalRegion();
			bf.put(Math.random());
			System.out.println("Producer " + thread.getName() + " gave a task.");
			try{
				Thread.sleep(1000);
			}catch(Exception e){e.printStackTrace();}
			l.unlock();
			System.out.println("The Producer released the lock");
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
