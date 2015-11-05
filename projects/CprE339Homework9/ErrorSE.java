package hw9;

public class ErrorSE implements State {

	private static ErrorSE instance = null;
	private ErrorSE() {
		
	}
	
	public synchronized static ErrorSE getInstance() {
		if(instance == null) {
			instance = new ErrorSE();
		}
		return instance;
	}

	/**
	 * If this state has been reached then an error has occured in
	 * the input. We set the 'error' boolean in calc to be true so
	 * that we can later alter input based on this.
	 */
	@Override
	public void handleInput(Calculator calc, String input) {
		calc.setError();
	}
}
