/*
* File: InfixEvaluationGUI.java
* Author: mbn217
* Date: 03/20/2018
* Purpose: This class will constrcut the Gui of the application 
* and process the evaluation based on user input
*/

package app;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
//Create the class
@SuppressWarnings("serial")
public class InfixEvaluationGUI extends JFrame implements ActionListener{
	// create txt fields
	private JTextField userInput = new JTextField(20);;
	private JTextField resultOutput = new JTextField(15);
	// Create the JLabel object
	private JLabel userInputLabel = new JLabel("Enter Infix Expression ");
	private JLabel resultOutputLabel = new JLabel("Result ",SwingConstants.CENTER);
	// create button object
	private JButton evaluateBtn = new JButton("Evaluate");
	// Create the panels
	private JPanel  oFirstPane = new JPanel(new FlowLayout());
	private JPanel oSecondPane = new JPanel(new FlowLayout());
	// Create the object of stack
	Stack<Object> stackObj = new Stack<Object>();
	//the constructor
	InfixEvaluationGUI(){
		super("Infix Expression Evaluator");
		// set the layout
		setLayout(new FlowLayout());
		// button to perform activity in actionListener
		evaluateBtn.addActionListener(this);
		// Adding all the component to the panel
		add(oFirstPane);
		add(oSecondPane);
		oFirstPane.add(userInputLabel);
		oFirstPane.add(userInput);
		oFirstPane.add(evaluateBtn);
		oSecondPane.add(resultOutputLabel);
		oSecondPane.add(resultOutput);
	}
	
	//main method
	public static void main(String args[]){
		// Create the Object of the class
		InfixEvaluationGUI infix = new InfixEvaluationGUI();
		infix.setVisible(true);
		// Set the size of applet
		infix.setSize(700, 300);
	}//end of main
	
	// Methods checks if we have extra operator in place
	boolean verifyCharactersInTheString(String inputExpression){
		// Declare an integer variable
		int index = 0;
		boolean flag = false;
		// Calculate the length of inputExpression
		int len = inputExpression.length();
		try{
			while (index < len && !flag){
				// Declare a variable
				char var = inputExpression.charAt(index);
				switch (var){
				case '(':
					// In case of left brace then push the brace in stack
					stackObj.push(new Character(var));
					break;
				// case to check right brace
				case ')':
					// pop the elements from stack
					char rBrace = (char) stackObj.pop();
					// if condition to check that we have same number of braces
					if (rBrace != '(')
						flag = true;
					break;
				}
				// incrementing the index
				index++;
			}
		}
		catch (EmptyStackException e){
			flag = true;
		}
		return stackObj.empty() && !flag;
	}
	// Action method
	public void actionPerformed(ActionEvent action)
	{
		// Create the object of class
		InfixEvaluation infixEvaluationObj = new InfixEvaluation();
		String str = userInput.getText();
		if (verifyCharactersInTheString(str)){
			try{
				int result = infixEvaluationObj.evaluateInfixExpression(str);
				if (result == -1)
					resultOutputLabel.setText("Result: Your input is not correct. Please try again");
				else
				{
					resultOutputLabel.setText("Result ");
					// Copy the text in the text field
					resultOutput.setText(Integer.toString(result));
				}
			}
			// check that value should not be divided by zero
			catch (ArithmeticException e){
				// Error popup 
				JOptionPane.showMessageDialog(this,"You can Not devide a number by zero","Error",JOptionPane.ERROR_MESSAGE);
			}
		}else {
			resultOutputLabel.setText("Result: Expresssion is not correct.");
			userInput.setText(str);
		}
	}
}