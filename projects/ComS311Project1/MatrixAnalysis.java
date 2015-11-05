package assignment3;

public interface MatrixAnalysis {
	
	/**
	 * @param double[][] m1	The first square matrix to multiply
	 * @param double[][] m2	The second square matrix to multiply
	 * @param double[][] m3	The result is placed in the square matrix m3 = m1*m2
	 * 
	 * @return long The time in the number of milliseconds for the multiple to complete
	 */
	public long analyzeMultiply(double[][] m1, double[][] m2, double[][] m3);
	
	
	/**
	 * @param double[][] m1	The square matrix to take the inverse of
	 * @param double[][] m2	The resultant inverse
	 * 
	 * @return long	The time in the number of milliseconds for the multiple to complete
	 */
	public long analyzeInverse(double[][] m1, double[][] m2);
}
