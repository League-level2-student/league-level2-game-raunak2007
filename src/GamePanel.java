import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

class GamePanel extends JPanel implements ActionListener, KeyListener{
	Font titleFont = new Font("Arial", Font.PLAIN, 24);
	public static BufferedImage bgImage;
	public static boolean needImage = true;
	public static boolean gotImage = false;	
	static final int MENU = 0;
    static final int GAME = 1;
    static final int END = 2;
    static int currentState = MENU;
    Timer frameDraw;
    Timer alienSpawn;
    Rocketship r=new Rocketship(250,400,50,50);
    ObjectManager manager=new ObjectManager(r);
    long startTime;
    GamePanel(){
    	this.frameDraw=new Timer(1000/60,this);
    	frameDraw.start();
    }
	@Override
	public void paintComponent(Graphics g){
		if(currentState == MENU){
		    drawMenuState(g);
		}else if(currentState == GAME){
		    drawGameState(g);
		}else if(currentState == END){
		    drawEndState(g);
		}
	}
	void loadImage(String imageFile) {
	    if (needImage) {
	        try {
	            bgImage = ImageIO.read(this.getClass().getResourceAsStream(imageFile));
		    gotImage = true;
	        } catch (Exception e) {
	            
	        }
	        needImage = false;
	    }
	}
	void startGame() {
		alienSpawn = new Timer(3000 , manager);
		startTime=System.currentTimeMillis();
	    alienSpawn.start();
	}
	void updateMenuState() { 
		
	}
	void updateGameState() { 
		manager.update();
		if(r.isActive==3) {
			currentState=END;
		}
	}
	void updateEndState()  { 
		
	}
	void drawMenuState(Graphics g) { 
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, ObstacleDerby.WIDTH, ObstacleDerby.HEIGHT);
		g.setFont(titleFont);
		g.setColor(Color.YELLOW);
		g.drawString("Obstacle Derby", 100, 200);
		g.drawString("Press ENTER to start", 100, 400);
		g.drawString("Press SPACE for instructions", 100, 600);
	}
	void drawGameState(Graphics g) { 
		if (needImage) {
		    loadImage ("field.png");
		}
		if (gotImage) {
			g.drawImage(bgImage, 0, 0, 500, 800, null);
		} else {
			g.setColor(Color.BLUE);
			g.fillRect(0, 0, 500, 800);
		}
		manager.draw(g);
		g.drawString(manager.getScore()+"", 0, 0);
	}
	void drawEndState(Graphics g)  { 
		g.setColor(Color.RED);
		g.fillRect(0, 0, ObstacleDerby.WIDTH, ObstacleDerby.HEIGHT);
		g.setColor(Color.BLACK);
		g.drawString("Game Over", 250, 400);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println("game");
		if(currentState == MENU){
		    updateMenuState();
		}else if(currentState == GAME){
		    updateGameState();
		}else if(currentState == END){
		    updateEndState();
		}
		repaint();
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode()==KeyEvent.VK_ENTER) {
		    if (currentState == END) {
		        currentState = MENU;
		        r=new Rocketship(0,0,20,20);
		        manager=new ObjectManager(r);
		    } else {
		        currentState++;
		    }
		}
		if (e.getKeyCode()==KeyEvent.VK_RIGHT) {
		    r.right(r.speed);
		}
		if (e.getKeyCode()==KeyEvent.VK_LEFT) {
		    r.left(r.speed);
		}
		startGame();
		if(currentState==END) {
			alienSpawn.stop();
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode()==KeyEvent.VK_RIGHT) {
		    r.right(r.speed/2);
		}
		if (e.getKeyCode()==KeyEvent.VK_LEFT) {
		    r.left(r.speed/2);
		}
	}
}
