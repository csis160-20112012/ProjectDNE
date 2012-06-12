/**
 * Represents both player one and player two in the game
 */


package game;


import java.awt.Color;
import java.awt.Graphics;


public class Player {
	

	// position of the player
	private int x;
	private int y;

	// velocity of the player (when key is pressed)
	private int vx;
	private int vy;

	// player color
	private Color color = Color.BLACK;

	// player dimensions
	private int width = 10;
	private int height = 100;

	
	
	
	
	// default constructor
	public Player(int x, int y, int vx, int vy, int width, int height) {
		this.x = x;
		this.y = y;
		this.vx = vx;
		this.vy = vy;
		this.width = width;
		this.height = height;
	}

	// constructor with no dimension values
	public Player(int x, int y, int vx, int vy) {
		this.x = x;
		this.y = y;
		this.vx = vx;
		this.vy = vy;
	}

	
	
	
	
	// update player's position
	public void movePlayer() {
		this.x += vx;
		this.y += vy;
	}

	
	
	
	
	// paint player
	public void paint(Graphics g) {
		g.setColor(color);
		g.fillRect(x, y, width, height);
	}

	
	
	
	// used to check collision with the player
	public boolean contains(int x, int y) {
		int offsetX = x - getX();
		int offsetY = y - getY();

		if (offsetX >= 0 && offsetX <= width && offsetY >= 0
				&& offsetY <= height) {
			return true;
		} else {
			return false;
		}
	}

	
	
	
	
	// getters
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getVX() {
		return vx;
	}

	public int getVY() {
		return vy;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	public Color getColor() {
		return color;
	}

	
	
	
	
	// setters
	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setVX(int vx) {
		this.vx = vx;
	}

	public void setVY(int vy) {
		this.vy = vy;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}

	
}
