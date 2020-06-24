import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RootPad extends Pad implements ActionListener {
	ScreenPanel sp;
	NumberPanel np;

	public RootPad(ScreenPanel sp,	NumberPanel np) {
		super("¡î(#)");
		this.sp = sp;
		this.np = np;
		this.setSize(100,50);
		this.setFont(new Font("Helvetica",Font.PLAIN,20));
		this.addActionListener(this);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {
		sp.update_screen("¡î");
		this.requestDefaultFocus();
		//np.requestFocus();
	}
}
