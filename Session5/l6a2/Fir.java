package l6a2;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Fir extends Thread {
	CyclicBarrier barrier;
	Calculator calculator;

	public Fir(CyclicBarrier barrier, Calculator c) {
		this.barrier = barrier;
		this.calculator = c;
	}

	public void run() {
		while (true) {
			activity();
			try {
				barrier.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (BrokenBarrierException e) {
				e.printStackTrace();
			}
			activity();
		}
	}

	public void activity() {
		int nb = (int)( Math.random()*20-10);
		System.out.println(this.getName()+ " " + nb);
		calculator.calc(nb);
		try {
			Thread.sleep(Math.round(Math.random() * 3 + 3) * 1000);
		} catch (InterruptedException e) {
		}

	}

}
