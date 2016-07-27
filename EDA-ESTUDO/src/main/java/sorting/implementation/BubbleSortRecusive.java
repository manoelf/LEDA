package sorting.implementation;


import sorting.AbstractSorting;
import util.Util;

public class BubbleSortRecusive<T extends Comparable<T>> extends AbstractSorting<T>  {
	
	
	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (validation(array, leftIndex, rightIndex))
			bubbleRecusive(array, leftIndex, rightIndex);
	}
	
	
	public void recussaoInterna(T[] array, int leftIndex,  int rightIndex) {
		if (leftIndex <= rightIndex - 1) {
			if (array[leftIndex].compareTo(array[leftIndex+1]) > 0) {
				Util.swap(array, leftIndex, leftIndex+1);
			}
			recussaoInterna(array, leftIndex + 1, rightIndex);
		}
	}
	
	public void bubbleRecusive(T[] array, int leftIndex, int rightIndex) {
		if (leftIndex <= rightIndex) {
			bubbleRecusive(array, leftIndex+1, rightIndex);
			recussaoInterna(array, leftIndex, rightIndex);
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
