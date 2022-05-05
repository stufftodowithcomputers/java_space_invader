import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;
import javax.swing.ImageIcon;

public class Alien {
	
	private int x, y;
	private int width = 30, height = 30;
	private int ySpeed;
	
	Image image;
	Random random;
	Game game;
	
	Alien(Game game) {
		random = new Random();
		image = new ImageIcon("Space_invaders_alien.png").getImage();
		this.game = game;
		
		x = random.nextInt(Window.WIDTH-width);
		y = random.nextInt(500);
		y *= -1;
		ySpeed = random.nextInt(2 + 1 - 1) + 1;
	}
	
	void show(Graphics graphics) {
		graphics.drawImage(image, x, y, width, height, null);
	}
	void move() {
		y = y + ySpeed;
	}
	void checkBounds() {
		if(y + height >= Window.HEIGHT) {
			ySpeed=0;
			game.running = false;
		}
	}
	
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
	
}
