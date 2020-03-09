import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Obstacle extends GameObject{
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;	
	Obstacle(int x, int y, int width, int height) {
		super(x, y, width, height);
		speed=1;
		if (needImage) {
		    loadImage ("obstacle.jpg");
		}
	}
	void update() {
		super.update();
		y-=4*speed;
	}
	void draw(Graphics g) {
		g.setColor(Color.YELLOW);
        g.fillRect(x, y, width, height);
        if (gotImage) {
        	g.drawImage(image, x, y, width, height, null);
        } else {
        	g.setColor(Color.BLUE);
        	g.fillRect(x, y, width, height);
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
