import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JPanel;

public class NumberPanel extends JPanel{
	
	ArrayList<Pad> numbers = new ArrayList<Pad>();
	String[] buttons_value = {"7","8","9","4","5","6","1","2","3","0",".","00"};
	public NumberPanel() {
		this.setLocation(0,150);
		this.setSize(300,400);
		this.setLayout(new GridLayout(4,3));
		//this.setBackground(Color.DARK_GRAY);
		//this.setForeground(Color.BLUE);
		for(int i=0; i<buttons_value.length; i++) {
			Pad temp = new Pad(buttons_value[i]);
			temp.setSize(100,100);
			temp.setFont(new Font("Helvetica",Font.PLAIN,30));
			numbers.add(temp);
			this.add(temp);
		}
	}
	
	public void addListener(ActionListener al) {
		for(Pad b:numbers) {
			b.addActionListener(al);
		}
	}
	public void myKeyListener(KeyListener kl) {
		for(Pad b:numbers) {
			b.addKeyListener(kl);
		}
	}
	public String[] get_buttons_value() {
		return buttons_value;
	}

}
