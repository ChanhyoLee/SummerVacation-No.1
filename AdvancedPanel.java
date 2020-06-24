import java.awt.GridLayout;

import javax.swing.JPanel;

public class AdvancedPanel extends JPanel{
	
	PowerPad powerpad;
	RootPad rootpad;
	OpenBracketPad openbracketpad;
	CloseBracketPad closebracketpad;
	
	public AdvancedPanel(ScreenPanel sp, NumberPanel np){
		powerpad = new PowerPad(sp, np);
		rootpad = new RootPad(sp, np);
		openbracketpad = new OpenBracketPad(sp, np);
		closebracketpad = new CloseBracketPad(sp, np);
		this.add(powerpad);
		this.add(rootpad);
		this.add(openbracketpad);
		this.add(closebracketpad);
		this.setLocation(0,550);
		this.setSize(400,50);
		this.setLayout(new GridLayout(1,4));
	}
}
