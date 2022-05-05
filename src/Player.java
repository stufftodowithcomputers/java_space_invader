import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Player {
	
	private int width = 40, height = 50;
	private int x = ((Window.WIDTH / 2) - width / 2), y = Window.HEIGHT - 100;
	private int xSpeed;
	
	Image image;
	
	Player() {
		image = new ImageIcon("space-invaders-ship.png").getImage();
	}
	
	void show(Graphics graphics) {
		graphics.drawImage(image, x, y, width, height, null);
	}
	void move() {
		x += xSpeed;
	}
	void checkBounds() {
		if(x + width >= Window.WIDTH) {
			setxSpeed(0);
			x -= 10;
		}
		if(x <= 0) {
			setxSpeed(0);
			x += 10;
		}
	}
	
	
	public void setxSpeed(int xSpeed) {
		this.xSpeed = xSpeed;
	}
	
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
}
