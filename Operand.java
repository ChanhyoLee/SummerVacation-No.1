import java.math.BigDecimal;

public class Operand extends Token{
	BigDecimal value;
	
	public Operand(String value) {
		super(value);
		try{
			this.value = BigDecimal.valueOf(Double.parseDouble(value));
		}catch(Exception e) {
			System.out.println("here4");
			//new ErrorFrame();		
		}
	}
}
