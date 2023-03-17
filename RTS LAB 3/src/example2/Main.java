package example2;

public class Main {
	
	public static void main(String[] args){
		Buffer b = new Buffer();
		
		Producer pro = new Producer(b);
		Consumer c1 = new Consumer(b);
		Consumer c2 = new Consumer(b);
		
		pro.start();
		c1.start(); 
		c2.start();
		}

}
