package adt.avltree;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;

public class AVLTest {
	
	private AVLTree<Integer> tree;
	private BSTImpl<Integer> bst; 
	
	
	@Before
	public void setUp() {
		this.bst = new BSTImpl<>();
	}
	
	@Test
	public void testBST() {
		
		assertTrue(bst.isEmpty());
		
		bst.insert(new Integer(10));
		assertFalse(bst.isEmpty());
		
		assertEquals(new Integer(10), bst.getRoot().getData());
		assertTrue(bst.getRoot().getLeft().isEmpty()); 
		assertTrue(bst.getRoot().getRight().isEmpty());
		assertEquals(new Integer(10), bst.getRoot().getLeft().getParent().getData()); 
		assertEquals(new Integer(10), bst.getRoot().getRight().getParent().getData()); 
		assertEquals(new Integer(10), bst.search(new Integer(10)).getData());

		
		bst.insert(new Integer(20));
		
		assertEquals(new Integer(20), bst.getRoot().getRight().getData());
		assertEquals(1, bst.height());
		assertEquals(new Integer(20), bst.search(new Integer(20)).getData());
		
		assertEquals(null, bst.search(new Integer(30)));
		
		bst.insert(new Integer(30));
		bst.insert(new Integer(40));
		bst.insert(new Integer(50));
		bst.insert(new Integer(60));

		assertEquals(5, bst.height());
		assertEquals(new Integer(60), bst.maximum().getData());
		assertEquals(new Integer(10), bst.minimum().getData());
		
	
		
	
	}
}
