import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Player {
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;	
	int x;
	int y;
	int width;
	int speed;
	int height;
	Rectangle collisionBox;
	Player(int x, int y, int width, int height) {
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
		this.collisionBox=new Rectangle(x,y,width, height);
		speed=10;
		if (needImage) {
		    loadImage ("player.png");
		}
		// TODO Auto-generated constructor stub
	} 
	void draw(Graphics g) {
		g.drawImage(image, x,y,width,height,null);
        if (gotImage) {
        	g.drawImage(image, x, y, width, height, null);
        } else {
        	g.setColor(Color.BLUE);
        	g.fillRect(x, y, width, height);
        }
	}
	public void right(int s) {
        x+=s/2;
        x+=s/2;
    }
	public void left(int s) {
		x-=s/2;
        x-=s/2;
    }
	public void down(int s) {
		y+=s/2;
        y+=s/2;
    }
	public void up(int s) {
        y-=s/2;
        y-=s/2;
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

}

