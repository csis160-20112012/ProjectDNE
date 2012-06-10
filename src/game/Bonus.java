package game;


import java.awt.Color;
import java.awt.Graphics;


public class Bonus {
	
	
	// position of the bonus
	private int x;
	private int y;

	// dimensions of the bonus
	private int width = 15;
	private int height = 15;
	
	// color of the bonus
	private Color color;
	
	// visibility of the bonus
	private boolean isVisible = false;
	
	
	
	
	
	// default constructor
	public Bonus(int x, int y, Color color, int width, int height, boolean isVisible) {
		this.x = x;
		this.y = y;
		this.color = color;
		this.width = width;
		this.height = height;
		this.isVisible = isVisible;
	}
	
	// constructor with no dimension values
	public Bonus(int x, int y, Color color, boolean isVisible) {
		this.x = x;
		this.y = y;
		this.color = color;
		this.isVisible = isVisible;
	}
	
	
	
	
	
	// paint bonus
	public void paint(Graphics g) {
		g.setColor(color);
		g.fillOval(x, y, width, height);
	}
	
	
	
	
	
	// getters
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
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
	
	public boolean getVisibility() {
		return isVisible;
	}
	
	
	
	
	
	// setters
	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
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
	
	public void setVisibility(boolean isVisible) {
		this.isVisible = isVisible;
	}
	
	
}
