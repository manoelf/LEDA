package testes;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import adt.bst.BSTImpl;
import adt.bst.SimpleBSTManipulationImpl;

public class simpleBSTTeste {

	SimpleBSTManipulationImpl<Integer> simpleBst;
	BSTImpl<Integer> bst;
	BSTImpl<Integer> bst2;

	@Before
	public void setUp() {
		simpleBst = new SimpleBSTManipulationImpl<>();
		bst = new BSTImpl<>();
		bst2 = new BSTImpl<>();
	}

	@Test
	public void test() {

		SimpleBSTManipulationImpl<Integer> simpleBst = new SimpleBSTManipulationImpl<>();
		BSTImpl<Integer> bst = new BSTImpl<>();
		BSTImpl<Integer> bst2 = new BSTImpl<>();

		assertTrue(simpleBst.equals(bst, bst2));
		assertTrue(simpleBst.isSimilar(bst, bst2));

		bst.insert(5);
		assertFalse(simpleBst.equals(bst, bst2));
		assertFalse(simpleBst.isSimilar(bst, bst2));
		assertEquals(new Integer(5), simpleBst.orderStatistic(bst, 1));

		
		bst.insert(4);

		bst.insert(8);
		bst.insert(2);
		bst.insert(6);
		bst.insert(18);
		bst.insert(9);
		bst.insert(20);

	//	assertEquals(new Integer(5), simpleBst.orderStatistic(bst, 3));

		
		bst2.insert(5);
		assertFalse(simpleBst.equals(bst, bst2));
		assertFalse(simpleBst.isSimilar(bst, bst2));

		bst2.insert(4);
		bst2.insert(8);
		bst2.insert(2);
		bst2.insert(6);
		bst2.insert(18);
		bst2.insert(9);
		bst2.insert(20);

		assertTrue(simpleBst.equals(bst, bst2));
		assertTrue(simpleBst.isSimilar(bst, bst2));

		bst2.insert(60);
		
		assertFalse(simpleBst.equals(bst, bst2));
		System.out.println(Arrays.toString(bst.order()));
	}

}
