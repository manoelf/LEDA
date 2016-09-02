package adt.avltree;

import static org.junit.Assert.*;

import org.junit.Test;

import adt.bst.BSTNode;

public class AVLTest {

	private AVLTreeImpl<Integer> tree = new AVLTreeImpl<>();
	private BSTNode<Integer> NIL = new BSTNode<Integer>();

	@Test
	public void test() {
		assertTrue(tree.isEmpty());
		assertTrue(tree.getRoot().isEmpty());
		
		
		tree.insert(5);
		assertEquals(new Integer(5), tree.getRoot().getData());
		assertTrue(tree.getRoot().getLeft().isEmpty());

		
		tree.insert(8);
		assertEquals(new Integer(8), tree.getRoot().getRight().getData());
		assertTrue(tree.getRoot().getRight().getLeft().isEmpty());
		assertTrue(tree.getRoot().getLeft().isEmpty());
		

		tree.insert(10);
		assertEquals(new Integer(10), tree.getRoot().getRight().getRight().getData());
		assertTrue(tree.getRoot().getRight().getRight().getLeft().isEmpty());
		assertEquals(new Integer(8), tree.getRoot().getRight().getData());

		
		tree.leftRotation(tree.getRoot());
		assertEquals(new Integer(5), tree.getRoot().getLeft().getData());
		assertEquals(new Integer(10),tree.getRoot().getRight().getData());
		
	}

}
