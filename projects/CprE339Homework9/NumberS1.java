package hw9;


public class NumberS1 implements State {
	
	private static NumberS1 instance = null;
	private NumberS1() {
		
	}
	
	public synchronized static NumberS1 getInstance() {
		if(instance == null) {
			instance = new NumberS1();
		}
		return instance;
	}

	/**
	 * If the input is an addition symbol we move to state S2
	 * If the input is a subtraction symbol we move to state S3
	 * Otherwise we go to the error state
	 */
	@Override
	public void handleInput(Calculator calc, String input) {

		if(input.equals("+")) {
			calc.setState(AdditionS2.getInstance());
		}
		else if(input.equals("-")) {
			calc.setState(SubtractionS3.getInstance());
		}
		else if(input.equals("*")) {
			calc.setState(MultiplicationS4.getInstance());
		}
		else if(input.equals("/")) {
			calc.setState(DivisionS5.getInstance());
		}
		else if(input.equals(")")) {
			calc.setRunningTotal(calc.getRunningTotal() + calc.getParenTotal());
			calc.setInParen(false);
		}
		else if(input.equals("(")) {
			calc.setInParen(true);
		}
		else {
			calc.setState(ErrorSE.getInstance());
		}
	

	}
}
