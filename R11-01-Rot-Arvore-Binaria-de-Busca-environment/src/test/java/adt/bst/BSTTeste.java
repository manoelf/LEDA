package adt.bst;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import adt.bt.BTNode;

public class BSTTeste {

	private BSTImpl<Integer> tree;
	private BTNode<Integer> NIL = new BTNode<Integer>();

	@Before
	public void setUp() {
		this.tree = new BSTImpl<>();
	}
	
	private void fillTree() {
		Integer[] array = { 6, 23, -34, 5, 9, 2, 0, 76, 12, 67, 232, -40 };
		for (int i : array) {
			tree.insert(i);
		}
	}
	
	@Test
	public void testeRemove() {
		fillTree();
		Integer[] array = {-40, -34, 0, 2, 5, 6, 9, 12, 23, 67, 76, 232};
		Assert.assertArrayEquals(array, this.tree.order());
		
		this.tree.remove(0);
		array = new Integer[] {-40, -34, 2, 5, 6, 9, 12, 23, 67, 76, 232};
		Assert.assertArrayEquals(array, this.tree.order());
		//System.out.println(Arrays.toString(array));
		//System.out.println(Arrays.toString(this.tree.order()));
		
		this.tree.remove(12);
		array = new Integer[] {-40, -34, 2, 5, 6, 9, 23, 67, 76, 232};
		Assert.assertArrayEquals(array, this.tree.order());

		this.tree.remove(2);
		array = new Integer[] {-40, -34, 5, 6, 9, 23, 67, 76, 232};
		Assert.assertArrayEquals(array, this.tree.order());
		
		this.tree.remove(-34);
		array = new Integer[] {-40, 5, 6, 9, 23, 67, 76, 232};
		Assert.assertArrayEquals(array, this.tree.order());
		
		
		
		
		this.tree.remove(232);
		array = new Integer[] {-40, 5, 6, 9, 23, 67, 76};
		Assert.assertArrayEquals(array, this.tree.order());
		Assert.assertTrue(this.tree.search(232).getData() == null);
		
		
	}
	
	
	@Test
	public void testeRemove2() {
		fillTree();
		Assert.assertTrue(this.tree.sucessor(232) == null);
		this.tree.remove(232);
		assertEquals(null, tree.search(232).getData());
		Assert.assertTrue(this.tree.search(232).getData() == null);
		
		
		this.tree.remove(9);
		Integer[] array = {-40, -34, 0, 2, 5, 6, 12, 23, 67, 76};
		Assert.assertArrayEquals(array, this.tree.order());
		
	}
	
	@Test
	public void testeRemoveRoots() {
		fillTree();
		Integer[] array = {-40, -34, 0, 2, 5, 6, 9, 12, 23, 67, 76, 232};
		Assert.assertArrayEquals(array, this.tree.order());
		
		this.tree.remove(this.tree.getRoot().getData());
		array = new Integer[]{-40, -34, 0, 2, 5, 9, 12, 23, 67, 76, 232};
		Assert.assertArrayEquals(array, this.tree.order());
		
		
		this.tree.remove(this.tree.getRoot().getData());
		array = new Integer[]{-40, -34, 0, 2, 5, 12, 23, 67, 76, 232};
		Assert.assertArrayEquals(array, this.tree.order());
		
		System.out.println(Arrays.toString(this.tree.order()));

		this.tree.remove(this.tree.getRoot().getData());
		array = new Integer[]{-40, -34, 0, 2, 5, 23, 67, 76, 232};
		System.out.println(Arrays.toString(array));
		System.out.println(Arrays.toString(this.tree.order()));
		Assert.assertArrayEquals(array, this.tree.order());
		
		
		this.tree.remove(this.tree.getRoot().getData());
		array = new Integer[]{-40, -34, 0, 2, 5, 67, 76, 232};
		Assert.assertArrayEquals(array, this.tree.order());
		
		
		this.tree.remove(this.tree.getRoot().getData());
		array = new Integer[]{-40, -34, 0, 2, 5, 76, 232};
		Assert.assertArrayEquals(array, this.tree.order());
		
		this.tree.remove(this.tree.getRoot().getData());
		array = new Integer[]{-40, -34, 0, 2, 5, 232};
		Assert.assertArrayEquals(array, this.tree.order());
		
		
		this.tree.remove(this.tree.getRoot().getData());
		array = new Integer[]{-40, -34, 0, 2, 5};
		Assert.assertArrayEquals(array, this.tree.order());		
		
		this.tree.remove(this.tree.getRoot().getData());
		array = new Integer[]{-40, 0, 2, 5};
		Assert.assertArrayEquals(array, this.tree.order());
		
		
		this.tree.remove(this.tree.getRoot().getData());
		array = new Integer[]{-40, 2, 5};
		Assert.assertArrayEquals(array, this.tree.order());
		
		this.tree.remove(this.tree.getRoot().getData());
		array = new Integer[]{-40, 5};
		Assert.assertArrayEquals(array, this.tree.order());	
		
		this.tree.remove(this.tree.getRoot().getData());
		array = new Integer[]{-40};
		Assert.assertArrayEquals(array, this.tree.order());
		
		this.tree.remove(this.tree.getRoot().getData());
		array = new Integer[]{};
		Assert.assertArrayEquals(array, this.tree.order());
	}
	
}
