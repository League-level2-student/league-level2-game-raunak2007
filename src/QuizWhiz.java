import javax.swing.JFrame;

public class QuizWhiz {
	JFrame frame;
	public static final int WIDTH=500;
	public static final int HEIGHT=800;
	GamePanel panel;
	QuizWhiz(JFrame f){
		this.frame=f;
		this.panel=new GamePanel();
	}
     void createUI() {
    	 frame.setVisible(true);
    	 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	 frame.addKeyListener(panel);
    	 frame.add(panel);
     }
}