import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class QuestionManager {
	GamePanel panel;
	int category;
	int setValue;
	TimeUnit timer;
	ArrayList<Question>gameQuestions = new ArrayList<Question>();
	Question[] historyQuestions = {new Question("Who defeated Napoleon at the Battle of Waterloo?","Duke of Wellington",3), new Question("What was the capital of Medieval Japan?","Heian-kyo",3),new Question("When did World War 1 end?","1918",2)};
	Question[] scienceQuestions = {new Question("What is the chemical formula for sulfuric acid?","H2SO4",3), new Question("What is the process that plants use to make food?","photosynthesis",2), new Question("Who planted pea plants to study heredity?","Gregor Mendel",3)};
	Question[] geographyQuestions = {new Question("What is the capital of Argentina?","Buenos Aires",3),new Question("Which country is Timbuktu in?","Mali",3),new Question("Which state has the Space Needle?","Washington",3)};
	Question[] musicQuestions = {new Question("Who wrote the song 'In My Feelings'?","Drake",2),new Question("Which composer wrote 'Four Seasons'?","Vivaldi",3),new Question("Where did Mozart play his symphonies?","Vienna",3)};
	Question[] literatureQuestions = {new Question("Who wrote 'Fahrenheit 451'?","Ray Bradbury",3),new Question("Which state was 'To Kill A Mockingbird' set in","Alabama",3),new Question("What is a poem with 14 lines called?","sonnet",3)};
	QuestionManager(GamePanel p){
		this.panel=p;
	}
	void chooseQuestion(JButton button) {
		JLabel label1=new JLabel();
		JLabel label2=new JLabel();
		label1.setLocation(100, 600);
		label1.setText("Choosing category");
		//panel.loadImage("wheel.jpg");
		//panel.draw(g,200,200,100,100);
		category=new Random().nextInt(5);
		setValue=new Random().nextInt(3);
		if(category==0) {
			//g.setColor(Color.BLACK);
			//g.drawString("Your category is History",100,600);
			label2.setLocation(100, 600);
			label2.setText("Your category is History");
			gameQuestions.add(historyQuestions[setValue]);
		}
		if(category==1){
			//g.setColor(Color.BLACK);
			//g.drawString("Your category is Science",100,600);
			label2.setLocation(100, 600);
			label2.setText("Your category is Science");
			gameQuestions.add(scienceQuestions[setValue]);
		}
		if(category==2) {
			//g.setColor(Color.BLACK);
			//g.drawString("Your category is Geography",100,600);
			label2.setLocation(100, 600);
			label2.setText("Your category is Geography");
			gameQuestions.add(geographyQuestions[setValue]);
		}
		if(category==3){
			//g.setColor(Color.BLACK);
			//g.drawString("Your category is Music",100,600);
			label2.setLocation(100, 600);
			label2.setText("Your category is Music");
			gameQuestions.add(musicQuestions[setValue]);
		}
		if(category==4){
			//g.setColor(Color.BLACK);
			//g.drawString("Your category is Literature",100,600);
			label2.setLocation(100, 600);
			label2.setText("Your category is Literature");
			gameQuestions.add(literatureQuestions[setValue]);
		}
	}
	
	String showQuestion(Question q) {
		String userAnswer=JOptionPane.showInputDialog(q.text);
		q.isAsked=true;
		return userAnswer;
	}
	void showNewQuestion() {
		try {
			timer.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		showQuestion(gameQuestions.get(gameQuestions.size()-1));
	}
	void checkQuestion(Question q) {
		if(showQuestion(q).equalsIgnoreCase(q.answer)) {
			JOptionPane.showMessageDialog(null,"You're correct");
			panel.score+=q.points;
			//panel.button.setText("Next Question");
		}
		else {
			JOptionPane.showMessageDialog(null,"Sorry, the correct answer is:"+q.answer);
			//panel.button.setText("Next Question");
		}
		panel.numberOfTimes++;
	}
	void giveHint(String s) {
		JOptionPane.showMessageDialog(null, "It starts with a "+s.charAt(0)+" and ends with an "+s.charAt(s.length()-1)+".");
	}
}
