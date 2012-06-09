package game;


public class Ball {
	

	// position of the ball
	private double x;
	private double y;

	// velocity of the ball
	private double vx;
	private double vy;

	// acceleration of the ball
	private double ax;
	private double ay;

	// dimensions of the ball
	private int width = 8;
	private int height = 8;
	
	
	
	

	// default constructor
	public Ball(double x, double y, double vx, double vy, double ax, double ay, int width, int height) {
		this.x = x;
		this.y = y;
		this.vx = vx;
		this.vy = vy;
		this.ax = ax;
		this.ay = ay;
		this.width = width;
		this.height = height;
	}

	// constructor with no dimension values
	public Ball(double x, double y, double vx, double vy, double ax, double ay) {
		this.x = x;
		this.y = y;
		this.vx = vx;
		this.vy = vy;
		this.ax = ax;
		this.ay = ay;
	}

	
	
	
	
	// update ball's position and velocity
	public void moveBall() {
		this.x += vx;
		this.y += vy;

		this.vx += ax;
		this.vy += ay;
	}

	
	
	
	
	// getters
	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getVX() {
		return vx;
	}

	public double getVY() {
		return vy;
	}

	public double getAX() {
		return ax;
	}

	public double getAY() {
		return ay;
	}

	
	
	
	
	// setters
	public void setHeight(int height) {
		this.height = height;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public void setVX(double vx) {
		this.vx = vx;
	}

	public void setVY(double vy) {
		this.vy = vy;
	}

	public void setAX(double ax) {
		this.ax = ax;
	}

	public void setAY(double ay) {
		this.ay = ay;
	}

	
}