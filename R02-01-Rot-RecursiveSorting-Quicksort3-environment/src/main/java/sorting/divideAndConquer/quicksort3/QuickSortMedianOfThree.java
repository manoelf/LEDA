package sorting.divideAndConquer.quicksort3;

import sorting.AbstractSorting;
import util.Util;

/**
 * A classe QuickSortMedianOfThree representa uma variação do QuickSort que funciona 
 * de forma ligeiramente diferente. Relembre que quando o pivô escolhido divide o 
 * array aproximadamente na metade, o QuickSort tem um desempenho perto do ótimo. 
 * Para aproximar a entrada do caso ótimo, diversas abordagens podem ser utilizadas. 
 * Uma delas é usar a mediana de 3 para achar o pivô. Essa técnica consiste no seguinte:
 * 1.	Comparar o elemento mais a esquerda, o central e o mais a direita do intervalo.
 * 2.	Ordenar os elemento, tal que: A[left] < A[center] < A[right].
 * 3.	Adotar o A[center] como pivô.
 * 4.	Colocar o pivô na penúltima posição A[right-1].
 * 5.	Aplicar o particionamento considerando o vetor menor, de A[left+1] até A[right-1].
 * 6.	Aplicar o algoritmo na metade a esquerda e na metade a direita do pivô.
 */
public class QuickSortMedianOfThree<T extends Comparable<T>> extends AbstractSorting<T>{
    
	public void sort(T[] array, int leftIndex, int rightIndex){
		if (validation(array, leftIndex, rightIndex)) {
			quickSort(array, leftIndex, rightIndex);
		}
	}
	
	
	public void quickSort(T[] array, int leftIndex, int rightIndex) {
		int middle = (leftIndex + rightIndex) / 2;
		if (leftIndex < rightIndex) {
			if (array[leftIndex].compareTo(array[rightIndex]) > 0) {
				Util.swap(array, leftIndex, rightIndex);
			}
			if (array[middle].compareTo(array[rightIndex]) > 0) {
				Util.swap(array, middle, rightIndex);
			} 
			if (array[leftIndex].compareTo(array[middle]) > 0) {
					Util.swap(array, leftIndex, middle);
			}
			
			
			Util.swap(array, middle, rightIndex - 1);
			int pivot = partition(array, leftIndex + 1, rightIndex - 1);
			quickSort(array, leftIndex, pivot - 1);
			quickSort(array, pivot + 1, rightIndex);
		}
	}

	
	public int partition(T[] array, int leftIndex, int rightIndex) {
		int i = leftIndex;
		int j = rightIndex;
		T pivot = array[leftIndex - 1];
		while(i <= j) {        	
			if (array[i].compareTo(pivot) <= 0) {
				i++;
			} else if (array[j].compareTo(pivot) > 0) {
				j--;
			} else {
				Util.swap(array, i, j);
			}
		}
		Util.swap(array, leftIndex - 1, j);
		return j;
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
	

	
}
