package try_app2;

public class Main {
	private static final int noOfThreads = 8;
	private static final int processorLoad = 1000000;
	Thread t; // because Fir class implements Runnable interface, we have to pass it to a
				// thread that the program will actually work

	public static void main(String args[]) {
		Window win = new Window(noOfThreads);
		Fir f;
		for (int i = 0; i < noOfThreads; i++) {
			// Thread t = new Thread();//
			f = new Fir(i, i + 2, win, processorLoad);
			f.addObserver(win);

			// Controller c = new Controller(f, win);
			f.t.start();
			/*
			 * try { Thread.sleep(100); } catch (InterruptedException e) { // TODO
			 * Auto-generated catch block e.printStackTrace(); }
			 */

		}
	}
}
