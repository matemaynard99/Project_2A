
//Written by
	//Dalton Vining
	//Samuel Maynard
	//Shane Callaway

	
import java.util.*;
public class Project_2A {
	static Stack<Integer> nums = new Stack<Integer>();
	static Stack<String> operators = new Stack<String>();
		
		public static int evaluatEquation(String s) {
			s = s + " ";
			
			char[] tokenArray = s.toCharArray();
			
			
			/**The idea of this program is to combine the infixToPostfix and
			 * postfix evaluating algorithims we discussed in class
			 * as that should be more efficient than simply performing each of those equations
			 * seperately.
			 */
			//use for loop to iterate through whole equation
			for (int i = 0; i<tokenArray.length-1; i++) {
				//simple line to ignore spaces
				if (tokenArray[i] == ' ') {
					continue;
				}//end if
				
				//check if current character is a number
				if (isNumber(tokenArray[i])) {
					//System.out.println(tokenArray[i]);
					/**converted char to string so it can become integer
					 * unsure how to change char to int directly
					 */
					//added while loop to catch multidigit numbers
					String newNum = "";
					while(isNumber(tokenArray[i])){
						
						newNum = newNum + tokenArray[i];
						i++;
						//add to stack of numbers as an integer
						nums.push(Integer.parseInt(newNum));
						//System.out.println(nums);
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
					//System.out.println(operators);
				}//end open parenthesis branch
				//check for closing parenthesis
				else if(tokenArray[i] == ')') {
					//System.out.println(nums);
					while(!operators.peek().equals("(")) {
						//System.out.println(nums);
						Integer right = nums.pop();
						//System.out.println(nums);
						Integer left = nums.pop();
						nums.push(equation(left, right, operators.pop()));
						//System.out.println(operators);
							
							
						
						/**Performs operation in the parenthesis in order
						 * until it reaches the open parenthesis
						 * pushes the outcome into the numbers stack after each operation
						 */
						
					}//end while
					operators.pop();
				}//end branch for closing parenthesis
				else{
					
					String newOp = "";
					while(!isNumber(tokenArray[i]) && tokenArray[i]!= ' ' && tokenArray[i] != '(') {
						newOp = newOp + tokenArray[i];
						i++;
						
					}//end while loop for op building
					/** i has to be decrimented, since it is incremented in the while loop
					 * that way the main for loop doesnt skip any tokens in the equation
					 */
					i--;
					if(isOperator(newOp)) {
						if(operators.isEmpty()) {
							operators.push(newOp);
							//System.out.println(operators);
						}
						else {
							while(!operators.isEmpty() && (precedence(operators.peek()) > precedence(newOp))) {
								Integer right = nums.pop();
								Integer left = nums.pop();
								nums.push(equation(left, right, operators.pop()));
							}//end while loop
							operators.push(newOp);
							//System.out.println(operators);
						}
						
					}
					//while loop until you reach open parenthesis
					/**use while loop to check if current operator
					 * has the same or higher precendence to one 
					 * on top of the operators stack
					 * if so performs the operator on top of stack 
					 * 
					 * added !.operators.isEmpty(), as empty stack causes error for comparison
					 */
					
					
					//once token is either first token or highest precedence adds to stack
					
				}//end else branch for operator
			}//end for
			
			/** once the while expression is either added to stacks or is 
			 * put through needed operations solves remaining operations in
			 * the operators stack, until empty
			 */
			while (!operators.isEmpty() ) {
				//System.out.println(nums);
				Integer right = nums.pop();
				//System.out.println(nums);
				Integer left = nums.pop();
				nums.push(equation(left, right, operators.pop()));
				
				
			}
			return nums.pop();
		}//end evaluate method
	
		
			/*
			*Precedence method. This returns a value based on the operator 
			*This value represents the order of operations on the expression so the proper result can be calculated
			*Takes in the string operater and compares it using operator.equals to find the proper int value of precedence
			*@Returns: Int representing precendence 
			*/
		static public int precedence(String operator) {
			if (operator.equals("^"))                           { return 7; }
			if (operator.equals("*") || operator.equals("/"))   { return 6; }
			if (operator.equals("+") || operator.equals("-"))   { return 5; }
			if (operator.equals(">") || operator.equals(">="))  { return 4; }
			if (operator.equals("<") || operator.equals("<="))  { return 4; }
			if (operator.equals("==") || operator.equals("!=")) { return 3; }
			if (operator.equals("&&"))                          { return 2; }
			if (operator.equals("||"))                          { return 1; }		
			if (operator.contentEquals("("))					{ return 0; }
			throw new IllegalArgumentException(String.format("Operator %s is not supported.", operator));   // Time complexity: O(n)
	        }//End Precedence method
		
		static public int equation(Integer left, Integer right, String s) {
			
			switch(s) {
	        
			default: return 0;
		    case "+":
		        return(left + right);
		     	    
		    case "-":
		        return(left - right);
	        
		    case "*":
		        return(left * right);
		        
		    case "/":
		        return(left / right);
		        
		    case "^":
		        return (int) (Math.pow(left, right));
		        
		    case ">":
		        if(left > right) {
		            return 1;
		        }
		        else {
		            return 0;
		        }
		   		        
		    case "<":
		        if(left < right) {
		            return 1;
		        }
		        else {
		            return 0;
		        }
		      		        
		    case ">=":
		        if(left >= right) {
		            return 1;
		        }
		        else {
		            return 0;
		        }
		  		        
		    case "<=":
		        if(left <= right) {
		            return 1;
		        }
		        else {
		            return 0;
		        }
				        
		    case "==":
		        if(left == right) {
		            return 1;
		        }
		        else {
		            return 0;
		        }
		        		        
		    case "!=":
		        if(left != right) {
		            return 1;
		        }
		        else {
		            return 0;
		        }
		    		        
		    case "&&":
		    	return (left & right);
		        		    
		    case "||":
		    	return (left ^ right);
		        		    
			}//end switch
		}//end static method
		
		//method to check if character  is a number
		//simple method to see if char is between 1-9 and thus a number
		static public boolean isNumber(char c) {
			if(c >= '0' && c <='9')
				return true;
			else
				return false;
		}//end is number check
		/*
		*Method to check if String s is equal to any of the allowed operators for this project. 
		*Returns: True if String s is equal to one of the operators, otherwise returns false bvecause String s isnt an operator
		*/
		static public boolean isOperator(String s) {
			switch (s) {
			case "^": return true;
			case "*": return true;
			case "/": return true;
			case "+": return true;
			case "-": return true;
			case ">": return true;
			case ">=": return true;
			case "<=": return true;
			case "==": return true;
			case "!=": return true;
			case "&&": return true;
			case "||": return true;
			default: return false;
			
			}//end switch
		}//end isOperator method
		public static void main(String[] args) {
			System.out.println(evaluatEquation(" 5^2%7 && (4-4)"));
			System.out.println(evaluatEquation(" (2 >3) -2"));
			System.out.println(evaluatEquation(" (3 + 4) || 0"));

		}//end main
		
}//end class Project_2A 

