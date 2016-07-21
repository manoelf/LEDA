package sorting.variationsOfBubblesort;

import java.util.Arrays;

import sorting.AbstractSorting;
import util.Util;

/**
 * The combsort algoritm.
 */
public class CombSort<T extends Comparable<T>> extends AbstractSorting<T> {
	private final double FATOR = 1.3;

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (validation(array, leftIndex, rightIndex))
			combSort(array, leftIndex, rightIndex);
	}
	
	
	public void combSort(T[] array, int leftIndex, int rightIndex) {
		int gap = ((int)((rightIndex - leftIndex + 1)/FATOR));
	    boolean trocou;
	    
	    do {
	        if (gap > 1) {
	            gap = (int) (gap / FATOR);
	        }
	        trocou = false;
	       
	        for (int i = leftIndex; i + gap <= rightIndex; i++) {
	            if (array[i].compareTo(array[i + gap]) > 0) {
	            	Util.swap(array, i, i + gap);
	                trocou = true;
	            }
	        }
	    } while (gap > 1 || trocou);

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
