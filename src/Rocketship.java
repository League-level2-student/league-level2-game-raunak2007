import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

class Rocketship extends GameObject {
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;	
	Rocketship(int x, int y, int width, int height) {
		super(x, y, width, height);
		speed=10;
		if (needImage) {
		    loadImage ("elprimo.jpg");
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
