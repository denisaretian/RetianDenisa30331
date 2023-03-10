package app2;

import java.util.Observable;

public class Fir extends Observable implements Runnable {
    int id;
    Window win;
    int processorLoad;
    Thread t;
    boolean active = true;
    boolean paused = false;
    int d;
    
    Fir(int id,int priority,Window win, int procLoad){
    	this.t = new Thread(this);
              this.id=id;
              this.win=win;
              this.processorLoad=procLoad;
     }
    
    
    //public void start(){
    //	if(t==null){ t = new Thread(this);
    //	t.start();
    //	}
    //}
    
     public void run(){
         int c = 0;
         
         while(c < 1000){
                   for(int j=0;j<this.processorLoad;j++){
                                  j++;
                                  j--;
                   }
                   c++;
                   d = c;
              this.setChanged();
              this.notifyObservers();
         }
     }

	public void setPause(boolean p){
		synchronized (this) {
			if(p==true){
				paused = true;
			}else{
				paused = false;
				notify();
			}
		}
	}
	
	public int getId() {
		return this.id;
		}
	
	public Thread getThread() {
		return t;
	}
	
	public int getD() {
		return d;
	}
	
}