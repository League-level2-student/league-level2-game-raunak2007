import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
public class GamePanel extends JPanel implements ActionListener {
	final int MENU=0;
	final int GAME=1;
	final int END=2;
	int currentState=MENU;
	int score=0;
	Graphics graphic;
	GamePanel(){
		
	}
	protected void paintComponent(Graphics g) {
		if(currentState==MENU) {
			drawMenuState(g);
		}
		if(currentState==GAME) {
			
		}
		if(currentState==END) {
			
		}
	}
	void drawMenuState(Graphics g) {
		g.setColor(new Color(200,100,0));
		g.fillRect(0, 0, QuizWhiz.WIDTH, QuizWhiz.HEIGHT);
	}
	void drawGameState(Graphics g) {
		g.setColor(new Color(200,100,0));
		g.fillRect(0, 0, QuizWhiz.WIDTH, QuizWhiz.HEIGHT);
	}
	void drawEndState(Graphics g) {
		g.setColor(new Color(200,100,0));
		g.fillRect(0, 0, QuizWhiz.WIDTH, QuizWhiz.HEIGHT);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(currentState==MENU) {
			drawMenuState(graphic);
		}
		if(currentState==GAME) {
			drawGameState(graphic);
		}
		if(currentState==END) {
			drawEndState(graphic);
		}
		repaint();
	}
	
}
