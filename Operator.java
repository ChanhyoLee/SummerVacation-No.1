
public class Operator extends Token{
	
	public Operator(String symbol) {
		super(symbol);
	}

	public int get_precedence(Token token) {
		if(token.get_symbol().equals("*")||token.get_symbol().equals("/")||token.get_symbol().equals("√")||token.get_symbol().equals("^")) return 2;
		else if(token.get_symbol().equals("+")||token.get_symbol().equals("-")) return 1;
		else return 0;
	}
}
