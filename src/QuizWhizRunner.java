import javax.swing.JFrame;

public class QuizWhizRunner {
	public static void main(String[] args) {
		JFrame frame=new JFrame();
		QuizWhiz p=new QuizWhiz(frame);
		p.createUI();
	}
}

