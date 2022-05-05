
import javax.swing.JFrame;

public class Window extends JFrame {
	private static final long serialVersionUID = 1L;
	static final int WIDTH = 650, HEIGHT = 800; 
	
	Window() {
		add(new Game());
		
		pack();
		setVisible(true);
		setResizable(false);
		setTitle("Space Invaders");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
}
