import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.imageio.ImageIO;

public class Waldo {
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;	
	public int waldoX;
	public int waldoY;
	public int width=10;
	public int height=20;
	public Rectangle waldoBox;

	Waldo(int x, int y){
		waldoX=x;
		waldoY=y;
		waldoBox=new Rectangle(waldoX-5,waldoY-5,10,10);
		if (needImage) {
		    loadImage ("waldo.png");
		}
	}
		void draw(Graphics g) {
	        if (gotImage) {
	        	g.drawImage(image, waldoX, waldoY, width, height, null);
	        } else {
	        	g.setColor(Color.BLUE);
	        	g.fillRect(waldoX, waldoY, width, height);
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
}
