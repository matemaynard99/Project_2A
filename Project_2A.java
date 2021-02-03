import java.io.*;
import java.util.*;

public class Project_2A {


	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		/*
		 * Reads from expressions.txt file
		 */
		FileReader fr = new FileReader("expressions.txt");
		BufferedReader br = new BufferedReader(fr);
		String str = "", l = "";
		while((l=br.readLine())!=null) { 
		    str += l;
		}
		br.close();
		/*
		 * Slits each separate expression based on ";" character
		 * Adds each expression to array of type String
		 */
		String[] arrExpressions = str.split(";");	

		/*
		 * Each expression stored in the array is edited to remove all whitespace
		 */
		for (int j = 0; j < arrExpressions.length; j++) {
			arrExpressions[j] = arrExpressions[j].replaceAll("\\s", "");
		}
		
		
		/*
		 * FOR TESTING ONLY
		 * Prints each element of the array
		 * In this case, its an array of strings containing each different expression
		 */		
		System.out.println("");
		//for (int i = 0; i < arrExpressions.length; i++) {
		//	System.out.println(Eval.evaluate(arrExpressions[i]) + "\n");
		//}
		String testExpression = "1 + 3 - 5";
		System.out.println(Eval.evaluate(testExpression));

	}

}
