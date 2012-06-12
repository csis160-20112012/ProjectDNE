package game;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;


public class Bonus {
	
	
	// position of the bonus
	private int x;
	private int y;

	// dimensions of the bonus
	private int width = 100;
	private int height = 100;
	
	// color of the bonus
	private Color color;
	
	// bonus type
	private int type;
	
	// if the player hits the bonus the variable is set to true
	private boolean isHitted = true;
	
	
	
	
	
	// default constructor
	public Bonus(int x, int y, Color color, int width, int height, int type, boolean isHitted) {
		this.x = x;
		this.y = y;
		this.color = color;
		this.width = width;
		this.height = height;
		this.type = type;
		this.isHitted = isHitted;
	}
	
	// constructor with no dimension values
	public Bonus(int x, int y, Color color, int type, boolean isHitted) {
		this.x = x;
		this.y = y;
		this.color = color;
		this.type = type;
		this.isHitted = isHitted;
	}
	
	
	
	
	
	// paint bonus
	public void paint(Graphics g) {
		g.setColor(color);
		g.fillRect(x, y, width, height);
		g.setColor(color.WHITE);
		g.setFont(new Font ("Monospaced", Font.BOLD, 40));
		g.drawString("?", x + 40, y + 60);
	}
	
	
	
	
	
	// used to check collision with the bonus
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
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	public Color getColor() {
		return color;
	}
	
	public int getType() {
		return type;
	}
	
	public boolean getIsHitted() {
		return isHitted;
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
	
	public void setType(int type) {
		this.type = type;
	}
		
	public void setIsHitted(boolean isHitted) {
		this.isHitted = isHitted;
	}
	
	
}
