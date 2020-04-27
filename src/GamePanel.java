import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.Timer;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import java.util.concurrent.TimeUnit;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Font font=new Font("Helvetica",48,24);
	TimeUnit timer;
	final int MENU=0;
	final int GAME=1;
	final int END=2;
	int currentState=MENU;
	int score=0;
	int numberOfTimes;
	QuestionManager manager=new QuestionManager(this);
	Graphics graphic;
	
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;	
	Timer gameTimer=new Timer();
	JFrame frame;
	GamePanel(JFrame f){
		frame=f;
	}
	void loadImage(String imageFile) {
	    if (needImage) {
	        try {
	            image = ImageIO.read(this.getClass().getResourceAsStream(imageFile));
		    gotImage = true;
	        } catch (Exception e) {
	            
	        }
	        needImage = false;
	    }
	    }
	
	void draw(Graphics g,int x, int y, int width, int height) {
        if (gotImage) {
        	g.drawImage(image, x, y, width, height, null);
        } else {
        	g.setColor(Color.BLUE);
        	g.fillRect(x, y, width, height);
        }
	}
	
	void drawMenuState(Graphics g) {
		g.setColor(new Color(200,100,0));
		g.fillRect(0, 0, QuizWhiz.WIDTH, QuizWhiz.HEIGHT);
		g.setColor(Color.WHITE);
		g.setFont(font);
		g.drawString("Quiz Whiz", 100, 100);
		g.drawString("Press ENTER to play", 100, 400);
		g.drawString("Press SPACE for instructions", 100, 600);
	}
	
	public static BufferedImage rotate (BufferedImage img ){
	    int width  = img.getWidth();
	    int height = img.getHeight();
	    BufferedImage newImage = new BufferedImage( height, width, img.getType() );
	    for( int i=0 ; i < width ; i++ )
	        for( int j=0 ; j < height ; j++ )
	            newImage.setRGB( height-1-j, i, img.getRGB(i,j) );
	    return newImage;
	}
	
	void drawEndState(Graphics g) {
		g.setColor(new Color(0,200,0));
		g.fillRect(0, 0, QuizWhiz.WIDTH, QuizWhiz.HEIGHT);
		g.setColor(Color.WHITE);
		g.drawString("You win!", 100, 100);
		loadImage("trophy.png");
		draw(g,200,200,100,100);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("j");
		if(arg0.getSource()==button&&button.getText().equals("Next Question")) {
			facilitateGame(graphic);
		}
	}
	
	void facilitateGame(Graphics g) {
		manager.chooseNewQuestion();
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getKeyCode()==KeyEvent.VK_ENTER&&currentState==GAME) {
			manager.checkQuestion(manager.gameQuestions.get(manager.gameQuestions.size()-1));
		}
		if(arg0.getKeyCode()==KeyEvent.VK_SPACE) {
			JOptionPane.showMessageDialog(null, "The objective of the game is to answer as many questions as possible in a row. If you get a streak of 3 questions in a row, you win. You can press '+' to get a hint");
		}
		if(arg0.getKeyCode()==KeyEvent.VK_PLUS&&currentState==GAME) {
			manager.giveHint(manager.gameQuestions.get(manager.gameQuestions.size()-1).answer);
		}
		if(arg0.getKeyCode()==KeyEvent.VK_ENTER) {
			if(currentState==MENU||currentState==GAME) {
				currentState++;
			}
			else if(currentState==END) {
				currentState=MENU;
			}
		}
		repaint();
	}
	
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public void adjustButton(JButton button,String text) {
		button.setText(text);
		button.addActionListener(this);
		frame.setSize(500,800);
		this.add(button);
		this.setVisible(true);
	}
	
	@Override
	public void paint(Graphics g){
		if(currentState == MENU){
		    drawMenuState(g);
		}
		else if(currentState == GAME){
			System.out.println("hi");
		    drawGameState(g);
		}
		else if(currentState == END&&score>=15){
		    drawEndState(g);
		}
	}
	
	void drawGameState(Graphics g) {
		g.setColor(Color.CYAN);
		g.fillRect(0, 0, QuizWhiz.WIDTH, QuizWhiz.HEIGHT);
		facilitateGame(g);
		manager.showNewQuestion();
	}
	
}