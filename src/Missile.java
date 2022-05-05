import java.awt.Color;
import java.awt.Graphics;

public class Missile {
	
	private int x, y;
	private int width = 2, height = 50;
	private int ySpeed = -3;
	
	Missile(int x) {
		this.x = x;
		y = Window.HEIGHT - 100;
	}
	
	void show(Graphics graphics) {
		graphics.setColor(Color.white);
		graphics.fillRect(x, y, width, height);
	}
	void move() {
		y += ySpeed;
	}
	boolean checkBounds() {
		if(y <= 0) {
			return true;
		}	
		return false;
	}
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	
}
