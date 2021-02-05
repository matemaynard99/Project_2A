//Written by
//Dalton Vining
//Samuel Maynard
//Shane Callaway

package Project_2A;
import java.util.*;
public class Project_2A {
	
	public int evaluatEquation(String s) {
		Stack<Integer> nums = new Stack<Integer>();
		Stack<String> operators = new Stack<String>();
		char[] tokenArray = s.toCharArray();
		
		
		/**The idea of this program is to combine the infixToPostfix and
		 * postfix evaluating algorithims we discussed in class
		 * as that should be more efficient than simply performing each of those equations
		 * seperately.
		 */
		//use for loop to iterate through whole equation
		for (int i = 0; i<tokenArray.length; i++) {
			//simple line to ignore spaces
			if (tokenArray[i] == ' ') {
				continue;
			}//end if
			
			//check if current character is a number
			if (isNumber(tokenArray[i])) {
				/**converted char to string so it can become integer
				 * unsure how to change char to int directly
				 */
				//added while loop to catch multidigit numbers
				while(isNumber(tokenArray[i])){
					String newNum = "";
					newNum = newNum + tokenArray[i];
					i++;
					//add to stack of numbers as an integer
					nums.push(Integer.parseInt(newNum));
				}//end while
				/** i has to be decrimented, since it is incremented in the while loop
				 * that way the main for loop doesnt skip any tokens in the equation
				 */
				i--;
			}//end if
			//check if open parenthesis
			else if(tokenArray[i] == '(') {
				String newOp = "";
				newOp = newOp + tokenArray[i];
				operators.push(newOp);
			}//end open parenthesis branch
			//check for closing parenthesis
			else if(tokenArray[i] == ')') {
				
				while(operators.peek() != ")") {
					/**Performsoperation in the parenthesis in order
					 * until it reaches the open parenthesis
					 * pushes the outcome into the numbers stack after each operation
					 */
					Integer right = nums.pop();
					Integer left = nums.pop();
					nums.push(equation(left, right, operators.pop()));
				}//end while
			}//end branch for closing parenthesis
			else if(isOperator(tokenArray[i])) {
				/**while loop to find multipart operators
				 * and convert them to a string
				 */
				string newOp = "";
				while(isOperator(tokenArray[i])) {
					newOp = newOp + tokenArray[i];
					i++;
					
				}//end while loop for op building
				/** i has to be decrimented, since it is incremented in the while loop
				 * that way the main for loop doesnt skip any tokens in the equation
				 */
				i--;
				
				//while loop until you reach open parenthesis
				/**use while loop to check if current operator
				 * has the same or higher precendence to one 
				 * on top of the operators stack
				 * if so performs the operator on top of stack 
				 * 
				 * added !.operators.isEmpty(), as empty stack causes error for comparison
				 */
				while(!operators.isEmpty() && (precedence(operators.peek()) > precedence(tokenArray[i]))) {
					Integer right = nums.pop();
					Integer left = nums.pop();
					nums.push(equation(left, right, operators.pop()));
				}//end while loop
				
				//once token is either first token or highest precedence adds to stack
				operators.push(tokenArray[i]);
			}//end else if branch for operator
		}//end for
		
		/** once the while expression is either added to stacks or is 
		 * put through needed operations solves remaining operations in
		 * the operators stack, until empty
		 */
		while (!operators.isEmpty()) {
			Integer right = nums.pop();
			Integer left = nums.pop();
			nums.push(equation(left, right, operators.pop()));
			
			return nums.pop();
		}
	}//end evaluate method
	
		/*
		*Precedence method. This returns a value based on the operator 
		*This value represents the order of operations on the expression so the proper result can be calculated
		*Takes in the string operater and compares it using operator.equals to find the proper int value of precedence
		*@Returns: Int representing precendence 
		*/
	public int precedence(String operator) {
		if (operator.equals("^"))                           { return 7; }
		if (operator.equals("*") || operator.equals("/"))   { return 6; }
		if (operator.equals("+") || operator.equals("-"))   { return 5; }
		if (operator.equals(">") || operator.equals(">="))  { return 4; }
		if (operator.equals("<") || operator.equals("<="))  { return 4; }
		if (operator.equals("==") || operator.equals("!=")) { return 3; }
		if (operator.equals("&&"))                          { return 2; }
		if (operator.equals("||"))                          { return 1; }		
		throw new IllegalArgumentException(String.format("Operator %s is not supported.", operator)) }  // Time complexity: O(n)
        }//End Precedence method
	
	public int equation(Integer left, Integer right, String s) {
		/**
		 * FIX ME
		 */
	}
	
	//method to check if character  is a number
	//simple method to see if char is between 1-9 and thus a number
	public boolean isNumber(char c) {
		if(c >= '0' && c <='9')
			return true;
		else
			return false;
	}//end is number check
	/*
	*Method to check if String s is equal to any of the allowed operators for this project. 
	*Returns: True if String s is equal to one of the operators, otherwise returns false bvecause String s isnt an operator
	*/
	public boolean isOperator(String s) {
		if (s.equals("^" || "*" || "/" || "+" || "-" || ">" || ">=" || "<" || "<=" || "==" || "!=" || "&&" || "||"){
			return true;
		}else {return false;}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

