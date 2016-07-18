package sorting.implementations;

import sorting.AbstractSorting;

public class SelectionSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		selectionSort(array, leftIndex, rightIndex);
	}
	
	private void selectionSort(T[] array, int leftIndex, int rightIndex) {
		for (int i = 0; i < rightIndex; i++) {
			int minor = i;
			for (int j = i + 1; j <= rightIndex; j++) {
				if (array[j].compareTo(array[minor]) < 0) {
					minor = j;
				}
			}
			swap(array, minor, i);
		}
	}

	public void swap(T[] array, int i, int j) {
		T temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

}
