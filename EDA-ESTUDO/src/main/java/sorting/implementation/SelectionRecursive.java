package sorting.implementation;

import java.util.Arrays;

import sorting.AbstractSorting;

public class SelectionRecursive<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		recursaoExterna(array, leftIndex, rightIndex);
	}
	
	
	
	

	public int recursaoInterna(T[] array, int leftIndex, int rightIndex, int key) {
		if (leftIndex < rightIndex){
			if (array[leftIndex].compareTo(array[key]) < 0) {
				key = leftIndex;
			}
			key = recursaoInterna(array, ++leftIndex, rightIndex, key);
		}
		return key;
	}
	
	public void recursaoExterna(T[] array, int leftIndex, int rightIndex) {
		int key = leftIndex;
		if (leftIndex < rightIndex) {
			key = recursaoInterna(array, leftIndex, rightIndex, leftIndex);
			swap(array, leftIndex, key);
			recursaoExterna(array, ++leftIndex, rightIndex);
		}
	}
	
	public void selectionRecursive(T[] array, int left, int right) {
		recursaoExterna(array, 0, right);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	public static void main(String[] args) {
		SelectionRecursive<Integer> sort = new SelectionRecursive<>();

		Integer[] array = new Integer[] { 5, 3, 2, 6, 786, -1, 9, 23 };

		//sort.selection(array, 0, 0);
		sort.selectionRecursive(array, 0, array.length);

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
