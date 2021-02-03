/*
 * Class to set values of precendence for the operators in the expression strings
 */
public class Precedence {
	public static int precedence(String operator) {
		if (operator.equals("^"))                           { return 7; }
		if (operator.equals("*") || operator.equals("/"))   { return 6; }
		if (operator.equals("+") || operator.equals("-"))   { return 5; }
		if (operator.equals(">") || operator.equals(">="))  { return 4; }
		if (operator.equals("<") || operator.equals("<="))  { return 4; }
		if (operator.equals("==") || operator.equals("!=")) { return 3; }
		if (operator.equals("&&"))                          { return 2; }
		if (operator.equals("||"))                          { return 1; }		
		throw new IllegalArgumentException(String.format("Operator %s is not supported.", operator));
		    }  // Time complexity: O(n)
}
