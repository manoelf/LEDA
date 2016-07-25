package sorting.implementation;

import java.util.Arrays;

import sorting.AbstractSorting;

public class SelectionRecursive<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		selection(array, leftIndex, rightIndex);
	}
	
	
	
	

	public int recursaoInterna(T[] array, int j, int key) {
		if (j < array.length){
			if (array[j].compareTo(array[key]) < 0) {
				key = j;
			}
			key = recursaoInterna(array, ++j, key);
		}
		return key;
	}
	
	public void recursaoExterna(T[] array, int i, int key) {
		if (i < array.length) {
			key = recursaoInterna(array, i, i);
			swap(array, i, key);
			recursaoExterna(array, ++i, key);
		}
	}
	
	public void selectionRecursive(T[] array, int left, int right) {
		recursaoExterna(array, 0, 0);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	public static void main(String[] args) {
		SelectionRecursive<Integer> sort = new SelectionRecursive<>();

		Integer[] array = new Integer[] { 5, 3, 2, 6, 786, -1, 9, 23 };

		//sort.selection(array, 0, 0);
		sort.selectionRecursive(array, 0, 0);

		System.out.println(Arrays.toString(array));

	}

	public void selection(T[] array, int left, int right) {

		for (int i = 0; i < array.length; i++) {
			int key = i;
			for (int j = i; j < array.length; j++) {
				if (array[j].compareTo(array[key]) < 0) {
					key = j;
				}
			}
			swap(array, i, key);

		}
	}

	private void swap(T[] array, int i, int key) {
		T temp = array[i];
		array[i] = array[key];
		array[key] = temp;
	}

}
