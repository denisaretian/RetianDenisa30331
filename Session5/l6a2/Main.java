package l6a2;

import java.util.concurrent.CyclicBarrier;

public class Main {

	private static boolean stopThreads = false;

	public static void main(String[] args) {
		CyclicBarrier bar = new CyclicBarrier(3, new Runnable() {
			public void run() {
				System.out.println("Barrier runtime");
			}
		});
		FileService service = new FileService("output.txt");
		Calculator calc = new Calculator(service);
		Fir fir1 = new Fir(bar, calc);
		Fir fir2 = new Fir(bar, calc);
		Fir fir3 = new Fir(bar, calc);
		fir1.start();
		fir2.start();
		fir3.start();

	}

	public static boolean isStopThreads() {
		return stopThreads;
	}

}
