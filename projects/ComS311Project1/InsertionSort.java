package assignment3;

import java.util.ArrayList;
import java.util.Random;

public class InsertionSort implements SortAnalysis<Integer> {

	/**
	 * Function used to determine running time of insertion sort.
	 * 
	 * @param list	The arraylist of integers to be sorted
	 * @return a long representing the running time in milliseconds
	 */
	@Override
	public long analyzeSort(ArrayList<Integer> list) {
		long startTime = System.currentTimeMillis();
		for(int i = 1; i < list.size(); i++) {
			int k = i;
			while(k > 0 && list.get(k) < list.get(k-1)) {
				int temp = list.get(k);
				list.set(k, list.get(k-1));
				list.set(k-1, temp);
				k--;
			}
		}
		return System.currentTimeMillis()-startTime;
	}
	
	/**
	 * 
	 * @param random boolean determining whether to fill the array with random or worst case data
	 * @param size	The size of array to create
	 * @return	The generated arraylist of integers
	 */
	public ArrayList<Integer> createFilledArray(boolean random, int size) {
		ArrayList<Integer> array = new ArrayList<Integer>();
		if(random == true) {
			Random getRandom = new Random();
			for(int i = 0; i < size; i++) {
				array.add(getRandom.nextInt());
			}
		}
		else {
			for(int i = size; i > 0; i--) {
				array.add(i);
			}
		}
		return array;
	}
	
	/**
	 * Binary search function to determine the exact running time over 1000 milliseconds of our insertion sort
	 * @param min	bottom value to search from
	 * @param max	top value to search from
	 * @param randomElements determines if random elements should be used or worst case elements should be used
	 * @return	the array size needed to produce a 1000 millisecond running time
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
		InsertionSort insertionSort = new InsertionSort();
		
		//Worst Case Data
		while(timeTaken < 1000) {
			ArrayList<Integer> array = insertionSort.createFilledArray(false, size);
			timeTaken = insertionSort.analyzeSort(array);
			size *= 2;
		}
		
		
		int finalSize = insertionSort.binarySearch(size/4, size/2, false);
		System.out.println("Size to be over 1000 ms: " + finalSize);
		
		int step = finalSize/100;
		for(int i = 1; i <= 100; i++) {
			ArrayList<Integer> worstArray = insertionSort.createFilledArray(false, step*i);

			long worstTimeTaken = insertionSort.analyzeSort(worstArray);
			System.out.println(worstTimeTaken);
		}
		//End of Worst Case Data
		
		
		
		//Random Data
		while(timeTaken < 1000) {
			ArrayList<Integer> array = insertionSort.createFilledArray(true, size);
			timeTaken = insertionSort.analyzeSort(array);
			size *= 2;
		}
		

		finalSize = insertionSort.binarySearch(size/4, size/2, true);
		System.out.println("Size to be over 1000 ms: " + finalSize);
		
		step = finalSize/100;
		for(int i = 1; i <= 100; i++) {
			ArrayList<Integer> worstArray = insertionSort.createFilledArray(true, step*i);

			long worstTimeTaken = insertionSort.analyzeSort(worstArray);
			System.out.println(worstTimeTaken);
		}
		//End of Random Data
		
	}
	
	
}
