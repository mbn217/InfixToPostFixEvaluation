/*
* File: PrecedenceCheck.java
* Author: mbn217
* Date: 03/20/2018
* Purpose: This class will evaluate which operator has high precedence  
* by calling the operatorPrecedence method in InfixEvaluation class
*/
package app;

public class PrecedenceCheck {
	
	// Mehod to check if top has high precedence than current
	static boolean operatorPrecedence(char topElement,char currentElement){
		// Declaring and initializing integer variables
		int topPrecedence = -1;
		int curPrecedence = -1;
		if (topElement == '+' || topElement == '-') topPrecedence = 0;			
		if (topElement == '*' || topElement == '/'|| topElement == '%') topPrecedence = 1;			
		if (currentElement == '+' || currentElement == '-') curPrecedence = 0;			
		if (currentElement == '*' || currentElement == '/'|| currentElement == '%') curPrecedence = 1;	
		if (topPrecedence >= curPrecedence) {
			return true;
		}else {
			return false;
		}

	}//end of method
}//end of class
