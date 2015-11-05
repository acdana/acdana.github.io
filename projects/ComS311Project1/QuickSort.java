package assignment3;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class QuickSort implements SortAnalysis<Integer> {

	/**
	 * This boolean variable is used to indicate if the worst case is to be considered or not.
	 * Not using this variable would require additions to analyzeSort so I decided to use it instead
	 */
	private static boolean worstCase = true;
	
	/**
	 * This function determines the running time of quicksort on some given arraylist of integers
	 * 
	 * @param	The arraylist of integers to quicksort
	 * @return	a long representing the running time of the quicksort algorithm on the array
	 */
	@Override
	public long analyzeSort(ArrayList<Integer> list) {
		long startTime = System.currentTimeMillis();
		Stack<Integer> partitionBounds = new Stack<Integer>();
		partitionBounds.push(list.size()-1);
		partitionBounds.push(0);
		if(list.size() == 0 || list.size() == 1) {
			return System.currentTimeMillis()-startTime;
		}
		while(!partitionBounds.isEmpty()) {
			int lowerBound = partitionBounds.pop();
			int upperBound = partitionBounds.pop();
			
			
			int bounds = partition(list, lowerBound, upperBound);
			
			if(bounds-1 > lowerBound) {
				partitionBounds.add(bounds-1);
				partitionBounds.add(lowerBound);
			}
			if(bounds+1 < upperBound) {
				partitionBounds.add(upperBound);
				partitionBounds.add(bounds+1);
			}
		}
		
		return System.currentTimeMillis()-startTime;
	}
	
	/**
	 * This function partitions our arraylist for quicksort and performs the swaps based on element conditions
	 * 
	 * @param array	The array to perform the partition on
	 * @param lowerBound	Where to begin the partition
	 * @param upperBound	Where to end the partition
	 * @return	the "meeting point" of upper and lower bounds such that new partitions can be created
	 */
	public int partition(ArrayList<Integer> array, int lowerBound, int upperBound) {
		
		int pivot;
		if(worstCase == true) {
			pivot = array.get(lowerBound);		
		}
		else {
			pivot = array.get((lowerBound+upperBound)/2);
		}
			
			while(true) {
				if(array.get(lowerBound) == pivot && array.get(upperBound) == pivot) {
					upperBound--;
				}
				while(array.get(lowerBound) < pivot) {
					lowerBound++;
				}
				while(array.get(upperBound) > pivot) {
					upperBound--;
				}
			
				if(lowerBound < upperBound) {
					
					swapElements(array, lowerBound, upperBound);
				}
				else {
					
					return lowerBound;
				}
			
			}	

	}
	
	/**
	 * Helper function to swap to elements in an arraylist
	 * @param arrayToSwap	the array we are swapping in
	 * @param pos1	the position of the first element to swap
	 * @param pos2	the position of the second element to swap
	 * 
	 */
	public void swapElements(ArrayList<Integer> arrayToSwap, int pos1, int pos2) {
		int temp = arrayToSwap.get(pos1);
		arrayToSwap.set(pos1, arrayToSwap.get(pos2));
		arrayToSwap.set(pos2, temp);
	}
	
	/**
	 * Function used to fill an array with either random elements or quicksort worst case elements
	 * @param random	boolean indicating if random or quicksort worst case elements should be used.
	 * @param size		the size of the array to create
	 * @return		The created array
	 */
	public ArrayList<Integer> createFilledArray(boolean random, int size) {
		ArrayList<Integer> array = new ArrayList<Integer>();
		if(random == true) {
			Random getRandom = new Random();
			for(int i = 0; i < size; i++) {
				array.add(getRandom.nextInt(1000));
			}
		}
		else {
			for(int i = 0; i < size; i++) {
				array.add(i);
			}
		}
		return array;
	}
	
	
	
	
	/**
	 * Binary search function to determine the exact running time over 1000 milliseconds of our quick sort
	 * @param min	bottom value to search from
	 * @param max	top value to search from
	 * @param randomElements determines if random elements should be used or worst case elements should be used
	 * @return	the array size needed to produce a 1000 millisecond runtime
	 */
	public int binarySearch(int min, int max, boolean randomElements) {
		ArrayList<Integer> array;
		int mid = ((min+max)/2);
		if(randomElements == false) {
			array = createFilledArray(false, mid);
		}
		else {
			array = createFilledArray(true, mid);
		}
		long time = analyzeSort(array);
		if(time == 1000) {
			return mid;
		}
		if (time > 1000) {
			
			array = createFilledArray(false, mid-1);
			
			time = analyzeSort(array);
			if(time <= 1000) {
				return mid;
			}
			else {
				if(randomElements == true) {
					return binarySearch(min, mid-1, true);
				}
				else {
					return binarySearch(min, mid-1, false);
				}
			}
		}
		else {
			array = createFilledArray(false, mid+1);
			time = analyzeSort(array);
			if(time >= 1000) {
				return mid+1;
			}
			else {
				if(randomElements == true) {
					return binarySearch(mid+1, max, true);
				}
				else {
					return binarySearch(mid+1, max, false);
				}
			}
		}
	}
	
	
	
	
	
	public static void main(String[] args) {
		long timeTaken = 0;
		int size = 1;
		QuickSort quickSort = new QuickSort();
		
		//Worst Case Data
		while(timeTaken < 1000) {
			ArrayList<Integer> array = quickSort.createFilledArray(false, size);
			//System.out.println(array.toString());
			timeTaken = quickSort.analyzeSort(array);
			System.out.println(size + " " + timeTaken);
			size *= 2;
		}
		

		int finalSize = quickSort.binarySearch(size/4, size/2, false);
		System.out.println("Size to be over 1000 ms: " + finalSize);
		
		int step = finalSize/100;
		for(int i = 1; i <= 100; i++) {
			ArrayList<Integer> worstArray = quickSort.createFilledArray(false, step*i);

			long worstTimeTaken = quickSort.analyzeSort(worstArray);
			System.out.println(worstTimeTaken);
		}
		//End of Worst Case Data

		//Start of Random Case Data
		worstCase = false;
		while(timeTaken < 1000) {
			ArrayList<Integer> array = quickSort.createFilledArray(true, size);
			//System.out.println(array.toString());
			timeTaken = quickSort.analyzeSort(array);
			System.out.println(size + " " + timeTaken);
			size *= 2;
		}
		

		finalSize = quickSort.binarySearch(size/4, size/2, true);
		System.out.println("Size to be over 1000 ms: " + finalSize);
		
		step = finalSize/100;
		for(int i = 1; i <= 100; i++) {
			ArrayList<Integer> worstArray = quickSort.createFilledArray(true, step*i);

			long worstTimeTaken = quickSort.analyzeSort(worstArray);
			System.out.println(worstTimeTaken);
		}
		//End of Random Case Data
		
	}

}
