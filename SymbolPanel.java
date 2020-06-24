import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JPanel;

public class SymbolPanel extends JPanel{

	ArrayList<Pad> symbols = new ArrayList<Pad>();
	String[] buttons_value = {"+","-","*","/"};
	public SymbolPanel() {
		this.setLocation(300,150);
		this.setSize(100,200);
		this.setLayout(new GridLayout(2,2));
		for(int i=0; i<buttons_value.length; i++) {
			Pad temp = new Pad(buttons_value[i]);
			temp.setSize(50,100);
			temp.setFont(new Font("Helvetica",Font.PLAIN,20));
			symbols.add(temp);
			this.add(temp);
		}
	}
	
	public void addListener(ActionListener al) {
		for(Pad b:symbols) {
			b.addActionListener(al);
		}
	}
	public String[] get_buttons_value() {
		return buttons_value;
	}

}
