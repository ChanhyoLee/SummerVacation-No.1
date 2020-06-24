import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CloseBracketPad extends Pad implements ActionListener{
	ScreenPanel sp;
	NumberPanel np;

	public CloseBracketPad(ScreenPanel sp, NumberPanel np) {
		super(")");
		this.sp = sp;
		this.np = np;
		this.setSize(100,50);
		this.setFont(new Font("Helvetica",Font.PLAIN,20));
		this.addActionListener(this);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {
		sp.update_screen(")");
		this.requestDefaultFocus();
		//np.requestFocus();
	}

}
