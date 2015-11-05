package hw9;

import java.util.ArrayList;

public class Calculator {
	
	private State currentState;
	private double runningTotal;
	private double parenTotal;
	private boolean error;
	private boolean inParen;
	
	public Calculator() {
		currentState = InitialS0.getInstance();
		runningTotal = 0;
		error = false;
		setInParen(false);
	}
	
	/**
	 * Function used to handle and calculate total based on string input
	 * @param input a string representing calculator input
	 * @return a string indicating the result (castable to integer) or an error message
	 */
	public String analyzeInput(String input) {
		ArrayList<String> parsedInput = inputParser(input);
		for(int i = 0; i < parsedInput.size(); i++) {
			currentState.handleInput(this, parsedInput.get(i));
		}
		if(error == true) {
			return "Invalid Input. Only positive integers, addition, subtraction, and spaces allowed.";
		}
		else {
			return Double.toString(runningTotal);
		}
	}
	
	public void setState(State newState) {
		this.currentState = newState;
	}

	public double getRunningTotal() {
		return runningTotal;
	}
	
	public void setRunningTotal(Double newTotal) {
		runningTotal = newTotal;
	}
	
	/**
	 * error is set to true when the Error state is entered
	 * so that we may later check the appropriate output
	 */
	public void setError() {
		error = true;
	}
	
	
	/**
	 * This helper function is used to parse an input string into an arraylist
	 * that is split by whole numbers and symbols. White space is left out.
	 * 
	 * @param input A string expected to contain calculator operations
	 * @return An arraylist of strings representing individual instructions for us to handle
	 */
	public ArrayList<String> inputParser(String input) {
		ArrayList<String> parsedInput = new ArrayList<String>();
		char[] rawInput = input.toCharArray();
		String temp = "";
		for(int i = 0; i < rawInput.length; i++) {
			if(Character.isDigit(rawInput[i]) || rawInput[i] == '.') {
				temp = temp.concat(Character.toString(rawInput[i]));
				if(i == rawInput.length-1) {
					parsedInput.add(temp);
				}
			}
			else if(rawInput[i] == ' ') {
				
			}
			else {
				if(temp.equals("")) {
					temp = temp.concat(Character.toString(rawInput[i]));
						parsedInput.add(temp);
						temp = "";
				}
				else {
					parsedInput.add(temp);
					temp = "";
					i--;
				}
			}
		}
		return parsedInput;
	}

	public double getParenTotal() {
		return parenTotal;
	}

	public void setParenTotal(double parenTotal) {
		this.parenTotal = parenTotal;
	}
	
	
	public boolean isInParen() {
		return inParen;
	}

	public void setInParen(boolean inParen) {
		this.inParen = inParen;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	public static void main(String[] args) {
		
		Calculator calc = new Calculator();
		String test = "(2+3)*4";
		System.out.println(calc.inputParser(test).toString());
		System.out.println(calc.analyzeInput(test));
		
	}


	
	
	
	
	
	
	
	
	
	
	
}
