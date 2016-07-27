package sorting.divideAndConquer;

import java.util.Arrays;

import sorting.AbstractSorting;
import util.Util;

/**
 * A mediana de uma colecao de valores é o valor que divide a coleção na metade. 
 * A forma mais custosa de encontrar a mediana seria ordenar o array completo e 
 * escolher o elemento da metade. Essa abordagem limita o tempo de execucao 
 * ao tempo do algoritmo de ordenacao utilizado. Entretanto, existem outras formas 
 * de se encontrar a mediana usando-se estrategias dos algoritmos de ordenação vistos 
 * (excetuando-se mergesort e quicksort). Sabe-se que a mediana é um excelente pivot 
 * para garantir um bom tempo de execução do quicksort. 
 * 
 * Voce deve implementar o algoritmo do quicksort que seleciona a mediana dos dados 
 * a serem ordenados como pivot. Considere o comentário acima para escolher a mediana.
 * 
 * Obs: VOCE NAO PODE ORDENAR OS DADOS E ESCOLHER O ELEMENTO DO MEIO COMO MEDIANA!!!
 * Qualquer metodo auxiliar deve ser implementado nesta classe!
 * 
 */
public class QuickSortComMediana<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array,int leftIndex, int rightIndex) {
		if (validation(array, leftIndex, rightIndex)) {
			quickSortMediana(array, leftIndex, rightIndex);
		}
		
		//you must use the method orderStatistic to obtain your pivot
		// and use it in your quicksort implementation
	}
	
	
	public void quickSortMediana(T[] array,int leftIndex, int rightIndex) {
		if (leftIndex < rightIndex) {
			int pivot = partition(array, leftIndex, rightIndex);
			quickSortMediana(array, leftIndex, pivot - 1);
			quickSortMediana(array, pivot + 1, rightIndex);
		}
	}
	
	
	public int partition(T[] array,int leftIndex, int rightIndex) {
		T[] helper = Arrays.copyOf(array, rightIndex + 1);
		T pivot = findMedian(helper, leftIndex, rightIndex);
		
		System.out.println(pivot);
		int i = leftIndex;
		
		for (int j = leftIndex + 1; j <= rightIndex; j++) {
			if (array[j].compareTo(pivot) < 0) {
				i++;
				Util.swap(array, i, j);
			}
		}
		Util.swap(array, leftIndex, i);
		return i;
	}
	

	
	
	public T findMedian(T[] array,int leftIndex, int rightIndex) {
		T pivot = array[leftIndex];
		if (leftIndex < rightIndex) {
			int middle = (rightIndex + leftIndex)/2;
			findMedian(array, leftIndex, middle);
			findMedian(array, middle + 1, rightIndex);
			pivot = merge(array, leftIndex, middle, rightIndex);
		}
		return pivot;
	}
	
	
	private T merge(T[] array, int leftIndex, int middle, int rightIndex) {
		T[] helper = Arrays.copyOf(array, rightIndex + 1);;
		
		int i = leftIndex;
		int j = middle + 1;
		int k = leftIndex;
		
		while (i <= middle && j <= rightIndex) {
			if (helper[i].compareTo(helper[j]) < 0) {
				array[k] = helper[i];
				i++;
			} else {
				array[k] = helper[j];
				j++;
			}
			k++;
		}
		
		
		return array[middle];
	}


	public static void main(String[] args) {
		Integer[] array = new Integer[]  {5, 4, 3, 2, 1};
		//[4, 7, 11, 22, 23, 26, 28, 29, 30, 31]
		QuickSortComMediana<Integer> quick = new QuickSortComMediana<>();
		quick.sort(array, 0, array.length - 1);
		System.out.println(Arrays.toString(array));
	
	}
	
	public boolean validation(T[] array,int leftIndex, int rightIndex) {
		boolean isValid = true;
		if (array == null) {
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
