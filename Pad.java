import java.awt.Color;
import javax.swing.JButton;

public class Pad extends JButton{
	private String value;
	
	public Pad(String value) {
		this.value = value;
		this.setText(this.value);
		//this.setBackground(Color.DARK_GRAY);
		//this.setSize(100,100);
		//this.setVisible(true);
	}
	
	public String get_value() {
		return value;
	}
	public void set_value(String value) {
		this.value = value;
	}	
}
