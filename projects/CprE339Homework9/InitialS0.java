package hw9;

public class InitialS0 implements State {
	
	private static InitialS0 instance = null;
	private InitialS0() {
		
	}
	
	public synchronized static InitialS0 getInstance() {
		if(instance == null) {
			instance = new InitialS0();
		}
		return instance;
	}
	
	/**
	 * If the input is a number we set the running total to be that number and switch
	 * state to S1. Otherwise we move to the Error state.
	 */
	@Override
	public void handleInput(Calculator calc, String input) {

			try {
				if(input.equals("(")) {
					calc.setInParen(true);
				}
				else {
					if(!calc.isInParen()) {
						Double num = Double.parseDouble(input);
						calc.setRunningTotal(num);
						calc.setState(NumberS1.getInstance());
					}
					else {
						Double num = Double.parseDouble(input);
						calc.setParenTotal(num);
						calc.setState(NumberS1.getInstance());
					}
				}
				
			}
			catch(Exception e) {
				calc.setState(ErrorSE.getInstance());
			}
	
	}
	
	
}
