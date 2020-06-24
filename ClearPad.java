import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClearPad extends Pad implements ActionListener{
	ScreenPanel sp;
	NumberPanel np;

	public ClearPad(ScreenPanel sp, NumberPanel np) {
		super("C");
		this.sp = sp;
		this.np = np;
		this.setLocation(300,350);
		this.setSize(50,100);
		this.setFont(new Font("Helvetica",Font.BOLD,30));
		this.set_value("");
		this.addActionListener(this);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {
		sp.clear_screen();		
		this.requestDefaultFocus();
		//np.requestFocus();
	}
}
