package sorting.linearSorting;

import java.util.Arrays;

import sorting.AbstractSorting;

/**
 * Classe que implementa a estratégia de Counting Sort vista em sala. Procure evitar desperdicio de 
 * memoria alocando o array de contadores com o tamanho sendo o máximo inteiro presente no array 
 * a ser ordenado.  
 * 
 */
public class CountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array,int leftIndex, int rightIndex) {
		if (validation(array, leftIndex, rightIndex)) {
			countingSort(array, leftIndex, rightIndex);
		}
	}

	
	
	
	public void countingSort(Integer[] array,int leftIndex, int rightIndex) {
		 int[] C = new int[max(array, leftIndex, rightIndex) + 1];
		
		 //Frequencia
		for (int i = leftIndex; i <= rightIndex; i++) {
			C[(int)array[i]]++;
		}

		//Acomulada
		for (int i = 1; i < C.length; i++) {
			C[i] += C[i -1];
		}

		//Sorting
		Integer[] B = new Integer[array.length];
		
		for (int i = rightIndex; i >= leftIndex; i--) {
			C[array[i]]--;
			B[C[array[i]]+leftIndex] = array[i];
		}

		//Copiando
		for (int i = leftIndex; i <= rightIndex; i++) {
			array[i] = B[i];
		}
		
	}
	
	
	/**
	 * Buscando o maior indice do array
	 */
	public int max(Integer[] array,int leftIndex, int rightIndex) {
		Integer max = array[leftIndex];
		for (int i = leftIndex; i <= rightIndex; i++) {
			if (array[i].compareTo(max) > 0) {
				max = array[i];
			}
		}
		return (int)max;
	}
	
	
	
	/*
	 * Validacoes de casos excepcionais
	 */
	private boolean validation(Integer[] array, int leftIndex, int rightIndex) {
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
	private boolean containsNull(Integer[] array) {
		for (int i = 0; i < array.length; i++) {
			if (array[i] ==  null) {
				return true;
			}
		}
		return false;
	}
	
}
