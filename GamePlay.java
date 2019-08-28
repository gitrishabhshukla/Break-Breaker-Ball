package brickBricker;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;

import javax.swing.JPanel;

public class GamePlay extends JPanel implements KeyListener , ActionListener {

	private boolean play = false;
	private int score = 0;
	private int playerPos = 210;
	private Timer timer;
	private int totalBricks = 21;
	private int delay = 8;
	private int ballposX = 120;
	private int ballposY = 310;
	private int ballXdir = -1;
	private int ballYdir = -2;
	
	private MapGenerator map;
	
	public GamePlay() {
		map = new MapGenerator(3,7);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay,this);
		timer.start();
	}
	
	public void paint(Graphics g) {
		// Rectangle
		g.setColor(Color.black);
		g.fillRect(1, 1, 700, 592);
		
		////////// Map
		map.draw((Graphics2D)g);
		
		/// score
		g.setColor(Color.white);
		g.setFont(new Font("serif",Font.BOLD,25));
		g.drawString("Score: "+ score, 530, 30);
		
		// BORDERS
		g.setColor(Color.yellow);
		g.fillRect(0, 0, 3, 592);
		g.fillRect(0, 0, 692, 3);
		g.fillRect(682, 0, 3, 592);
		
		// ball
		g.setColor(Color.yellow);
		g.fillOval(ballposX,ballposY,15,15);
		
		// paddle
		g.setColor(Color.green);
		g.fillRect(playerPos,550,100,20);
		
		if(ballposY > 570) {
			play = false;
			ballXdir = 0;
			ballYdir = 0;
			
			g.setColor(Color.red);
			g.setFont(new Font("serif",Font.BOLD,30));
			g.drawString("Game Over , Your Score:"+ score , 170, 300);
			
			//score =0;
			
			g.setColor(Color.red);
			g.setFont(new Font("serif",Font.BOLD,30));
			g.drawString("Enter To Restart" , 220, 350);
			
		}
		
		if(totalBricks <= 0) {
			play = false;
			ballXdir = 0;
			ballYdir = 0;
			
			g.setColor(Color.red);
			g.setFont(new Font("serif",Font.BOLD,30));
			g.drawString("Game Won" , 230, 300);
			
			score =0;
			
			g.setColor(Color.red);
			g.setFont(new Font("serif",Font.BOLD,30));
			g.drawString("Enter To Restart" , 220, 350);
			
		}
		
		g.dispose();
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
        timer.start();
        
        if(play) {
        	
        	if(new Rectangle(ballposX,ballposY,20,20).intersects(new Rectangle(playerPos,550,80,20))) {
        		ballYdir = -ballYdir;
        	}
        	
        	A: for(int i=0;i<map.map.length;i++) {
   			       for(int j=0;j<map.map[0].length;j++) {
   				       if( map.map[i][j] > 0) {
   				    	   int brickX = j*map.brickWidth + 80;
   				    	   int brickY = i*map.brickHeight + 50;
   				    	   int brickWidth = map.brickWidth;
   				    	   int brickHeight = map.brickHeight;
   				    	   
   				    	   Rectangle rect = new Rectangle(brickX,brickY,brickWidth,brickHeight);
   				    	   Rectangle ballrect = new Rectangle(ballposX,ballposY,20,20);
   				    	   Rectangle brickrect = rect;
   				    	   
   				    	   if(ballrect.intersects(brickrect)) {
   				    		   map.setBrickValue(0, i, j);
   				    		   totalBricks--;
   				    		   score += 5;
   				    		   
   				    		   if(ballposX + 19 < brickrect.x || ballposX + 1>brickrect.x + brickWidth) {
   				    			   ballXdir = - ballXdir;
   				    		   } else {
   				    			   ballYdir = -ballYdir;
   				    		   }
   				    		   
   				    		   break A;
   				    	   }
   				       }
   			  }
   		     }
        	
        	ballposX += ballXdir;
        	ballposY += ballYdir;
        	if(ballposX < 0) {
        		ballXdir = -ballXdir;
        	}
        	if(ballposY < 0) {
        		ballYdir = -ballYdir;
        	}
        	if(ballposX > 670) {
        		ballXdir = -ballXdir;
        	}
        }
        
		repaint();
		
		
	}

	@Override
	public void keyTyped(KeyEvent e) {}
	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_RIGHT ) {
			if(playerPos>=600) {
				playerPos = 600;
			} else {
				moveToRight();
			}
		}
		
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			if(playerPos < 10) {
				playerPos = 10;
			} else {
				moveToLeft();
			}
		}
		
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			play = true;
			ballXdir = -1;
			ballYdir = -2;
			ballposX = 120;
			ballposY = 250;
			playerPos = 320;
			totalBricks = 21;
			map = new MapGenerator(3,7);
			
			repaint();
		}
		
	}

   public void moveToRight() {
	   play = true;
	   playerPos += 30;
   }
   public void moveToLeft() {
	   play = true;
	   playerPos -= 30;
   }
	

}
