package sorting.implementation;

import java.util.Arrays;

import sorting.AbstractSorting;
import util.Util;

public class InsertionSortRecurssive<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		//insertionIterative(array, leftIndex, rightIndex);
		if (validation(array, leftIndex, rightIndex))
			insertionRecursive(array, leftIndex, rightIndex);
	}

	public void insertionIterative(T[] array, int leftIndex, int rightIndex) {

		for (int i = leftIndex; i <= rightIndex; i++) {
			for (int j = i; j > leftIndex ; j--) {
				if (array[j].compareTo(array[j - 1]) < 0) {
					Util.swap(array, j, j - 1);
				}
			}
		}
	}
	
	
	
	
	public void recussaoInterna(T[] array, int leftIndex, int rightIndex) {
		if (rightIndex > leftIndex) {
			if (array[rightIndex].compareTo(array[rightIndex - 1]) < 0) {
				Util.swap(array, rightIndex, rightIndex-1);
			}
			
			recussaoInterna(array, leftIndex, rightIndex-1);
		}
	}
	
	public void insertionRecursive(T[] array, int leftIndex, int rightIndex) {
		if (leftIndex <= rightIndex) {
			recussaoInterna(array, leftIndex, rightIndex);
			insertionRecursive(array, leftIndex+1, rightIndex);
		}
	}
	
	

	/*
	 * Validacoes de casos excepcionais
	 */
	private boolean validation(T[] array, int leftIndex, int rightIndex) {
		if (array == null) {
			return false;
		} else if (leftIndex < 0 || rightIndex > array.length) {
			return false;
		} else if (leftIndex > rightIndex || rightIndex < leftIndex) {
			return false;
		} else if (containsNull(array)) {
			return false;
		} else if (array.length == 0) {
			return false;
		} else {
			return true;
		}
	}

	/*
	 * Validacoes de casos excepcionais
	 */
	private boolean containsNull(T[] array) {
		for (int i = 0; i < array.length; i++) {
			if (array[i] ==  null) {
				return true;
			}
		}
		return false;
	}
	
	
}
