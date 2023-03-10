package try_app2;

import java.util.Observable;

public class Fir extends Observable implements Runnable {
     int id;
     Window win;
     int processorLoad;
     int c;
     Thread t;
     boolean active = true;
     boolean paused = false;
     Fir(int id,Window win, int procLoad){
              this.id=id;
              this.win=win;
              this.processorLoad=procLoad;
              Thread t = new Thread();
     }
     
     public void start() {
    	 if(t == null) {
    		 t = new Thread(this);
    		 t.start();
    	 }
     }
    public void run(){
    	while(active) {
    		if(paused) {
    			synchronized(this) {
    				try {
    					wait();
    				}catch ( InterruptedException e) {
    					e.printStackTrace();
    			}//synchronized
    		}//if
    			
             int cc=0;
             while(cc<1000){
                       //for(int j=0;j<this.processorLoad;j++){
                        //              j++;j--;
                        //   }
                cc++;
                c = cc;
                this.setChanged();
                this.notifyObservers();
              	}
             }
    					
        }
    	
    }
    public int getC() {
    	return c;
    }
}
