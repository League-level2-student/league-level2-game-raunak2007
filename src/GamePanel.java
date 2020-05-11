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
import java.io.File;
import java.io.IOException;
import java.util.Timer;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
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
	Color brown=new Color(200,100,0);
	Font font=new Font("Helvetica",48,20);
	int numberOfMenu=0;
	JButton button=new JButton();
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
	BufferedImage image;
	boolean gotImage=false;
	boolean needImage=true;
	//boolean needHint=false;
	QuestionManager manager=new QuestionManager(this);
	ImageIcon imageIcon=new ImageIcon();
	Timer gameTimer=new Timer();
	JFrame frame=new JFrame();
	Graphics gr;
	GamePanel(){
		repaint();
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
		/*System.out.println(frame.getBackground());
		JLabel label=new JLabel();
		label.setVisible(true);
		label.setText("Quiz Whiz");
		label.setBounds(100,100,100,25);
		this.add(label);
		JLabel label1=new JLabel();
		label1.setVisible(true);
		label1.setText("Press Next to play");
		label1.setBounds(100,300,100,25);
		this.add(label1);
		JLabel label2=new JLabel();
		label2.setVisible(true);
		label2.setText("Press SPACE for instructions");
		label2.setBounds(100,600,100,25);
		this.add(label2);
		*/
		numberOfMenu++;
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, QuizWhiz.WIDTH, QuizWhiz.HEIGHT);
		g.setFont(font);
		g.setColor(Color.YELLOW);
		g.drawString("Quiz Whiz", 50, 200);
		g.drawString("Press ENTER to start", 50, 400);
		g.drawString("Press SPACE for instructions", 25, 600);
		button=new JButton();
		button.setText("Next");
		button.setBounds(100,700,100,25);
		button.addActionListener(this);
		if(numberOfMenu<=1) {
		this.add(button);
		}
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
		g.drawString("You win!",100,100);
		BufferedImage trophy=imageCollector("trophy.png");
		g.drawImage(trophy,100,100,250,250,null);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println(button.getText());
		if(currentState==MENU) {
			System.out.println("aoc");
		}
		if(button.getText().equals("Next")&&currentState==MENU) {
			currentState++;
			repaint();
			System.out.println("j");
		}
		else if(arg0.getSource()==button&&button.getText().equals("Move On")&&currentState==GAME) {
			currentState=QUESTION;
		}
		else if(score>=15&&currentState==QUESTION) {
			currentState=END;
		}
		repaint();
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getKeyCode()==KeyEvent.VK_SPACE) {
			JOptionPane.showMessageDialog(null, "The objective of the game is to answer as many questions as possible in a row. If you get a streak of 3 questions in a row, you win. You can press '+' to get a hint");
		}
		//if(arg0.getKeyCode()==KeyEvent.VK_SHIFT&&currentState==QUESTION) {
			//needHint=true;
		//}
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
	public void paintComponent(Graphics g){
		if(currentState==MENU) {
			drawMenuState(g);
		}
		else if(currentState == GAME){
		    drawGameState(g);
		}else if(currentState == END){
		    drawEndState(g);
		}
	}
	
	public static synchronized void playSound(final String url) {
		  new Thread(new Runnable() {
		  // The wrapper thread is unnecessary, unless it blocks on the
		  // Clip finishing; see comments.
		    public void run() {
		      try {
		        Clip clip = AudioSystem.getClip();
		        AudioInputStream inputStream = AudioSystem.getAudioInputStream(
		          Main.class.getResourceAsStream("/path/to/sounds/" + url));
		        clip.open(inputStream);
		        clip.start(); 
		      } catch (Exception e) {
		        System.err.println(e.getMessage());
		      }
		    }
		  }).start();}
	
	void drawGameState(Graphics g) {
		g.setColor(Color.MAGENTA);
		g.fillRect(0, 0, 500, 800);
		System.out.println("jhi");
		//g.setColor(Color.CYAN);
		//g.fillRect(0, 0, QuizWhiz.WIDTH, QuizWhiz.HEIGHT);
		//zebra(g);
		while(score<15&&numberOfTimes<7) {
		currentState=QUESTION;
		manager.setQuestionAsked();
		manager.checkQuestion(g);
		}
		if(score>=15&&currentState==QUESTION) {
			drawEndState(g);
		}
	}
	BufferedImage imageCollector(String fileName) {
			BufferedImage m=null;
			try {
			m=ImageIO.read(new File("C://"+fileName));
			}
			catch (IOException e) {
		    }
			if(m!=null) {
				System.out.println("nada");
			}
		return m;
	}
	
}