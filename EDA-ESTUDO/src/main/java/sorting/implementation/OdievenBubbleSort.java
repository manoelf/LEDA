package sorting.implementation;

import sorting.AbstractSorting;
import util.Util;

public class OdievenBubbleSort<T extends Comparable<T>> extends AbstractSorting<T>  {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		// TODO Auto-generated method stub
		
	}
	
	
	public void odievenBubbleSort(T[] array, int leftIndex, int rightIndex) {
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array.length - i; j+=2) {
				if (array[j].compareTo(array[j+1]) > 0) {
					Util.swap(array, i, j);
				}
				
			}
		}
	}

}
