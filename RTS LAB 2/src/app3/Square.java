package app3;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class Square extends Thread{
	int id;
	Window win;
	boolean active = true;
	boolean paused = false;
	
	Square(int id, int priority, Window w){
		this.id = id;
		this.win = w;
		this.setPriority(priority);
	}
	
	
	public long getId() {
		return this.id;
	}
	
	
	
	@SuppressWarnings("deprecation")
	public void run() {
		int c = 0;
		while(active) {
			if(paused) {
				synchronized(this) {
					try {
						wait();
					} catch(InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			c = (int) (c + Math.random() * 10);
			System.out.println("Thread " + id + " value: "+ c);
			try {Thread.sleep(500);} catch (InterruptedException e) {}
			this.win.setProgressValue(id, c);
			
			if(c > 100) {
				System.out.println("Thread " + id + " stopped");
				this.stop();
				
			}
			
		}
	}
	
	
	public void setPause(boolean p) {
		synchronized(this) {
			if(p == true) {
				paused = true;
			} else {
				paused = false;
				notify();
			}
		}
	}
	
	public boolean isPuased() {
		return paused;
	}
	/*public void setProgressValue(int id, int val) {
		squares.get(id).setLocation(squares.get(id).height,squares.get(id).width+val);
	}
	
	public void setProgressValue(int val) {
		((Rectangle) squares).setLocation(this.getWidth(),this.getHeight()+val);
	}
	
	public void addSquare(int id, int x, int y, int width, int heigth) {
		Rectangle rect = new Rectangle(x, y, width, heigth);
		squares.add(rect);
		this.id = id;
	}
	
	public Dimension getPreferredSize() {
		return new Dimension(PREF_W, PREF_H);
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		for(Rectangle rect : squares) {
		g2.draw(rect);
		}
	}*/

		
}
