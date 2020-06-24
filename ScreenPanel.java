import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

public class ScreenPanel extends JPanel{
	JLabel input_screen = new JLabel("");
	String input = "";
	
	public ScreenPanel() {
		this.setLocation(0,0);
		this.setSize(400,150);
		this.setVisible(true);
		this.setBackground(Color.WHITE);
		this.setLayout(new BorderLayout());
		input_screen.setFont(new Font("Helvetica",Font.PLAIN,30));
		this.add(input_screen,"East");
		//input_screen.setVisible(true);
	}
	
	public void update_screen(String str) {
		if(input.length()+str.length()>20) {
			ErrorFrame ef = new ErrorFrame();
			ef.set_error_text("Too Long Input!");
			ef.auto_exit();
			return;
		}
		input += str;
		input_screen.setText(input);
	}
	
	public void clear_screen() { 
		input = "";
		input_screen.setText("");
	}
	
	public String get_expression() {
		return input;
	}

}
