import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Calculate {
	Stack<Operator> stack = new Stack<Operator>();
	Stack<String> inside_bracket_str = new Stack<String>();
	Queue<Operand> num_queue = new LinkedList<Operand>();
	ScreenPanel sp;

	Operator op = new Operator("$");
	String original_expression ="";
	String str_expression ="";
	int minus_count=0;
	boolean inverse = false;
	boolean status = true;
	boolean in_bracket = false;
	int bracket_count=0;
	
	public Calculate(ScreenPanel sp, String str){
		this.sp = sp;
		str_expression = str;
		original_expression = str;
		System.out.println(str);
		convert_to_postfix();
		if(status) {
			System.out.println(str_expression);
			System.out.println(calculate());
			sp.clear_screen();
			if(status) sp.update_screen(String.valueOf(calculate()));
		}
		else sp.clear_screen();
	}
	public Calculate(String str, ScreenPanel sp) {
		str_expression = str;
		original_expression = str;
		System.out.println("inside str: "+str);
		convert_to_postfix();
		//System.out.println("inside status: " + status);
		if(status) {
			double temp = calculate().doubleValue();
			if(temp<0) minus_count++;
			temp = Math.abs(temp);
			str_expression = String.valueOf(temp);
			System.out.println("After str: "+str_expression);
			//System.out.println("inside str: " + str_expression);
		}
	}
	
	private void convert_to_postfix() {
		stack.push(op);
		String str = str_expression;
		String number = "";
		String temp_str = "";
		String in_bracket_str = "";
		int count_exponent_sign =0;
		for(int i=0; i<str.length(); i++) {
			if(str.charAt(i)=='(') {
				in_bracket = true;
				bracket_count++;
				inside_bracket_str.add(in_bracket_str);
				in_bracket_str = "";
				continue;
			}
			else if(str.charAt(i)==')') {
				bracket_count--;
				System.out.println("bracket count: "+bracket_count);
				if(bracket_count > 1) in_bracket_str += ')';
				else if(bracket_count ==0)in_bracket = false;
				//in_bracket_str = inside_bracket_str.pop() + in_bracket_str;
				//System.out.println("before in_bracket_str: "+in_bracket_str);
				Calculate temp_cal = new Calculate(in_bracket_str, sp);
				in_bracket_str = inside_bracket_str.pop();
				//System.out.println("number: " + number);
				number ="";
				number += temp_cal.get_str_expression();
				if(bracket_count >= 1) {
					//in_bracket_str += stack.peek().get_symbol();
					in_bracket_str += temp_cal.get_str_expression();
				}

				System.out.println("after in_bracket_str: "+in_bracket_str);

				minus_count += temp_cal.get_minus_count();
				continue;
			}
			//System.out.println(str.charAt(i));
			/*if(str.charAt(i) ==')') {
				in_bracket = false;
				Calculate temp_cal = new Calculate(in_bracket_str);
				temp_str += temp_cal.get_str_expression();
				continue;
			}*/ //&& (str.charAt(i)!=')' && (str.charAt(i)!='('))
			if(in_bracket && (str.charAt(i)!=')' && (str.charAt(i)!='(')) && bracket_count==0) {
				//System.out.println(str.charAt(i));
				in_bracket_str += str.charAt(i);
				System.out.println("bracket count =0: " + in_bracket_str);
				continue;
			}
			if(in_bracket && bracket_count>=1) {
				//System.out.println(str.charAt(i));
				System.out.println("bracket count= "+bracket_count+": "+ in_bracket_str);
				in_bracket_str += str.charAt(i);
				continue;
			}
			if(is_operator(str.charAt(i))){
				if(number.contains("E")) {
					count_exponent_sign++;
					if(count_exponent_sign==1) {
						number += str.charAt(i);
						continue;
					}
				}

				Operator temp_op = new Operator(Character.toString(str.charAt(i)));
				if(number.length()==0) {
					if(str.charAt(i)=='-' && !stack.peek().get_symbol().equals("√")) {
						if(stack.peek().get_symbol().equals("*")||stack.peek().get_symbol().equals("/")) minus_count++;
						else if(stack.peek().get_symbol().equals("-")) {
							stack.pop();
							stack.push(new Operator("+"));
						}
						else if(stack.peek().get_symbol().equals("+")){
							stack.pop();
							stack.push(new Operator("-"));
						}
						else if(stack.peek().get_symbol().equals("$")) minus_count++;
						else if(stack.peek().get_symbol().equals("^")) inverse = true;
						//else if(stack.peek().get_symbol().equals(""))
					
					}
					/*else if( str.charAt(i)=='-' && stack.peek().get_symbol().equals("�닖")) {
						
					}*/
					else if(str.charAt(i)=='+') {;
						if(stack.peek().get_symbol().equals("√")) {
						}
					}
					else if(str.charAt(i)=='√') {
						stack.push(new Operator("√"));
					}
					else {
						System.out.println("here");
						show_error();
						return;
					}
				}
				else {
					Operand temp_num = new Operand(number);	
					num_queue.add(temp_num);
					temp_str += number + ",";
					number = "";
					if(stack.peek().get_symbol().equals("√")) {
						temp_str += stack.pop().get_symbol() + ",";
					}
					while((op.get_precedence(stack.peek())>=op.get_precedence(temp_op)) && (stack.peek().get_symbol() != "$")){
						//System.out.println(temp_str);
						//System.out.println((op.get_precedence(stack.peek()) + op.get_precedence(temp_op)));
						//System.out.println(stack.peek().get_symbol()+","+temp_op.get_symbol());
						temp_str += stack.pop().get_symbol();
						temp_str += ",";
					}
					if(minus_count%2 == 1) {
						if(temp_op.get_symbol().equals("+")) stack.push(new Operator("-"));
						else if(temp_op.get_symbol().equals("-")) stack.push(new Operator("+"));
						else stack.push(temp_op);
					}
					else stack.push(temp_op);
				}
			}
			else {
				number += str.charAt(i);
				num_queue.add(new Operand(number));
			}
		}
		if(number.length()!=0) num_queue.add(new Operand(number));
		else {
			if(original_expression.length()==0) return;
			else if(original_expression.charAt(original_expression.length()-1)==')') return;
			else {
				System.out.println("here2");
				show_error();
				return;
			}
		}
		temp_str += number + ",";
		//System.out.println("outside for" + temp_str);
		while(!stack.empty() && stack.peek().get_symbol() != "$") temp_str += stack.pop().get_symbol() + ",";
		str_expression = temp_str;
	}
	
	@SuppressWarnings("deprecation")
	private BigDecimal calculate() {
		Stack<Operand> num_stack = new Stack<Operand>();
		BigDecimal result=BigDecimal.valueOf(0.0);
		String str = str_expression;
		String temp_num ="";
		BigDecimal num1,num2;
		for(int i=0; i<str.length(); i++) {
			if(str.charAt(i)==',') {
				if(!is_operator(temp_num.charAt(0))) {
					//System.out.println(temp_num+","+temp_num.equals("√"));
					num_stack.push(new Operand(temp_num));
				}
				else {
					//System.out.println(is_operator('√'));
					if(num_stack.size()!=1) {
						try {
							num1 = BigDecimal.valueOf(Double.valueOf(num_stack.pop().get_symbol()));
							num2 = BigDecimal.valueOf(Double.valueOf(num_stack.pop().get_symbol()));
							//System.out.println(temp_num.equals("√"));
							if(temp_num.equals("+")) result = num1.add(num2);
							else if(temp_num.equals("-")) result = num2.subtract(num1);
							else if(temp_num.equals("*")) result = num1.multiply(num2);
							else if(temp_num.equals("/")) result = num2.divide(num1,8,BigDecimal.ROUND_HALF_UP);
							else if(temp_num.equals("^")) {
								//System.out.println(inverse);
								if(!inverse) result = new BigDecimal(Math.round(Math.pow(num2.doubleValue(), num1.doubleValue())*100000000)/100000000.0);
								else {
									num1 = num1.negate();
									result = new BigDecimal(Math.round(Math.pow(num2.doubleValue(), num1.doubleValue())*100000000)/100000000.0);
								}
							}
							else if(temp_num.equals("√")) {
								result = new BigDecimal(Math.sqrt(num1.doubleValue()));
								num_stack.push(new Operand(num2.toString()));
							}
							//result.setScale(8);
							num_stack.push(new Operand(String.valueOf(result)));
						}catch(Exception e) {
							System.out.println("here3");
							show_error();
							return new BigDecimal(0);
						}
					}
					else {
						if(temp_num.equals("-")) result = BigDecimal.valueOf(Double.valueOf(num_stack.pop().get_symbol())).negate();
						else if(temp_num.equals("√")) {
							double num = Double.parseDouble(num_stack.pop().get_symbol());
							if(num>=0) result = new BigDecimal(Math.round(Math.sqrt(num)*100000000)/100000000.0);
							else {
								System.out.println("Check");
								show_error();
							}
						}
						else {
							sp.clear_screen();
							//sp.update_screen("Error!");
						}
						result.setScale(8,BigDecimal.ROUND_HALF_UP);
						num_stack.push(new Operand(String.valueOf(result)));	
					}
				}
				temp_num = "";
			}
			else temp_num += str.charAt(i);
		}
		try {
			if(minus_count%2==1) return BigDecimal.valueOf(Double.valueOf(num_stack.pop().get_symbol())).negate(); 
			else return BigDecimal.valueOf(Double.valueOf(num_stack.pop().get_symbol()));	
		}catch(Exception e) {
			System.out.println("Check1");
			show_error();
			return new BigDecimal(0);
		}
	}
	
	public boolean is_operator(char c) {
		if(c=='+'||c=='-'||c=='/'||c=='*'||c=='$'||c=='^'||c=='√'|c=='('||c==')') return true;
		else return false;
	}
	private void show_error() {
		if(status) {
			ErrorFrame ef = new ErrorFrame();
			ef.auto_exit();
		}
		status = false;
	}
	private String get_str_expression() {
		return str_expression;
	}
	private int get_minus_count() {
		return minus_count;
	}
}
