package assignment3;

import java.util.ArrayList;

public interface SortAnalysis<E extends Comparable<? super E>> {
	
	/**
	 * Sorts the list of elements and returns the amount of time required to
	 * sort them. (in milliseconds)
	 * 
	 * @param list	A list of comparable elements to be sorted and analyzed.
	 * @return	The time required to sort the list.
	 */
	public long analyzeSort(ArrayList<E> list);
}
