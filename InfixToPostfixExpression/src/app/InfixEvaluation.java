/*
* File: InfixEvaluation.java
* Author: mbn217
* Date: 03/20/2018
* Purpose: This class will evaluate the infix and perform most 
* of the operations
*/
package app;
//Include the necessary package
import java.util.EmptyStackException;
import java.util.Stack;
import javax.swing.JOptionPane;
public class InfixEvaluation {
	//Declaring and instantionating two stacks
	Stack<String> operandStk= new Stack<String>();
	Stack<Character> operatorStk = new Stack<Character>();
	// Declare a constructor
	InfixEvaluation(){
	}
	// Method to evaluate the value entered by the user
	public int evaluateInfixExpression(String inputVal)	throws ArithmeticException{
		// Declare the boolean variable.
		boolean check = false;
		// Start the try block
		try{
			// splitting the string into characters
			String charVal[] = inputVal.split("");
			// for loop to loop through all the character of the string entered
			for (int i = 0; i < charVal.length; i++){
				// if condition to check if the character is an operand or not
				if (!charVal[i].equals("+")
						&& !charVal[i].equals("*")
						&& !charVal[i].equals("-")
						&& !charVal[i].equals("/")
						&& !charVal[i].equals("(")
						&& !charVal[i].equals(")"))
				{
					// if it is an operand push it onto the operand stack
					operandStk.push(charVal[i]);
				}
				// check if it is a left parenthesis
				else if (charVal[i].equals("(")){
					// push it onto the operator stack and the charVal at position 0.
					operatorStk.push((Character) charVal[i].charAt(0));
				}
				// check if it is a right parenthesis
				else if (charVal[i].equals(")")){
					while (operatorStk.peek() != '('){
						int a = Integer.parseInt(
								operandStk.pop());
						int b = Integer.parseInt(
								operandStk.pop());
						char operator = operatorStk.pop();
						int res = 0;
						if (operator == '+'){
							res = b + a;
							operandStk.push(res + "");
						}
						else if (operator == '-'){
							res = b - a;
							operandStk.push(res + "");
						}
						else if (operator == '*'){
							res = b * a;
							operandStk.push(res + "");
						}
						else if (operator == '/'){
							res = b / a;
							operandStk.push(res + "");
						}
					}
					operatorStk.pop();
				}
				// check whether the token is operand or not.
				else if (charVal[i].equals("+")|| charVal[i].equals("-")|| charVal[i].equals("*")|| charVal[i].equals("/")){

					while (!operatorStk.empty()&& PrecedenceCheck.operatorPrecedence(operatorStk.peek(),charVal[i].charAt(0))){
						int a = Integer.parseInt(operandStk.pop());
						int b = Integer.parseInt(operandStk.pop());
						// Storing the value from stack to a character variable
						char operator = operatorStk.pop();
						int res = 0;
						if (operator == '+'){
							res = b + a;
							operandStk.push(res + "");
						}
						else if (operator == '-'){
							res = b - a;
							operandStk.push(res + "");
						}
						else if (operator == '*'){
							res = b * a;
							operandStk.push(res + "");
						}
						else if (operator == '/')
						{
							res = b / a;
							operandStk.push(res + "");
						}
					}
					// push the value into the stack
					operatorStk.push(charVal[i].charAt(0));
				}
			}
			// while the operator stack is not empty do the while loop
			while (!operatorStk.empty()){
				// pop two operands from the stack.
				int a = Integer.parseInt(operandStk.pop());
				int b = Integer.parseInt(operandStk.pop());
				char operator = operatorStk.pop();
				int res = 0;
				if (operator == '+'){
					res = b + a;
					operandStk.push(res + "");
				}else if (operator == '-'){
					res = b - a;
					operandStk.push(res + "");
				}else if (operator == '*'){
					res = b * a;
					operandStk.push(res + "");
				}else if (operator == '/'){
					res = b / a;
					operandStk.push(res + "");
				}
			}
		}
		// Start the catch block to handle exception.
		catch (EmptyStackException e){
			// if exception occurs
			check = true;
		}
		// if condition to check if there are no exception
		if (check == false) {
			return Integer.parseInt(operandStk.pop());
		}else return -1;
	}//end of method
}//end of class
