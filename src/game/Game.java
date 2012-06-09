package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.util.Random;
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
			
			if (canIStart==true) {
			ball.moveBall();
			
			// walls dimensions
			int minY = ball.getHeight() / 2;
			int maxY = getHeight() - (ball.getHeight() / 2);

			int minX = 0 + ball.getWidth() / 2;
			int maxX = getWidth() - (ball.getWidth() / 2);

			// check collisions variables
			int ballX = (int) ball.getX();
			int ballY = (int) ball.getY();

			int ballminX = (int) ball.getX() - (ball.getWidth() / 2);
			int ballmaxX = (int) ball.getX() + (ball.getWidth() / 2);

			int ballminY = (int) ball.getY() + (ball.getHeight() / 2);
			int ballmaxY = (int) ball.getY() - (ball.getHeight() / 2);

			// check if the ball is out of bounds (wall collisions)
			if (ball.getY() > maxY) {
				ball.setY(maxY);
				ball.setVY(-ball.getVY());
			} else if (ball.getY() < minY) {
				ball.setY(minY);
				ball.setVY(-ball.getVY());
			}

			if (ball.getX() > maxX) {
				ball.setX(maxX);
				ball.setVX(-ball.getVX());
			} else if (ball.getX() < minX) {
				ball.setX(minX);
				ball.setVX(-ball.getVX());
			}

			
			
			Random YRandomizer = new Random();
			int RandomY = YRandomizer.nextInt(10) - 5; 
			
			// check collision with player one
			if (playerOne.contains(ballX, ballminY)
					|| playerOne.contains(ballX, ballmaxY)
					|| playerOne.contains(ballminX, ballY)
					|| playerOne.contains(ballmaxX, ballY)) {
				//ball.setY(playerOne.getY() - ball.getWidth() / 2);
				ball.setVY(ball.getVY() + RandomY);

				//ball.setX(playerOne.getX() - ball.getWidth() / 2);
				ball.setVX(-ball.getVX());

				// beep after every paddle hit
				Toolkit.getDefaultToolkit().beep();
			}

			// check collision with player two
			if (playerTwo.contains(ballX, ballminY)
					|| playerTwo.contains(ballX, ballmaxY)
					|| playerTwo.contains(ballminX, ballY)
					|| playerTwo.contains(ballmaxX, ballY)) {
				//ball.setY(playerTwo.getY() - ball.getWidth() / 2);
				ball.setVY(ball.getVY() + RandomY);

				//ball.setX(playerTwo.getX() - ball.getWidth() / 2);
				ball.setVX(-ball.getVX());

				// beep after every paddle hit
				Toolkit.getDefaultToolkit().beep();
			}

			repaint();
		}
		}

	}

	// ball constants and object
	private static final int INITIAL_Y_VELOCITY = 1;
	private static final int INITIAL_X_VELOCITY = 10;
	private static final double ACCELERATION = 0;
	private Ball ball = new Ball(200, 0, INITIAL_X_VELOCITY, INITIAL_Y_VELOCITY, 0, ACCELERATION, 10, 10);

	
	public boolean canIStart = true;
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
			
		
		if (ball.getX() > getWidth() - 15) {		
			g.setFont (new Font ("Monospaced", Font.BOLD,24));
			g.drawString("Player one won. Player two sucks.", 200, getHeight() / 2);
			canIStart = false;
		} else if (ball.getX() < 0 + 15) {
			g.setFont (new Font ("Monospaced",Font.BOLD,24));
			g.drawString("Player two lost. Player one is allowed to make fun of player two.", 200, getHeight() / 2);
			canIStart = false;
		}
		else {
		g.setColor(Color.RED);
		g.clearRect(0, 0, getWidth(), getHeight());

		int upperLeftX = (int) (ball.getX() - ball.getWidth() / 2);
		int upperLeftY = (int) (ball.getY() - ball.getHeight() / 2);
		g.fillOval(upperLeftX, upperLeftY, ball.getWidth(), ball.getHeight());

		
		playerOne.paint(g);		
		playerTwo.paint(g);
		
		g.setColor(Color.WHITE);
		g.drawString("1", playerOne.getX() + 1, playerOne.getY() + 50);
		g.drawString("2", playerTwo.getX() + 1, playerTwo.getY() + 50);
		}
		
	}

	// player one and two
	private static final int PlAYER_VY = 30;
	private static final int PlAYERONE_X = 40;
	private static final int PlAYERONE_Y = 250;
	private static final int PlAYERTWO_X = 1130;
	private static final int PlAYERTWO_Y = 250;

	private Player playerOne = new Player(PlAYERONE_X, PlAYERONE_Y, 0, 0);
	private Player playerTwo = new Player(PlAYERTWO_X, PlAYERTWO_Y, 0, 0);

	// key listener
	private class GameKeysListener implements KeyListener {

		// move player one and two
		@Override
		public void keyPressed(KeyEvent e) {

			if (e.getKeyCode() == KeyEvent.VK_W) {
				playerOne.setVY(-PlAYER_VY);
				playerOne.movePlayer();
			} else if (e.getKeyCode() == KeyEvent.VK_S) {
				playerOne.setVY(PlAYER_VY);
				playerOne.movePlayer();
			} else if (e.getKeyCode() == KeyEvent.VK_R) {
				playerOne.setX(PlAYERONE_X);
				playerOne.setY(PlAYERONE_Y);
				playerOne.setVY(0);
				playerOne.movePlayer();
			}

			if (e.getKeyCode() == KeyEvent.VK_UP) {
				playerTwo.setVY(-PlAYER_VY);
				playerTwo.movePlayer();
			} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				playerTwo.setVY(PlAYER_VY);
				playerTwo.movePlayer();
			} else if (e.getKeyCode() == KeyEvent.VK_R) {
				playerTwo.setX(PlAYERONE_X);
				playerTwo.setY(PlAYERONE_Y);
				playerTwo.setVY(0);
				playerTwo.movePlayer();
			}
		
		// check position limitations for the 2 players
			if (playerOne.getY() < 0) {
				playerOne.setY(0);				
			}
			
			if (playerTwo.getY() < 0) {
				playerTwo.setY(0);				
			}
			
			if (playerOne.getY() > getHeight() - playerOne.getHeight()) {
				playerOne.setY(getHeight() - playerOne.getHeight());				
			}
			
			if (playerTwo.getY() > getHeight() - playerTwo.getHeight()) {
				playerTwo.setY(getHeight() - playerTwo.getHeight());				
			}
			
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

	public Game() {
		super();

		// setup game keys listener
		setFocusable(true);
		addKeyListener(new GameKeysListener());
	}


}
