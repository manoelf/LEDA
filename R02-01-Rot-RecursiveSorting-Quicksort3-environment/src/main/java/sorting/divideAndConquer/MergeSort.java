package sorting.divideAndConquer;

import java.util.Arrays;

import sorting.AbstractSorting;

/**
 * Merge sort is based on the divide-and-conquer paradigm.  
 * The algorithm consists of recursively dividing the unsorted list in the middle,
 * sorting each sublist, and then merging them into one single sorted list.
 * Notice that if the list has length == 1, it is already sorted.
 */
public class MergeSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array,int leftIndex, int rightIndex) {
		if (validation(array, leftIndex, rightIndex)) {
			mergeSort(array, leftIndex, rightIndex);
		}
	}
	
	
	public void mergeSort(T[] array, int leftIndex, int rightIndex) {
		if (leftIndex < rightIndex) {
			int middle = (rightIndex + leftIndex) / 2;
			mergeSort(array, leftIndex, middle);
			mergeSort(array, middle + 1, rightIndex);
			merge(array, leftIndex, middle, rightIndex);
		}
	}
	
	
	
	
	public void merge(T[] array, int leftIndex, int middle, int rightIndex) {
		T[] aux = Arrays.copyOf(array, array.length);
		//T[] aux = (T[]) new Comparable[array.length];
		int i = leftIndex;
		int j = middle + 1;
		int k = leftIndex;
		
	/*	for (int w = 0; w < aux.length; w++) {
			aux[w] = array[w];
		}*/
		
		while (i <= middle && j <= rightIndex) {
			if (aux[i].compareTo(aux[j]) < 0) {
				array[k] = aux[i];
				i++;
			} else {
				array[k] = aux[j];
				j++;
			}
			k++;
		}
		
		while (i <= middle) {
			array[k] = aux[i];
			i++;
			k++;
		}
		
		while (j <= rightIndex) {
			array[k] = aux[j];
			j++;
			k++;
		}
	}
	
	private boolean validation(T[] array, int leftIndex, int rightIndex) {
		if (array == null) {
			return false;
		} else if (leftIndex < 0 || rightIndex > array.length) {
			return false;
		} else if (leftIndex > rightIndex || rightIndex < leftIndex) {
			return false;
		} else if (containsNull(array)) {
			return false;
		} else {
			return true;
		}
	}


	private boolean containsNull(T[] array) {
		for (int i = 0; i < array.length; i++) {
			if (array[i] ==  null) {
				return true;
			}
		}
		return false;
	}
	
}
