package app3;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Window extends JFrame{
	private ArrayList<Rectangle> squares = new ArrayList<Rectangle>();
	JPanel pane;
	
	Window(int nrThreads){
		//setTitle("app3");
		super("Game Frame"); //it's the same thing as setTitle
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400,500);
		
		pane = new JPanel();
		add(pane);
		
		init(nrThreads);
		
		
		//pack();
		setLocationRelativeTo(null);//that puts the frame in the center
		setVisible(true);
		
	}	
	
	public void init(int n) {
		for(int i = 0; i < n; i++) {
			Rectangle rect = new Rectangle(60 + i * 100, 45, 60, 60);
			squares.add(rect);
			
		}
		
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;
		for(Rectangle rect : squares) {
		g2.draw(rect);
		}
	}
	
	public Dimension getPreferredSize() {
		return new Dimension(300, 300);
	}
	
	public void setProgressValue(int id, int val) {
		squares.get(id).setLocation((int) squares.get(id).getX(), (int) (squares.get(id).getY()+val));
		repaint();
	}
	
}
