import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EqualPad extends Pad implements ActionListener {
	ScreenPanel sp;
	NumberPanel np;

	public EqualPad(ScreenPanel sp,	NumberPanel np) {
		super("=");
		this.sp = sp;
		this.np = np;
		this.setLocation(300,450);
		this.setSize(100,100);
		this.setFont(new Font("Helvetica",Font.BOLD,40));
		this.addActionListener(this);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {
		new Calculate(sp, sp.get_expression());
		this.requestDefaultFocus();
		//np.requestFocus();
	}
}
