package sorting.simpleSorting;

import sorting.AbstractSorting;
import util.Util;

/**
 * The selection sort algorithm chooses the smallest element from the array and
 * puts it in the first position. Then chooses the second smallest element and
 * stores it in the second position, and so on until the array is sorted.
 */
public class SelectionSort<T extends Comparable<T>> extends AbstractSorting<T>{

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		int minor;
		if (isAble(array, leftIndex, rightIndex)){
			for (int i = leftIndex; i <= rightIndex; i++) {
				minor = i;
				for (int j = i+1; j <= rightIndex; j++) {
					if (array[j].compareTo(array[minor]) < 0) {
						minor = j;
					}
				}
				Util.swap(array, i, minor);
			}
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
