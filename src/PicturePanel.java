import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class PicturePanel {
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
	BufferedImage image;
	boolean gotImage=false;
	boolean needImage=true;
	boolean needHint=false;
	//QuestionManager manager=new QuestionManager(this);
	ImageIcon imageIcon=new ImageIcon();
	Timer gameTimer=new Timer();
	JFrame frame=new JFrame();
	Image img = Toolkit.getDefaultToolkit().getImage("E:\\trophy.png");
	Graphics g;
}
