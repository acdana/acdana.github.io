package assignment3;

import java.util.Random;


public class MatrixOperations implements MatrixAnalysis {

	/**
	 * Function used to determine the running time of matrix multiplication for given matricies
	 * 
	 * @param m1	first matrix to multiply
	 * @param m2	second matrix to multiply
	 * @param m3	matrix to store the result in
	 * @return	the running time in milliseconds of the matrix multiplication
	 */
	@Override
	public long analyzeMultiply(double[][] m1, double[][] m2, double[][] m3) {
		long startTime = System.currentTimeMillis();
		
		for(int i = 0; i < m1.length; i++) {
			for(int j = 0; j < m2[i].length; j++) {
				for(int k = 0; k < m1[i].length; k++) {
					m3[i][j] += m1[i][k]*m2[k][j];
				}
			}
		}
		
		long endTime = System.currentTimeMillis();
		return endTime-startTime;
	}

	
	/**
	 * Not currently implemented
	 */
	@Override
	public long analyzeInverse(double[][] m1, double[][] m2) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	/**
	 * This helper function creates a randomly filled square matrix of the 
	 * given size.
	 * 
	 * @param size	The dimensions of the square matrix to be created/filled
	 * @return	A randomly filled square matrix of with dimensions of size
	 */
	public static double[][] createFilledSquareMatrix(int size) {
		double[][] matrix = new double[size][size];
		Random random = new Random();
		
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				matrix[i][j] = random.nextDouble() * 3 + random.nextInt() * 3;
			}
		}
		return matrix;
	}
	
	/**
	 * Binary search function to determine the exact running time over 1000 milliseconds of our matrix multiplication
	 * @param min	bottom value to search from
	 * @param max	top value to search from
	 * @return	the matrix size needed to produce a 1000 milliscond running time
	 */
	public int binarySearch(int min, int max) {
		
			int mid = ((min+max)/2);
			double[][] m1 = createFilledSquareMatrix(mid);
			double[][] m2 = createFilledSquareMatrix(mid);
			double[][] m3 = new double[mid][mid];
			long time = analyzeMultiply(m1, m2, m3);
			if(time == 1000) {
				return mid;
			}
			if (time > 1000) {
				
				m1 = createFilledSquareMatrix(mid-1);
				m2 = createFilledSquareMatrix(mid-1);
				m3 = new double[mid-1][mid-1];
				time = analyzeMultiply(m1, m2, m3);
				if(time <= 1000) {
					return mid;
				}
				else {
					return binarySearch(min, mid-1);
				}
			}
			else {
				m1 = createFilledSquareMatrix(mid+1);
				m2 = createFilledSquareMatrix(mid+1);
				m3 = new double[mid+1][mid+1];
				time = analyzeMultiply(m1, m2, m3);
				if(time >= 1000) {
					return mid+1;
				}
				else {
					return binarySearch(mid+1, max);
				}
			}
		
		
	}
	
	
	
	
	public static void main(String[] args) {
		
		long timeTaken = 0;
		int size = 1;
		MatrixOperations matrix = new MatrixOperations();
		
		while(timeTaken < 1000) {
			double[][] m1 = createFilledSquareMatrix(size);
			double[][] m2 = createFilledSquareMatrix(size);
			double[][] m3 = new double[size][size];
			timeTaken = matrix.analyzeMultiply(m1, m2, m3);
			size *= 2;
		}
		

		int finalSize = matrix.binarySearch(size/4, size/2);
		System.out.println("Size to be over 1000 ms: " + finalSize);
		
		for(int i = 1; i <= finalSize; i++) {
			double[][] m1 = createFilledSquareMatrix(i);
			double[][] m2 = createFilledSquareMatrix(i);
			double[][] m3 = new double[i][i];
			long averageTime = 0;
			for(int k = 0; k < 3; k++) {
				averageTime += matrix.analyzeMultiply(m1, m2, m3);
			}
			averageTime /= 3;

			System.out.println(averageTime);
		}
		
	}
	
	
	
}
