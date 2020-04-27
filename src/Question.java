
public class Question {
	String text;
	String answer;
	boolean isAsked=false;
	int points;
	Question(String t, String a, int p){
		this.text=t;
		this.answer=a;
		this.points=p;
	}
}
