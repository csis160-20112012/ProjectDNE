/**
 * Represents the ball in the game
 */


package game;


public class Ball {
	

	// position of the ball
	private int x;
	private int y;

	// velocity of the ball
	private int vx;
	private int vy;

	// dimensions of the ball
	private int width = 8;
	private int height = 8;
	
	
	
	

	// default constructor
	public Ball(int x, int y, int vx, int vy, int width, int height) {
		this.x = x;
		this.y = y;
		this.vx = vx;
		this.vy = vy;
		this.width = width;
		this.height = height;
	}

	// constructor with no dimension values
	public Ball(int x, int y, int vx, int vy) {
		this.x = x;
		this.y = y;
		this.vx = vx;
		this.vy = vy;
	}

	
	
	
	
	// update ball's position and velocity
	public void moveBall() {
		this.x += vx;
		this.y += vy;
	}

	
	
	
	
	// getters
	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

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

	
	
	
	
	// setters
	public void setHeight(int height) {
		this.height = height;
	}

	public void setWidth(int width) {
		this.width = width;
	}

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

	
}