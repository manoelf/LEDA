package sorting.divideAndConquer;

import java.util.Arrays;

import sorting.AbstractSorting;

/**
 * Merge sort is based on the divide-and-conquer paradigm.  
 * The algorithm consists of recursively dividing the unsorted list in the middle,
 * sorting each sublist, and then merging them into one single sorted list.
 * Notice that if the list has length == 1, it is already sorted.
 */
public class CountingSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array,int leftIndex, int rightIndex) {
		if (validation(array, leftIndex, rightIndex)) {
			countingSort(array, leftIndex, rightIndex);
		}
	}
	
	
	
	
	public void countingSort(T[] array,int leftIndex, int rightIndex) {
		 int[] C = new int[max(array, leftIndex, rightIndex) + 1];
		
		 //Frequencia
		for (int i = leftIndex; i < rightIndex+1; i++) {
			C[(int)array[i]]++;
		}

		//Acomulada
		for (int i = 1; i < C.length; i++) {
			C[i] += C[i -1];
		}

		//Sorting
		T[] B = (T[]) new Comparable[array.length];
		
		for (int i = rightIndex; i >= leftIndex; i--) {
			C[(int)array[i]]--;
			B[C[(int)array[i]]+leftIndex] = array[i];
		}

		//Copiando
		for (int i = leftIndex; i <= rightIndex; i++) {
			array[i] = B[i];
		}
		
	}
	
	
	
	public int max(T[] array,int leftIndex, int rightIndex) {
		T max = array[leftIndex];
		for (int i = leftIndex; i <= rightIndex; i++) {
			if (array[i].compareTo(max) > 0) {
				max = array[i];
			}
		}
		return (int)max;
	}
	
	
	
	private boolean validation(T[] array, int leftIndex, int rightIndex) {
		if (array == null) {
			return false;
		} else if (leftIndex < 0 || rightIndex > array.length) {
			return false;
		} else if (leftIndex > rightIndex || rightIndex < leftIndex) {
			return false;
		} else if (containsNull(array)) {
			return false;
		} else {
			return true;
		}
	}


	private boolean containsNull(T[] array) {
		for (int i = 0; i < array.length; i++) {
			if (array[i] ==  null) {
				return true;
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		CountingSort<Integer> counting = new CountingSort<>();
		
		
		Integer[] array = new Integer[] {3, 5, 2, 8, 9, 0, 1, 10, 2};
		int left = 0;
		int right = array.length - 1;
		
		
		Integer[] auxiliar = Arrays.copyOf(array, array.length);
		Arrays.sort(auxiliar, left, right + 1);
		System.out.println("Array:    " + Arrays.toString(array));
		
		System.out.println("Esperado: " + Arrays.toString(auxiliar));
		
		counting.sort(array, left, right);
		
		System.out.println("Obtido:   " + Arrays.toString(array));

	}
	
}
