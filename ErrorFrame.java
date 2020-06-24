import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Timer;


public class ErrorFrame extends JFrame{
	JLabel error = new JLabel("Wrong input!");

	public ErrorFrame() {
		this.setTitle("Error");
		this.setLocation(100,300);
		this.setSize(200,100);
		this.setLayout(new BorderLayout());
		error.setFont(new Font("Helvetica",Font.BOLD,20));
		error.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(error,"Center");
		this.setVisible(true);
	}
	
	public void auto_exit() {
		ActionListener al = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				exit_ErrorFrame();
			}		
		};
		Timer timer = new Timer(1000,al);
		timer.start();
	}
	public void exit_ErrorFrame() {
		this.setVisible(false);
	}
	public void set_error_text(String str) {
		error.setText(str);
	}
}
