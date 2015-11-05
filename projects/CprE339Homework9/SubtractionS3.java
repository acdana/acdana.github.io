package hw9;

public class SubtractionS3 implements State {
	
	private static SubtractionS3 instance = null;
	private SubtractionS3() {
		
	}
	
	public synchronized static SubtractionS3 getInstance() {
		if(instance == null) {
			instance = new SubtractionS3();
		}
		return instance;
	}

	/**
	 * If the input is a number we can subtract the input from the
	 * running total and then set the running total to be this new total. 
	 * We then move back to state S1.
	 * Otherwise we go to the error state
	 */
	@Override
	public void handleInput(Calculator calc, String input) {

			try {
				
				if(input.equals(")")) {
					calc.setRunningTotal(calc.getRunningTotal() + calc.getParenTotal());
					calc.setInParen(false);
				}
				else if(input.equals("(")) {
					calc.setInParen(true);
				}
				else {
				
					double num = Double.parseDouble(input);
					if(!calc.isInParen()) {
						calc.setRunningTotal(calc.getRunningTotal() - num);
					}
					else {
						calc.setParenTotal(calc.getParenTotal() - num);
					}
					calc.setState(NumberS1.getInstance());
				}
			}
			catch(Exception e) {
				calc.setState(ErrorSE.getInstance());
			}
		

	}
}
