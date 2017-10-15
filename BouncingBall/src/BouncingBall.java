import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class BouncingBall extends JPanel{
	
	private int X;
	private int Y;
	private int radius;
	
	private int dx;
	private int dy;
	
	private boolean isRunning;
	
	private BouncingBall(int X, int Y, int radius, int dx, int dy) {
		this.X = X;
		this.Y = Y;
		this.radius = radius;
		
		this.dx=dx;
		this.dy=dy;
		
		isRunning=false;
	}
	
	public void letsBounce() {
		if(isRunning)
			return;
		Thread thread = new Thread() {
		      public void run() {
		    	  int width;
		    	  int height;
		    	  
		    	  while(true) {
		    		  width = getWidth();
		              height = getHeight();
		     
		              X = X + dx ;
		              Y = Y + dy;
		     
		              if (X - radius < 0) {
		                dx = -dx; 
		                X = radius; 
		              } else if (X + radius > width) {
		                dx = -dx;
		                X = width - radius;
		              }
		     
		              if (Y - radius < 0) {
		                dy = -dy;
		                Y = radius;
		              } else if (Y + radius > height) {
		                dy = -dy;
		                Y = height - radius;
		              }
		    		  
		    		  repaint();
		    		  
		    		  try {
			              Thread.sleep(10);
			          } catch (InterruptedException ex) {
			        	  ex.printStackTrace();
			          }  
		    	  }
		      }
		};
		thread.start();
	}
	
	public void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    g.setColor(Color.RED);
	    g.fillOval((int)(X-radius), (int)(Y-radius), (int)radius*2, (int)radius*2);
	}
	
	public static void main(String args[]) {
		JFrame frame = new JFrame();
		frame.setSize(500, 300);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		BouncingBall ball = new BouncingBall(frame.getWidth()/2, frame.getHeight()/2, 25, 5, 5);
		frame.setContentPane(ball);
		ball.letsBounce();
	}
}
