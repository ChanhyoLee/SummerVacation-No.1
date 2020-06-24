import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class CalculatorFrame extends JFrame implements ActionListener, KeyListener{
	ScreenPanel screenpanel = new ScreenPanel();
	NumberPanel numberpanel = new NumberPanel();
	SymbolPanel symbolpanel = new SymbolPanel();
	AdvancedPanel advancedpanel = new AdvancedPanel(screenpanel,numberpanel);
	ClearPad clearpad = new ClearPad(screenpanel,numberpanel);
	EqualPad equalpad = new EqualPad(screenpanel,numberpanel);
	BackSpacePad backspacepad = new BackSpacePad(screenpanel,numberpanel);
	JPanel extrapanel = new JPanel();
	JPanel equalpanel = new JPanel();
	
	boolean is_shift_on = false;
		
	public CalculatorFrame(){
		this.setTitle("Chanhyo's Calculator");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(400,620);
		this.setResizable(false);
		Container contentPane = this.getContentPane();
		contentPane.setLayout(null);
		extrapanel.setBounds(300,350,100,100);
		//extrapanel.setSize(100,100);
		//extrapanel.setLayout(new FlowLayout());
		extrapanel.setLayout(new GridLayout(2,1));
		extrapanel.add(clearpad);
		extrapanel.add(backspacepad);
		equalpanel.add(equalpad);
		
		contentPane.add(advancedpanel);
		contentPane.add(extrapanel);
		contentPane.add(screenpanel);
		contentPane.add(numberpanel);
		contentPane.add(equalpad);
		contentPane.add(symbolpanel);

		numberpanel.addListener(this);
		numberpanel.myKeyListener(this);
		symbolpanel.addListener(this);
		this.setVisible(true);

	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {
		Pad b = (Pad)e.getSource();
		screenpanel.update_screen(b.get_value());
		symbolpanel.requestDefaultFocus();
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int keyCode = e.getKeyCode();
		if(keyCode == 16) is_shift_on = true;
		keyCode = decrypt_keyCode(keyCode);
		act(keyCode);	
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if(keyCode == 16) is_shift_on = false;
	}
	
	public int decrypt_keyCode(int keyCode) {
		if(is_shift_on) {
			if(keyCode==56) return 42;
			else if(keyCode==61) return 43;
			else if(keyCode==54) return 94;
			else if(keyCode==57) return 40;
			else if(keyCode==48) return 41;
			else if(keyCode==51) return -1;
			else return keyCode;
		}
		else return keyCode;	
	}
	
	public void act(int keyCode) {
		if((keyCode>=48 && keyCode<=57)||keyCode==40||keyCode==41||keyCode==42||keyCode==43||keyCode==45||keyCode==46||keyCode==47||keyCode==94) screenpanel.update_screen((char)keyCode+"");
		else if(keyCode==-1) screenpanel.update_screen("√");
		else if(keyCode==8) backspacepad.erase_element();
		else if(keyCode==10||keyCode==61) new Calculate(screenpanel, screenpanel.get_expression());
		else if(keyCode==67) screenpanel.clear_screen();
		}
}
