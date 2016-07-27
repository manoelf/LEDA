package sorting.implementation;

import java.util.Arrays;

import sorting.AbstractSorting;

public class MergeSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		mergeSort(array, leftIndex, rightIndex);
	}
	
	public void mergeSort(T[] array, int leftIndex, int rightIndex) {
		if (leftIndex < rightIndex) {
			int meio = (leftIndex + rightIndex)/2;
			mergeSort(array, leftIndex, meio);
			mergeSort(array, meio + 1, rightIndex);
			merge(array,  leftIndex, meio, rightIndex);
		}
	}

	private void merge(T[] array, int leftIndex, int meio, int rightIndex) {
		T[] helper = Arrays.copyOf(array, rightIndex + 1);
		
		int i = leftIndex;
		int j = meio + 1;
		int k = leftIndex;
		
		while (i <= meio && j <= rightIndex) {
			if (helper[i].compareTo(helper[j]) < 0) {
				array[k] = helper[i];
				i++;
			} else {
				array[k] = helper[j];
				j++;
			}
			k++;
		}
		
		while (i <= meio) {
			array[k] = helper[i];
			i++;
			k++;
		}
		
		while (j <= rightIndex) {
			array[k] = helper[j];
			j++;
			k++;
		}
		
	}

}
