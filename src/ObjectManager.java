import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager implements ActionListener {
	GamePanel g;
	Random random=new Random();
	Rocketship r;
	ArrayList<Obstacle> aliens=new ArrayList<Obstacle>();
	int score=0;
	ObjectManager(Rocketship rocket){
		this.r=rocket;
	}
	void addAlien() {
		aliens.add(new Obstacle(random.nextInt(ObstacleDerby.WIDTH),750,50,50));
	}
	void update() {
		for(int i=0;i<aliens.size();i++) {
			aliens.get(i).update();
			if(aliens.get(i).y>ObstacleDerby.HEIGHT) {
				aliens.get(i).isActive=3;
			}
		}
		checkCollision();
		purgeObjects();
	}
	int getScore() {
		return score;
	}
	void draw(Graphics g) {
		r.draw(g);
		for(int i=0;i<aliens.size();i++) {
			aliens.get(i).draw(g);
		}
	}
	void purgeObjects() {
		for(int i=0;i<aliens.size();i++) {
			if(aliens.get(i).isActive==3) {
				aliens.remove(i);
			}
		}
	}
	void checkCollision() {
		for(int i=0;i<aliens.size();i++) {
			if(r.collisionBox.intersects(aliens.get(i).collisionBox) ) {
				aliens.get(i).isActive+=1;
				r.isActive+=1;
			}
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		long currentTime=System.currentTimeMillis();
		long elapsed=currentTime-g.startTime;
		if(elapsed/3000>=aliens.size()) {
		addAlien();
	}
	}
}
