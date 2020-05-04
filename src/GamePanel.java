import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.Timer;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
	JButton button=new JButton();
	JButton button2=new JButton();
	JButton button3=new JButton();
	JButton button4=new JButton();
	JButton button5=new JButton();
	JTextField field= new JTextField();
	JTextPane pane= new JTextPane();
	TimeUnit timer;
	final int MENU=0;
	final int GAME=1;
	final int END=3;
	final int QUESTION=2;
	int currentState=MENU;
	int score=0;
	int numberOfTimes;
	QuestionManager manager=new QuestionManager(this);
	ImageIcon imageIcon=new ImageIcon();
	Timer gameTimer=new Timer();
	JFrame frame=new JFrame();
	Image img = Toolkit.getDefaultToolkit().getImage("E:\\trophy.png");
	Graphics g;
	GamePanel(){
		drawMenuState(g);
	}
	/*void loadImage(String imageFile) {
	    if (needImage) {
	        try {
	            image = ImageIO.read(this.getClass().getResourceAsStream(imageFile));
		    gotImage = true;
	        } catch (Exception e) {
	            
	        }
	        needImage = false;
	    }
	    }
	*/
	/*void draw(Graphics g,int x, int y, int width, int height) {
        if (gotImage) {
        	g.drawImage(image, x, y, width, height, null);
        } else {
        	g.setColor(Color.BLUE);
        	g.fillRect(x, y, width, height);
        }
	}
	*/
	void drawMenuState(Graphics g) {
		this.setBackground(new Color(200,100,0));
		g.setColor(new Color(200,100,0));
		g.drawString("Quiz Whiz", 100, 100);
		button=new JButton();
		button.setText("Next");
		button.setLocation(100, 700);
		this.add(button);
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
		this.setBackground(Color.GREEN);
		//g.setColor(new Color(0,200,0));
		//g.fillRect(0, 0, QuizWhiz.WIDTH, QuizWhiz.HEIGHT);
		g.setColor(Color.WHITE);
		imageIcon.setImage(img);
		g.drawString("You win!", 100, 100);
		//loadImage("trophy.png");
		//draw(g,200,200,100,100);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("j");
		if(arg0.getSource()==button&&button.getText().equals("Next")&&currentState==MENU||currentState==QUESTION) {
			currentState++;
		}
		else if(arg0.getSource()==button&&button.getText().equals("Move On")&&currentState==GAME) {
			currentState=QUESTION;
		}
		repaint();
	}
	
	void facilitateGame(Graphics g) {
		manager.chooseQuestion(button);
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getKeyCode()==KeyEvent.VK_SPACE) {
			JOptionPane.showMessageDialog(null, "The objective of the game is to answer as many questions as possible in a row. If you get a streak of 3 questions in a row, you win. You can press '+' to get a hint");
		}
		if(arg0.getKeyCode()==KeyEvent.VK_PLUS&&currentState==GAME) {
			manager.giveHint(manager.gameQuestions.get(manager.gameQuestions.size()-1).answer);
		}
		if(arg0.getKeyCode()==KeyEvent.VK_ENTER) {
			if(currentState==MENU||currentState==GAME) {
				currentState++;
				System.out.println("l");
			}
			else if(currentState==END) {
				currentState=MENU;
			}
		}
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
	public void paintComponent(Graphics g){
		/*if(currentState == MENU){
		    drawMenuState();
		}
		
	else */if(currentState == GAME){
			System.out.println("hi");
		    drawGameState(g);
		}
		else if(currentState == END&&score>=15){
		    drawEndState(g);
		}
	}
	
	void drawGameState(Graphics g) {
		frame.setBackground(Color.MAGENTA);
		//g.setColor(Color.CYAN);
		//g.fillRect(0, 0, QuizWhiz.WIDTH, QuizWhiz.HEIGHT);
		//facilitateGame(g);
		manager.chooseQuestion(button);
		manager.showNewQuestion();
	}
	
}