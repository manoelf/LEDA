package sorting.implementations;

import sorting.AbstractSorting;

public class MergeSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		mergeSort(array, leftIndex, rightIndex);
	}
	
	
	
	
	private void mergeSort(T[] array, int leftIndex, int rightIndex) {
		if (leftIndex < rightIndex) {
			int med = leftIndex + (rightIndex - leftIndex) / 2;
			mergeSort(array, leftIndex, med);
			mergeSort(array, med + 1, rightIndex);
			ordena(array, leftIndex, med, rightIndex);
		}
	}
	
	@SuppressWarnings("unchecked")
	private void ordena(T[] array, int leftIndex, int med, int rightIndex) {
		T[] listaAuxiliar = (T[]) new Comparable[rightIndex + 1];
		for (int i = leftIndex; i <= rightIndex; i ++ ){ 
			listaAuxiliar[i] = array[i];
		}
		
		
		int i = leftIndex;
		int j = med + 1;
		int k = leftIndex;
		
		
		while (i <= med && j <= rightIndex) {
			if (listaAuxiliar[i].compareTo(array[j]) <= 0) {
				array[k] = listaAuxiliar[i];
				i++;
			}else {
				array[k] = array[j];
				j++;
			}
			k++;
		}
		
		
		while (i <= med) {
			array[k] = listaAuxiliar[i];
			i++;
			k++;
		}
		
	}

/*	private void mergeSort(T[] array, int left, int high) {
		if (left < high) {
			int middle = (high + left) / 2;
			mergeSort(array, left, middle);
			mergeSort(array, middle + 1, high);
			merge(array, left, middle, high);
		}
	}

	private void merge(T[] array, int left, int middle, int high) {
		T[] aux = (T[]) new Comparable[high + 1];
		for (int w = 0; w < aux.length; w++) {
			aux[w] = array[w];
		}
		
		int i = left;
		int j = middle + 1;
		int k = left;

		while (i <= middle && j <= high) {
			if (aux[i].compareTo(aux[j]) < 0) {
				array[k] = aux[i];
				i++;
			} else {
				array[k] = aux[j];
				j++;
			}
			k++;
		}

		while (i <= middle) {
			array[k] = aux[i];
			i++;
			k++;
		}

		while (j <= high) {
			array[k] = aux[j];
			j++;
			k++;
		}

	}
	
	public <T> T[] makeArray(int size) {
		@SuppressWarnings("unchecked")
		T[] array = (T[]) new Object[size];
		return array;
	}*/
	
}
