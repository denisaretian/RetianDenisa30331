package app3;

public class Main {
	
	private static final int NUMBERofTHREADS = 3;

	public static void main(String[] args) {
		
		Window w = new Window(NUMBERofTHREADS);
		for(int i = 0; i < NUMBERofTHREADS; i++) {
			new Square(i, i+2, w).start();
			
		}
		
		/*w.getContentPane().add(squares);
		for (int i = 0; i < 3; i++) {
	         squares.addSquare(i,55 + i * 150, 20, 100, 100);
	         Thread t = new Thread(new Squares());
	         t.start();
	      }
		w.pack();*/

	}

}
