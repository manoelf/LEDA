package sorting.implementations;

import sorting.AbstractSorting;

public class QuickSort<T extends Comparable<T>> extends AbstractSorting<T> {

	
	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		quickSort(array, leftIndex, rightIndex);
	}

	
/*	
	public void quickSort(T[] array, int left, int high) {
		if (left <= high) {
			int pivot = partition(array, left, high);
			quickSort(array, left, pivot -1);
			System.out.println(array+ " " + pivot + 1 + "  " + high);
			quickSort(array, pivot + 1, high);
		}
	}*/
	
	
	public void quickSort(T[] array, int leftIndex, int rightIndex) {
		if (leftIndex <= rightIndex) {
			int pivot = partition(array, leftIndex, rightIndex);
			quickSort(array, leftIndex, pivot - 1);
			quickSort(array, pivot + 1, rightIndex);
		}
	}
	

	public int partition(T[] array, int leftIndex, int rightIndex) {
		int i = leftIndex + 1;
		int j = rightIndex;
		T pivot = array[leftIndex];
		while(i <= j) {        	
			if (array[i].compareTo(pivot) <= 0) {
				i++;
			} else if (array[j].compareTo(pivot) > 0) {
				j--;
			} else {
				swap(array, i, j);
			}
		} 
		swap(array, leftIndex, j);
		return j;
	}

/*	private int partition(T[] array, int left, int high) {
		T pivot = array[left];
		int i = left;
		
		for (int j = left + 1; j <= high; j++) {
			if (array[j].compareTo(pivot) <= 0) {
				i++;
				swap(array, i, j);
			}
		}
		swap(array, left, i);
		return i;
	}
	*/
	
	public void swap(T[] array, int i, int j) {
		T temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
	
	
}

