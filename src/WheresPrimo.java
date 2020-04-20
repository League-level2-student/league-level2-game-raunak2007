import javax.swing.JFrame;

public class WheresPrimo {
	JFrame frame;
	public static final int WIDTH=500;
	public static final int HEIGHT=800;
	PrimoPanel panel;
	WheresPrimo(JFrame f){
		this.frame=f;
		this.panel=new PrimoPanel();
	}
     void createUI() {
    	 frame.setVisible(true);
    	 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	 frame.addKeyListener(panel);
    	 frame.add(panel);
     }
}
