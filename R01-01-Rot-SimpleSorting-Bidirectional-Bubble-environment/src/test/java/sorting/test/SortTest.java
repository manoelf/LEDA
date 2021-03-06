package sorting.test;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;
import sorting.AbstractSorting;
import sorting.Sorting;
import sorting.simpleSorting.BubbleSort;
import sorting.simpleSorting.InsertionSort;
import sorting.simpleSorting.SelectionSort;
import sorting.variationsOfBubblesort.BidirectionalBubbleSort;

public class SortTest {

	public AbstractSorting<Integer> implementation;
	Integer[] arrayNull;
	Integer[] array;
	Integer[] arrayParcial;
	
	@Before
	public void setUp() {
		arrayNull = new Integer[] {null, null, null};
		
		implementation = new BidirectionalBubbleSort();
		
	}

	@Test
	public void arrayNullExceptions() {
		this.implementation.sort(arrayNull, 0, 2);
		this.implementation.sort(new Integer[] {null});
	}

	@Test
	public void arraySortParcial() {

		
		array = new Integer[] {4, 1, 2, 4, 6, 4, 2, 0};
		arrayParcial = new Integer[] {1, 2, 4, 4, 6, 4, 2, 0};
		
		implementation.sort(array, 0, 2);
		for (int i = 0; i <= 2; i++) {
			assertTrue(array[i] == arrayParcial[i]);
		}
	}
	
	
	@Test
	public void testSorts() {
		implementation = new BubbleSort(); 
		arrayNullExceptions();
		arraySortParcial();
		
		implementation = new SelectionSort(); 
		arrayNullExceptions();
		arraySortParcial();
		
		implementation = new InsertionSort(); 
		arrayNullExceptions();
		arraySortParcial();
		
		implementation = new BidirectionalBubbleSort(); 
		arrayNullExceptions();
		arraySortParcial();
		
		
	}
}
