import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class ObstacleDerby {
	JFrame frame;
	public static final int WIDTH=500;
	public static final int HEIGHT=800;
	GamePanel g;
	ObstacleDerby(JFrame f){
		this.frame=f;
		this.g=new GamePanel();
	}
	void setup() {
		frame.add(g);
		frame.setSize(WIDTH,HEIGHT);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addKeyListener(g);
	}

}

