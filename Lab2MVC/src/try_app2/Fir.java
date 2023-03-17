package try_app2;

import java.util.Observable;

@SuppressWarnings("deprecation")
public class Fir extends Observable implements Runnable {
	int id;
	Window win;
	int processorLoad;
	int cc;
	Thread t;
	boolean active = true;
	boolean paused = false;

	Fir(int id, int priority, Window win, int procLoad) {
		this.id = id;
		this.win = win;
		this.processorLoad = procLoad;
		this.t = new Thread(this);
		t.setPriority(priority);
	}

//	public void start() {
//		if (t == null) {
//			t = new Thread(this);
//			t.start();
//		}
//	}

//	public void run(){
//    	while(active) {
//    		if(paused) {
//    			/*synchronized(this) {
//    				try {
//    					wait();
//    				}catch ( InterruptedException e) {
//    					e.printStackTrace();
//    			}//synchronized
//    		}//if*/
//    			
//             int cc=0;
//             while(cc<1000){
//                       for(int j=0;j<this.processorLoad;j++){
//                                     j++;j--;
//                          }
//                cc++;
//                c = cc;
//                //this.win.update(null, cc);
//                this.setChanged();
//                this.notifyObservers();
//                
//              	}
//             //try {Thread.sleep(100);} catch (InterruptedException e) {}
//             }
//    					
//        }
//    	
//    }

	public void run() {
		int c = 0;
		while (c < 1000) {
			for (int j = 0; j < this.processorLoad; j++) {
				j++;
				j--;
			}
			c++;
			// this.win.setProgressValue(id, c);
			c = cc;
			this.setChanged();
			this.notifyObservers();
		}
	}

	public int getC() {
		return cc;
	}
	
	public int getId() {
		return id;
	}
}
