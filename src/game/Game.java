package game;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.Toolkit;

import java.io.File;
import java.io.IOException;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.JPanel;


public class Game extends JPanel {

	
	// animation constants and objects
	private static final int FRAMES_PER_SECOND = 60;
	private static final int MS_TO_WAIT = 1000 / FRAMES_PER_SECOND;

	private Timer animationTimer = new Timer("Animation");
	private TimerTask animationTask = new AnimationUpdater();
	
	
	// ball constants and object
	private static final int Y_VELOCITY = 1;
	private static final int X_VELOCITY = 15;
	private Ball ball = new Ball(200, 0, X_VELOCITY, Y_VELOCITY, 10, 10);
	
	
	// player one and two constants and objects
	private static final int PLAYER_VY = 30;
	private static final int PLAYERONE_X = 40;
	private static final int PLAYERONE_Y = 250;
	private static final int PLAYERTWO_X = 1000; //remember to change
	private static final int PLAYERTWO_Y = 250;

	private Player playerOne = new Player(PLAYERONE_X, PLAYERONE_Y, 0, 0);
	private Player playerTwo = new Player(PLAYERTWO_X, PLAYERTWO_Y, 0, 0);
	
	
	// player one and player two score
	private int scoreOne = 0;
	private int scoreTwo = 0;

	
	// game status values
	public enum GameStatus { 
		MENU, MATCH, RESETBALL
	}
	
	private GameStatus gameStatus = GameStatus.MENU;
	
	
	// images
	private BufferedImage menu;
	private BufferedImage background;
	
	
	
	
	
	public void start() {
		animationTimer.schedule(animationTask, 0, MS_TO_WAIT);
	}
	

	
	
	
	private class AnimationUpdater extends TimerTask {
		
		public void run() {
			if (gameStatus == GameStatus.MATCH) {
				ball.moveBall();
			
			
				// walls restrictions
				int minY = ball.getHeight() / 2;
				int maxY = getHeight() - (ball.getHeight() / 2);

				int minX = 0 + ball.getWidth() / 2;
				int maxX = getWidth() - (ball.getWidth() / 2);

			
				// check collisions variables
				int ballminX = ball.getX() - (ball.getWidth() / 2);
				int ballmaxX = ball.getX() + (ball.getWidth() / 2);

				int ballminY = ball.getY() + (ball.getHeight() / 2);
				int ballmaxY = ball.getY() - (ball.getHeight() / 2);

			
				// check if the ball is out of bounds (wall collision)
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

			
				Random ballDirectionRandomizer = new Random();
				int directionRandomizer = ballDirectionRandomizer.nextInt(10) - 5; 
			
			
				// check collision with player one
				if (	playerOne.contains(ball.getX(), ballminY)
					|| 	playerOne.contains(ball.getX(), ballmaxY)
					|| 	playerOne.contains(ballminX, ball.getY())
					|| 	playerOne.contains(ballmaxX, ball.getY())) {
				
					ball.setVY(ball.getVY() + directionRandomizer);
					ball.setVX(-ball.getVX());
					Toolkit.getDefaultToolkit().beep();
				}

			
				// check collision with player two
				if (	playerTwo.contains(ball.getX(), ballminY)
					|| 	playerTwo.contains(ball.getX(), ballmaxY)
					||	playerTwo.contains(ballminX, ball.getY())
					|| 	playerTwo.contains(ballmaxX, ball.getY())) {
				
					ball.setVY(ball.getVY() + directionRandomizer);
					ball.setVX(-ball.getVX());
					Toolkit.getDefaultToolkit().beep();
				}

				repaint();
			}
		}
	}

	

	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		
		// initialize images and draw menu
		if (gameStatus == GameStatus.MENU) {
			try {                
				menu = ImageIO.read(new File("main1.png"));
			} catch (IOException ex) {
				return;
			}
					
			try {                
				background = ImageIO.read(new File("background.png"));
			} catch (IOException ex) {
				return;
			}
			
			g.drawImage(menu, 0, 0, null);
		}
		
		
		// draw match elements
		if (gameStatus == GameStatus.MATCH) { 
			g.drawImage(background, 0, 0, null);
		
			if (ball.getX() > getWidth() - 15) {
				g.setFont(new Font ("Monospaced", Font.BOLD,24));
				g.setColor(Color.BLACK);
				g.drawString("Player one scored!", 200, getHeight() / 2);
				g.drawString("Press ENTER to play again (attention player one).", 200, getHeight() / 2 + 50);
				
				gameStatus = GameStatus.RESETBALL;
				scoreOne++;
			} 
			else if (ball.getX() < 0 + 15) {
				g.setFont(new Font ("Monospaced",Font.BOLD,24));
				g.setColor(Color.BLACK);
				g.drawString("Player two scored!", 200, getHeight() / 2);
				g.drawString("Press ENTER to play again (attention player two).", 200, getHeight() / 2 + 50);
				
				gameStatus = GameStatus.RESETBALL;
				scoreTwo++;
			} else {
				g.setColor(Color.RED);		
				g.fillOval(ball.getX(), ball.getY(), ball.getWidth(), ball.getHeight());
		
				playerOne.paint(g);		
				playerTwo.paint(g);
				
				g.setColor(Color.ORANGE);
				g.setFont(new Font ("Monospaced", Font.BOLD, 30));
				g.drawString("" + scoreOne, 10, 25);
				g.drawString("" + scoreTwo, getWidth() - 20, 25);
			}
		}
		
		
		// reset ball position
		if (gameStatus == GameStatus.RESETBALL && ball.getVX() < 0) {
			ball.setX(getWidth() / 2 + 400);
			ball.setY(getHeight() / 2);
			ball.setVY(1);
		}
		
		if (gameStatus == GameStatus.RESETBALL && ball.getVX() > 0) {
			ball.setX(getWidth() / 2 - 400);
			ball.setY(getHeight() / 2);
			ball.setVY(1);
		}
	}
	
	
	
	

	// key listener
	private class GameKeysListener implements KeyListener {

		@Override
		public void keyPressed(KeyEvent e) {
			
			// star the match when enter is pressed
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				gameStatus = GameStatus.MATCH;
			}
			
			
			// move player one			
			if (e.getKeyCode() == KeyEvent.VK_W) {
				playerOne.setVY(-PLAYER_VY);
				playerOne.movePlayer();
			} 
			else if (e.getKeyCode() == KeyEvent.VK_S) {
				playerOne.setVY(PLAYER_VY);
				playerOne.movePlayer();
			}
			
			// move player two
			if (e.getKeyCode() == KeyEvent.VK_O) {
				playerTwo.setVY(-PLAYER_VY);
				playerTwo.movePlayer();
			} 
			else if (e.getKeyCode() == KeyEvent.VK_L) {
				playerTwo.setVY(PLAYER_VY);
				playerTwo.movePlayer();
			}
		
			// check position limitations for the 2 players
			if (playerOne.getY() < 0) {
				playerOne.setY(0);				
			} 
			else if (playerTwo.getY() < 0) {
				playerTwo.setY(0);				
			}
			else if (playerOne.getY() > getHeight() - playerOne.getHeight()) {
				playerOne.setY(getHeight() - playerOne.getHeight());				
			}
			else if (playerTwo.getY() > getHeight() - playerTwo.getHeight()) {
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

		setFocusable(true);
		addKeyListener(new GameKeysListener());
	}

	
}
