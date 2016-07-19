package sorting.linearSorting;

import java.util.Arrays;

import sorting.AbstractSorting;

/**
 * Classe que implementa do Counting Sort vista em sala. 
 * Desta vez este algoritmo deve satisfazer os seguitnes requisitos:
 * - Alocar o tamanho minimo possivel para o array de contadores (C)
 * - Ser capaz de ordenar arrays contendo numeros negativos
 */                                                                                                                                                                                                        
public class ExtendedCountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array,int leftIndex, int rightIndex) {
		if (validation(array, leftIndex, rightIndex)) {
			countingSort(array, leftIndex, rightIndex);
		}
	}
	
	/*
	 * Aplicando a melhoria exigida (maior elemento menos menor elemento)
	 * assim, alocando um tamanho minimo para o array que contabiliza a frequencia.
	 * 
	 * Eh usado um shift para poder considerar valores negativos, fazendo um shift do modulo do menor
	 * elemento  e inserindo a partir da primeira posicao
	 */
	public void countingSort(Integer[] array, int leftIndex, int rightIndex) {
		int min = min(array, leftIndex, rightIndex);
		int max = max(array, leftIndex, rightIndex);
		
		//
		int tamanho = max - min;
		int shift = min;
		int[] frequencia = new int[tamanho +1];

		//Frequencia
		for (int i = leftIndex; i <= rightIndex; i++) {
			frequencia[array[i]-shift]++;
		}

		//Acomulativa
		for (int i = 1; i < frequencia.length; i++) {
			frequencia[i] += frequencia[i -1];
		}
		
		//Sorting
		Integer[] ordenado = new Integer[array.length];
		
		for (int i = rightIndex; i >= leftIndex; i--) {
			frequencia[array[i] - shift]--;
			ordenado[frequencia[array[i] - shift] + leftIndex] = array[i];
		}
		
		//Copia
		for (int i = leftIndex; i <= rightIndex; i++) {
			array[i] = ordenado[i];
		}
	}
	
	/*
	 * Busca o maior indice do array
	 */
	public Integer max(Integer[] array, int leftIndex, int rightIndex) {
		int max = array[leftIndex];
		
		for (int i = leftIndex; i <= rightIndex; i++) {
			if (array[i].compareTo(max) > 0) {
				max = array[i];
			}
		}
		return max;
	}

	
	/*
	 * Busca o menor indice do array
	 */
	public Integer min(Integer[] array, int leftIndex, int rightIndex) {
		int min = array[leftIndex];
		
		for (int i = leftIndex; i <= rightIndex; i++) {
			if (array[i].compareTo(min) < 0) {
				min = array[i];
			}
		}
		return min;
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
