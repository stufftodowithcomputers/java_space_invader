import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Game extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	final int DELAY = 10;
	boolean running;
	int points = 0;
	
	Timer timer;
	Image image;
	
	ArrayList<Missile> missiles = new ArrayList<>();
	ArrayList<Alien> aliens = new ArrayList<>();
	Player player = new Player();
	
	Game() {
		image = new ImageIcon("space-invaders-logo.png").getImage();
		
		setPreferredSize(new Dimension(Window.WIDTH, Window.HEIGHT));
		setBackground(Color.black);
		setFocusable(true);
		addKeyListener(new KeyEvents());
		
		for(int i=0; i < 10; i++) {
			aliens.add(new Alien(this));
		}
		
		start();
	}
	
	void start() {
		running = true;
		timer = new Timer(DELAY, this);
		timer.start(); 
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(new ImageIcon("back.gif").getImage(), 0, 0, Window.WIDTH, Window.HEIGHT, null);
		
		if(running) {
			
			g.setColor(Color.white);
			g.drawString("Points: "+points, 25, 25);
			
			for(int i=0; i < aliens.size(); i++) {
				aliens.get(i).show(g);
			}
			
			for(int i = 0; i<missiles.size(); i++) {
				missiles.get(i).show(g);
			}
			player.show(g);			
		} else {
			g.setFont(new Font("Sans Serif", Font.BOLD, 35));
			FontMetrics metrics = getFontMetrics(g.getFont());
			
			g.drawImage(image, 0, 0, Window.WIDTH, 200, null);
			
			g.setColor(Color.WHITE);
			g.drawString("Press (space) to start again!", (Window.WIDTH - metrics.stringWidth("Press (space) to start again!")) / 2, 300);
		
			g.setColor(Color.GREEN);
			g.drawString("Scored: "+points+" points!", (Window.WIDTH - metrics.stringWidth("Scored: "+points+" points!")) / 2, 450);
		}
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// Loop
		if(running) {
			for(int i=0; i < aliens.size(); i++) {
				aliens.get(i).move();
				aliens.get(i).checkBounds();
			}
			for(int i = 0; i < missiles.size(); i++) {
				missiles.get(i).move();
				if(missiles.get(i).checkBounds()) {
					missiles.remove(i);
				}
			}
			player.move();
			player.checkBounds();
			
			checkCollisions();
		} 		
		repaint();
	}
	
	void checkCollisions() {
		for(int i = 0; i < aliens.size(); i++) {
			for(int j = 0; j < missiles.size(); j++) {
				if(aliens.get(i).getX() <= missiles.get(j).getX() && missiles.get(j).getX() <= aliens.get(i).getX() + aliens.get(i).getWidth()) {
					if(aliens.get(i).getY() >= missiles.get(j).getY() && aliens.get(i).getY() + aliens.get(i).getHeight() >= missiles.get(j).getY()) {
						aliens.remove(i);
						missiles.remove(j);
						
						aliens.add(new Alien(this));
						points++;
					}
				}
			
			}
		}
	}
	
	void newMissile() {
		missiles.add(new Missile(player.getX() + player.getWidth() / 2));
	}
	
	void resetGame() {
		for(int i = 0; i < aliens.size(); i++) {
			aliens.set(i, new Alien(this));
		}
		missiles.clear();
		
		points = 0;
		running = true;
	}
	
	public class KeyEvents extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			if(running) {
				if(e.getKeyCode() == KeyEvent.VK_D) {
					player.setxSpeed(5);
				}
				if(e.getKeyCode() == KeyEvent.VK_A) {
					player.setxSpeed(-5);
				}
				if(e.getKeyChar() == ' ') {
					newMissile();
				}
			} else {
				if(e.getKeyChar() == ' ') {					
					resetGame();
				}
			}
			
		}
		
		@Override
		public void keyReleased(KeyEvent e) {
			if(running) {
				if(e.getKeyCode() == KeyEvent.VK_D) {
					player.setxSpeed(0);
				}
				if(e.getKeyCode() == KeyEvent.VK_A) {
					player.setxSpeed(0);
				}
			}
		}
	}
}
