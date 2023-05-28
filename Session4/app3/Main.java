package app3;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
	
	public static void main(String[] args){
		Buffer b = new Buffer();
		Lock l = new ReentrantLock();
		Condition full = l.newCondition(); //full.await()at the consumer; full.signal() at producer when the list reaches 100 items
		
		Producer pro = new Producer(b);
		Consumer c1 = new Consumer(b,l);
		Consumer c2 = new Consumer(b,l);
		Consumer c3 = new Consumer(b,l);
		
		pro.start();
		c1.start(); 
		c2.start();
		c3.start();
		}

}
