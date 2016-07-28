package sorting.divideAndConquer;

import java.util.Arrays;

import sorting.AbstractSorting;
import util.Util;

/**
 * A mediana de uma colecao de valores é o valor que divide a coleção na metade.
 * A forma mais custosa de encontrar a mediana seria ordenar o array completo e
 * escolher o elemento da metade. Essa abordagem limita o tempo de execucao ao
 * tempo do algoritmo de ordenacao utilizado. Entretanto, existem outras formas
 * de se encontrar a mediana usando-se estrategias dos algoritmos de ordenação
 * vistos (excetuando-se mergesort e quicksort). Sabe-se que a mediana é um
 * excelente pivot para garantir um bom tempo de execução do quicksort.
 * 
 * Voce deve implementar o algoritmo do quicksort que seleciona a mediana dos
 * dados a serem ordenados como pivot. Considere o comentário acima para
 * escolher a mediana.
 * 
 * Obs: VOCE NAO PODE ORDENAR OS DADOS E ESCOLHER O ELEMENTO DO MEIO COMO
 * MEDIANA!!! Qualquer metodo auxiliar deve ser implementado nesta classe!
 * 
 */
public class QuickSortComMediana<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (validation(array, leftIndex, rightIndex)) {
			quickSortMediana(array, leftIndex, rightIndex);
		}
	}
	
	public void quickSortMediana(T[] array, int leftIndex, int rightIndex) {
		if (leftIndex < rightIndex) {
			int pivot = partition(array, leftIndex, rightIndex);
			quickSortMediana(array, leftIndex, pivot - 1);
			quickSortMediana(array, pivot + 1, rightIndex);
		}
	}

	public int partition(T[] array, int leftIndex, int rightIndex) {
		int swapped = findMedian(array, leftIndex, rightIndex);
		T pivot = array[swapped];
		int i = leftIndex;

		for (int j = leftIndex; j <= rightIndex; j++) {
			if (array[j].compareTo(pivot) < 0) {
				Util.swap(array, i, j);
				i++;
			}
		}

		Util.swap(array, swapped, i);
		return i;
	}


	public int findMedian(T[] array, int leftIndex, int rightIndex) {
		T[] helper = Arrays.copyOf(array, rightIndex + 1);
		int middle = (rightIndex + leftIndex) / 2;

		int key = leftIndex;
		for (int i = leftIndex; i <= middle; i++) {
			key = i;
			for (int j = i + 1; j <= rightIndex; j++) {
				if (helper[j].compareTo(helper[key]) < 0)
					key = j;
			}
			Util.swap(helper, key, i);
		}
		return key;
	}

	public boolean validation(T[] array, int leftIndex, int rightIndex) {
		boolean isValid = true;
		if (array == null) {
			isValid = false;
		} else if (array.length == 0) {
			isValid = false;
		} else if (leftIndex > rightIndex) {
			isValid = false;
		} else if (rightIndex < leftIndex) {
			isValid = false;
		} else if (rightIndex > array.length) {
			isValid = false;
		} else if (leftIndex < 0) {
			isValid = false;
		} else {
			for (int i = leftIndex; i <= rightIndex; i++) {
				if (array[i] == null) {
					isValid = false;
				}
			}
		}

		return isValid;
	}

}
