import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class PrimoPanel extends JPanel implements ActionListener, KeyListener {
	final int MENU=0;
	final int GAME=1;
	final int END=2;
	int currentState=MENU;
	Graphics graphic;
	Player p;
	Timer frameDraw;
    Timer alienSpawn;
    Rectangle waldoBox;
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;	
	int waldoX;
	int waldoY;
	int score=0;
	Waldo waldo;
	PrimoPanel(){
    	this.frameDraw=new Timer(1000/60,this);
    	frameDraw.start();
    	if (needImage) {
		    loadImage ("flag.png");
		}
    }
	protected void paintComponent(Graphics g){
		if(currentState == MENU){
		    drawMenuState(g);
		}
		else if(currentState == GAME){
			drawGameState(g);
		}
		else if(currentState == END){
		    drawEndState(g);
		}
	}
	void updateMenuState() {  
		
	}
	void updateGameState() { 
		setWaldo(graphic);
	}
	void updateEndState()  { 
		 
	}
	void drawMenuState(Graphics g) { 
		g.setColor(Color.RED);
		g.fillRect(0, 0, WheresPrimo.WIDTH, WheresPrimo.HEIGHT);
		g.setFont(new Font("Helvetica",Font.BOLD,24));
		g.setColor(Color.WHITE);
		g.drawString("Where's Waldo", 100, 100);
		g.drawString("Press ENTER to start", 100, 400);
		g.drawString("Press SPACE for instructions", 100, 600);

	}
	void setWaldo(Graphics g) {
		waldo=new Waldo(new Random().nextInt(460),new Random().nextInt(790));
		
	}
	void drawGameState(Graphics g) { 
		draw(g);
		g.setColor(Color.GREEN);
		g.drawString("Score:"+score+"",100,100);
	}
	void drawEndState(Graphics g)  { 
		g.setColor(Color.RED);
		g.fillRect(0, 0, WheresPrimo.WIDTH, WheresPrimo.HEIGHT);
		g.drawString("You have obviously won!",200,100);
	 }
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(currentState == MENU){
		    updateMenuState();
		}
		else if(currentState == GAME){
			updateGameState();
			waldo.draw(graphic);
		}
		else if(currentState == END){
			updateEndState();
		}
		repaint();
	}
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getKeyCode()==KeyEvent.VK_ENTER) {
		    if (currentState == END) {
		        currentState = MENU;
		    }
		    else if (currentState == MENU) {
		        currentState = GAME;
		        setWaldo(graphic);
		    }
		    else if (currentState == GAME&&score>=10) {
		        currentState = END;
		    }
		}   
		if(currentState==END&&score<15&&arg0.getKeyCode()==KeyEvent.VK_ENTER) {
		    JOptionPane.showMessageDialog(null,"allahu akbar!");
		}
		if (arg0.getKeyCode()==KeyEvent.VK_UP) {
		    p.up(p.speed);
		}
		if (arg0.getKeyCode()==KeyEvent.VK_DOWN) {
		    p.down(p.speed);
		}
		if (arg0.getKeyCode()==KeyEvent.VK_RIGHT) {
		    p.right(p.speed);
		}
		if (arg0.getKeyCode()==KeyEvent.VK_LEFT) {
		    p.left(p.speed);
		}
		if(arg0.getKeyCode()==KeyEvent.VK_SPACE&&currentState==MENU) {
			JOptionPane.showMessageDialog(null, "The objective of the game is to find Waldo as quick as you can. You lose a point every second you play the game, so the quicker you find Waldo");
		}	
		if(arg0.getKeyCode()==KeyEvent.VK_SPACE&&currentState==GAME&&p.collisionBox.intersects(waldo.waldoBox)) {
			score+=1;
			setWaldo(graphic);
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode()==KeyEvent.VK_UP) {
		    p.up(p.speed/2);
		}
		if (e.getKeyCode()==KeyEvent.VK_DOWN) {
		    p.down(p.speed/2);
		}
		if (e.getKeyCode()==KeyEvent.VK_RIGHT) {
		    p.right(p.speed/2);
		}
		if (e.getKeyCode()==KeyEvent.VK_LEFT) {
		    p.left(p.speed/2);
		}
	}
	void startGame() {
		alienSpawn = new Timer(1000,Waldo);
	    alienSpawn.start();
	}
	void draw(Graphics g) {
		g.setColor(Color.RED);
        g.fillRect(0, 0, WheresPrimo.WIDTH, WheresPrimo.HEIGHT);
        if (gotImage) {
        	g.drawImage(image, 0, 0, WheresPrimo.WIDTH, WheresPrimo.HEIGHT, null);
        } else {
        	g.setColor(Color.BLUE);
        	g.fillRect(0, 0, WheresPrimo.WIDTH, WheresPrimo.HEIGHT);
        }
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
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	
	}
}

