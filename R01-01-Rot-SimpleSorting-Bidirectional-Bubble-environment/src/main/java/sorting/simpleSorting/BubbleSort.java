package sorting.simpleSorting;

import sorting.AbstractSorting;
import util.Util;

/**
 * The bubble sort algorithm iterates over the array multiple times, pushing big
 * elements to the right by swapping adjacent elements, until the array is
 * sorted.
 */
public class BubbleSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (isAble(array, leftIndex, rightIndex)) {
			boolean swapped = true;
		
			do {
				swapped = false;
				for (int j = leftIndex; j < rightIndex; j++) {
					if (array[j].compareTo(array[j + 1]) > 0) {
						Util.swap(array, j, j + 1);
						swapped = true;
					}
				}
			} while (swapped);
		}
	}
	
	
	/**
	 * Verificacao se contem elementos null no array
	 * @param array
	 * @return caso contenha elementos null no array falso sera retornado, caso contrario o retorno sera true.
	 */
	private boolean containsNull(T[] array) {
		for (int i = 0; i < array.length; i++) {
			if (array[i] == null) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Faz verificacoes para sabes se os dados recebidos podem ser usados 
	 * no algoritmo de ordenacao.
	 * @param array
	 * @param leftIndex
	 * @param rightIndex
	 * @return
	 */
	private boolean isAble(T[] array, int leftIndex, int rightIndex) {
		if (array == null) {
			return false;
		} else if (containsNull(array)) {
			return false;
		} else {
			return isValidInex(array.length, leftIndex, rightIndex);
		}
	}
	
	/**
	 * Verificacao dos numeros, nao podendo ser menores que zero ou maiores que o tamanho 
	 * do array. Tambem nao e permitido que o leftIndex seja maior que o rightIndex e nem que o rightIndex seja menor
	 * que  o leftIndex
	 * 
	 * @param length, tamanho do array
	 * @param leftIndex, indice que comecara a ordenacao 
	 * @param rightIndex, indice que deleimita a ordenacao
	 * @return retorno true se as entradas atendem os requisitos para que hava uma ordenacao
	 */
	private boolean isValidInex(int length, int leftIndex, int rightIndex) {
		if (leftIndex > rightIndex || rightIndex < leftIndex ) {
			return false;
		} else if (leftIndex < 0 || rightIndex < 0) {
			return false;
		} else if (leftIndex > length || rightIndex > length) {
			return false;
		} else {
			return true;
		}
	}
}
