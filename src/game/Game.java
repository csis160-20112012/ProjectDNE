package game;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.util.Timer;
import java.util.TimerTask;


public class Game extends JPanel {
	
	
	// animation constants and objects
	private static final int FRAMES_PER_SECOND = 30;
	private static final int MS_TO_WAIT = 1000 / FRAMES_PER_SECOND;
		
	
	private Timer animationTimer = new Timer("Ball Animation");
	private TimerTask animationTask = new AnimationUpdater();
	
	
	public void start() {
        animationTimer.schedule(animationTask, 0, MS_TO_WAIT);
    }
	
	
	private class AnimationUpdater extends TimerTask {

        public void run() {
        	
        	ball.moveBall();
        	      	
        	//walls dimensions
            int minY = ball.getHeight() / 2;
            int maxY = getHeight() - (ball.getHeight() / 2);
            
            int minX = 0 + ball.getWidth() / 2;
            int maxX = getWidth() - (ball.getWidth() / 2);
            
         // check collisions with the bricks
        	int ballX = (int) ball.getX();
        	int ballY = (int) ball.getY();

        	int ballminX = (int) ball.getX() - (ball.getWidth() / 2);
        	int ballmaxX = (int) ball.getX() + (ball.getWidth() / 2);
        	
        	int ballminY = (int) ball.getY() + (ball.getHeight() / 2);
        	int ballmaxY = (int) ball.getY() - (ball.getHeight() / 2);

            //check if the ball is out of bounds
            if (ball.getY() > maxY) {
                ball.setY(maxY);
                ball.setVY(-ball.getVY());
            }
            else if (ball.getY() < minY) {
                ball.setY(minY);
                ball.setVY(-ball.getVY());
            }
            
            if (ball.getX() > maxX) {
                ball.setX(maxX);
                ball.setVX(-ball.getVX());
            }
            else if (ball.getX() < minX) {
                ball.setX(minX);
                ball.setVX(-ball.getVX());
            }
            
            //check collision with player one
            if( playerOne.contains(ballX, ballminY) || playerOne.contains(ballX, ballmaxY) ||
            		playerOne.contains(ballminX, ballY) || playerOne.contains(ballmaxX, ballY) ) 
            	{
            		ball.setY(playerOne.getY() - ball.getWidth()/2);
            		ball.setVY(-ball.getVY());
            		
            		ball.setX(playerOne.getX() - ball.getWidth()/2);
            		ball.setVX(-ball.getVX());

            		// beep after every paddle hit
            		Toolkit.getDefaultToolkit().beep();
            	}

            repaint();
        }

    }
	
	
	
	
	
	//ball constants and object
    private static final int INITIAL_Y_VELOCITY = 4;
    private static final int INITIAL_X_VELOCITY = 25;
    private static final double ACCELERATION = 0.2;
    private Ball ball = new Ball(200, 0, INITIAL_X_VELOCITY, INITIAL_Y_VELOCITY, 0, ACCELERATION, 7, 7);
	
	
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
              
        g.setColor(Color.RED);
        g.clearRect(0, 0, getWidth(), getHeight());

        int upperLeftX = (int) (ball.getX() - ball.getWidth()/2);
        int upperLeftY = (int) (ball.getY() - ball.getHeight()/2);
        g.fillOval(upperLeftX, upperLeftY, ball.getWidth(), ball.getHeight());
        
        playerOne.paint(g);
       
	}
    
    
    
    
    
    //player one
	private static final int PlAYERONE_VY = 8;
	private static final int PlAYERONE_X = 10;
	private static final int PlAYERONE_Y = 200;
	
	private PlayerOne playerOne = new PlayerOne(PlAYERONE_X, PlAYERONE_Y, 0, 0);
	
	
	
	
	
	//key listener
    private class GameKeysListener implements KeyListener {
    	
		@Override
		public void keyPressed(KeyEvent e) {

            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            	playerOne.setVY(-PlAYERONE_VY);
            }
            else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            	playerOne.setVY(PlAYERONE_VY);
            }
            else if (e.getKeyCode() == KeyEvent.VK_R) {
            	playerOne.setX(PlAYERONE_X);
            	playerOne.setY(PlAYERONE_Y);
            	playerOne.setVY(0);
            }
            
            playerOne.movePlayerOne();
		}

		@Override
        public void keyReleased(KeyEvent e) {
			playerOne.setVY(0);
        }

		@Override
		public void keyTyped(KeyEvent e) {
			// empty method, do not need to respond to this event
		}
    }
	


	
	
	
	
	public static void main(String[] args) {
		
		JFrame window = new JFrame();
        
        Game panel = new Game();
        
        window.add(panel);
        window.setSize(1200, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
        
        panel.start();

	}
	
	
}
