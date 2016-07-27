package sorting.implementation;

import java.util.Arrays;

import sorting.AbstractSorting;
import util.Util;

public class SelectionSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		selectionSort(array, leftIndex, rightIndex, leftIndex);
	}
	
	
	public int recursaoInterna(T[] array, int leftIndex, int rightIndex, int key){
		if (leftIndex < rightIndex) {
			if (array[leftIndex].compareTo(array[key]) > 0) {
				key = leftIndex;
			}
			recursaoInterna(array, leftIndex+1, rightIndex, key);
		}
		
		return key;
	}
	
	public void selectionSort(T[] array, int leftIndex, int rightIndex, int key) {
		if (leftIndex < rightIndex) {
			key = recursaoInterna(array, leftIndex, rightIndex, key);
			Util.swap(array, leftIndex, key);
			selectionSort(array, leftIndex+1, rightIndex, key);
		}
	}
	

	
	public static void main(String[] args) {
		Integer[] array = new Integer[] {45, 2, 674, 1, 6,8,-1};
		
		SelectionSort<Integer> insertion = new SelectionSort<>();
		
		insertion.sort(array, 0, array.length - 1);
		
		System.out.println(Arrays.toString(array));
	}
	

}
