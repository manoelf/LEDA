package sorting.variationsOfBubblesort;

import java.util.Arrays;

import sorting.AbstractSorting;
import util.Util;

/**
 * The implementation of the algorithm must be in-place!
 */
public class GnomeSort<T extends Comparable<T>> extends AbstractSorting<T> {
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (validation(array, leftIndex, rightIndex))
			gnome(array, leftIndex, rightIndex);
	}

	public void gnome(T[] array, int leftIndex, int rightIndex) {
		int pivot = leftIndex;
		
		while (pivot < rightIndex) {
			if (array[pivot].compareTo(array[pivot + 1]) <= 0) {
				pivot++;
			} else {
				Util.swap(array, pivot, pivot + 1);
				if (pivot > leftIndex) {
					pivot--;
				}
			}
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
			if (array[i] == null) {
				return true;
			}
		}
		return false;
	}
}
