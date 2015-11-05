package hw9;


public class AdditionS2 implements State {
	
	private static AdditionS2 instance = null;
	private AdditionS2() {
		
	}
	
	public synchronized static AdditionS2 getInstance() {
		if(instance == null) {
			instance = new AdditionS2();
		}
		return instance;
	}

	/**
	 * If the input is a number we can add the running total to
	 * it and then set the running total to be this new total. We then
	 * move back to state S1.
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
						calc.setRunningTotal(num + calc.getRunningTotal());
					}
					else {
						calc.setParenTotal(num + calc.getParenTotal());
					}
					calc.setState(NumberS1.getInstance());
				}
				
			}
			catch(Exception e) {
				calc.setState(ErrorSE.getInstance());
			}
		

	}
}
