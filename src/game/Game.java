package game;


import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.util.Timer;
import java.util.TimerTask;


public class Game extends JPanel {
	
	
	// animation constants and objects
	private static final int FRAMES_PER_SECOND = 60;
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
