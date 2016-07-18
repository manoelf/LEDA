package sorting.implementations;

import sorting.AbstractSorting;

public class InsertionSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		insertionSort(array, leftIndex, rightIndex);
	}
	
	public void insertionSort(T[] v, int leftIndex, int rightIndex) {

	    for (int j = 1; j <= rightIndex; j++) {
	        T key = v[j];
	        int i = j-1;
	        while(i >= leftIndex && v[i].compareTo(key) > 0) {
	            v[i+1] = v[i]; 
	            i--;
	        }
	    v[i+1] = key;
	   }
	}
	
	private void swap(T[] array, int j, int i) {
		T temp = array[j];
		array[j] = array[i];
		array[i] = temp;
	}

}
