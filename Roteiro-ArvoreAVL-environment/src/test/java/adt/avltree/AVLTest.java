package adt.avltree;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

import adt.bst.BSTNode;

public class AVLTest {
	
    private AVLTree<Integer> tree;
	private BSTNode<Integer> NIL = new BSTNode<Integer>();
	

	@Before
	public void setUp() {
		tree = new AVLTreeImpl<>();
	}
	
	@Test
	public void testRightRotation() {
		this.tree.insert(new Integer(0));
		this.tree.insert(new Integer(1));
		this.tree.insert(new Integer(2));
		this.tree.insert(new Integer(3));
		
		
	
	}
}
