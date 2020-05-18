import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
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
	Question questionAsked;
	Question[] historyQuestions = {new Question("Who defeated Napoleon at the Battle of Waterloo?","Duke of Wellington",3), new Question("What was the capital of Medieval Japan?","Heian-kyo",3),new Question("When did World War 1 end?","1918",2)};
	Question[] scienceQuestions = {new Question("What is the chemical formula for sulfuric acid?","H2SO4",3), new Question("What is the process that plants use to make food?","photosynthesis",2), new Question("Who planted pea plants to study heredity?","Gregor Mendel",3)};
	Question[] geographyQuestions = {new Question("What is the capital of Argentina?","Buenos Aires",3),new Question("Which country is Timbuktu in?","Mali",3),new Question("Which state has the Space Needle?","Washington",3)};
	Question[] musicQuestions = {new Question("Who wrote the song 'In My Feelings'?","Drake",2),new Question("Which composer wrote 'Four Seasons'?","Vivaldi",3),new Question("Where did Mozart play his symphonies?","Vienna",3)};
	Question[] literatureQuestions = {new Question("Who wrote 'Fahrenheit 451'?","Ray Bradbury",3),new Question("Which state was 'To Kill A Mockingbird' set in","Alabama",3),new Question("What is a poem with 14 lines called?","sonnet",3)};
	QuestionManager(GamePanel p){
		this.panel=p;
	}
	void chooseQuestion() {
		JOptionPane.showMessageDialog(null,"Choosing category...");
		category=new Random().nextInt(5);
		setValue=new Random().nextInt(3);
		if(category==0) {
			JOptionPane.showMessageDialog(null,"Your category is History");
			if(historyQuestions[setValue].isAsked==false) {
				gameQuestions.add(historyQuestions[setValue]);
			}
		}
		if(category==1){
			JOptionPane.showMessageDialog(null,"Your category is Science");
			if(scienceQuestions[setValue].isAsked==false) {
				gameQuestions.add(scienceQuestions[setValue]);
			}
		}
		if(category==2) {
			JOptionPane.showMessageDialog(null,"Your category is Geography");
			if(geographyQuestions[setValue].isAsked==false) {
				gameQuestions.add(geographyQuestions[setValue]);
			}		
		}
		if(category==3){
			JOptionPane.showMessageDialog(null,"Your category is Music");
			if(musicQuestions[setValue].isAsked==false) {
				gameQuestions.add(musicQuestions[setValue]);
			}
		}
		if(category==4){
			JOptionPane.showMessageDialog(null,"Your category is Literature");
			if(literatureQuestions[setValue].isAsked==false) {
				gameQuestions.add(literatureQuestions[setValue]);
			}
		}
		
	}
	
	/*String showQuestion(Question q) {
		String userAnswer=JOptionPane.showInputDialog(q.text);
		q.isAsked=true;
		return userAnswer;
	}*/
	/*void giveHint() {
		JOptionPane.showMessageDialog(null, "It starts with a "+questionAsked.answer.charAt(0)+" and ends with an "+questionAsked.answer.charAt(questionAsked.answer.length()-1)+".");
	}
	*/
	void setQuestionAsked() {
				chooseQuestion();
				questionAsked=gameQuestions.get(gameQuestions.size()-1);
				while(questionAsked.isAsked==true) {
					chooseQuestion();
					questionAsked=gameQuestions.get(gameQuestions.size()-1);
				}
	}
	String showNewQuestion() {
		try {
			timer.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("s");
		String s=JOptionPane.showInputDialog(questionAsked.text);
		return s;
		//return showQuestion(gameQuestions.get(gameQuestions.size()-1));
	}
	void checkQuestion(Graphics g) {
		//if(panel.needHint==true) {
			//giveHint();
		//}
		//System.out.println("world");
		String s=showNewQuestion();
		if(s.equalsIgnoreCase(questionAsked.answer)) {
			JOptionPane.showMessageDialog(null,"You're correct");
			panel.score+=questionAsked.points;
			//panel.button.setText("Next Question");
		}
		else {
			JOptionPane.showMessageDialog(null,"Sorry, the correct answer is:\n"+gameQuestions.get(gameQuestions.size()-1).answer);
			//panel.button.setText("Next Question");
		}
		questionAsked.isAsked=true;
		panel.numberOfTimes++;
		if(panel.numberOfTimes>=2) {
			panel.currentState=panel.END;
		}
		/*if(panel.score<18) {
			JOptionPane.showMessageDialog(null,"Your score is: "+panel.score);
		}*/
		
	}
}
