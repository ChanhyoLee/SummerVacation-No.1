import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BackSpacePad extends Pad implements ActionListener{
	ScreenPanel sp;
	NumberPanel np;
	
	public BackSpacePad(ScreenPanel sp,NumberPanel np) {
		super("<-");
		this.sp = sp;
		this.np = np;
		this.setLocation(350,350);
		this.setSize(50,100);
		this.setFont(new Font("Helvetica",Font.BOLD,30));
		this.addActionListener(this);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		erase_element();
		//this.transferFocusUpCycle();
		this.requestDefaultFocus();
		//FocusTraversalPolicy.getDefaultComponent().requestFocus()
		//this.transferFocus();
		//this.transferFocusBackward();
		
	}

	public void erase_element() {
		try {
			String str = sp.get_expression();
			sp.clear_screen();
			str = str.substring(0, str.length()-1);
			sp.update_screen(str);
		}catch(Exception e) {}
	}

}
