package sorting.variationsOfBubblesort;

import java.awt.image.renderable.RenderableImage;
import java.util.Arrays;

import sorting.AbstractSorting;
import util.Util;

/**
 * This algorithm simulates a logical partitioning of the input array by considering 
 * different indexing, that is, the first sub-array is indexed by even elements and
 * the second sub-array is indexed by odd elements. Then, it applies a complete bubblesort
 * in the first sub-array considering neighbours (even). After that, 
 * it applies a complete bubblesort in the second sub-array considering
 * neighbours (odd).  After that, the algorithm performs a merge between elements indexed
 * by even and odd numbers.
 */
public class OddEvenBubbleSort<T extends Comparable<T>> extends AbstractSorting<T>{
	@Override
	public void sort(T[] array,int leftIndex, int rightIndex){
		if (validation(array, leftIndex, rightIndex)){
			odievenBubbleSort(array, leftIndex, rightIndex);
		}
	}
	
	
	private void odievenBubbleSort(T[] array, int leftIndex, int rightIndex) {
		boolean trocou;
		do {
			
			trocou = false;
			
			for (int i = leftIndex; i < rightIndex-1; i+=2) {
				if (array[i].compareTo(array[i+2]) >  0) {
					Util.swap(array, i, i+2);
					trocou = true;
				}
			}
			
			for (int j = leftIndex + 1; j < rightIndex-1; j+=2) {
				if (array[j].compareTo(array[j+2]) > 0) {
					Util.swap(array, j, j+2);
					trocou = true;
				}
			}
			
		} while (trocou);
		
		
		T[] aux = Arrays.copyOf(array, array.length);
		int i = leftIndex;
		int j = leftIndex+1;
		int k = leftIndex;
		
		
		while (i <= rightIndex && j <= rightIndex) {
			if (aux[i].compareTo(aux[j]) < 0) {
				array[k] = aux[i];
				i += 2;
			} else {
				array[k] = aux[j];
				j += 2;
			}
			k++;
		}
		
		while (i <= rightIndex) {
			array[k] = aux[i];
			k++;
			i += 2;
		}
		
		while (j <= rightIndex) {
			array[k] = aux[j];
			k++;
			j += 2;
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
