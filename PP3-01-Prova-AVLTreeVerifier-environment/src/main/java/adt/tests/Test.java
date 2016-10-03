package adt.tests;

import static org.junit.Assert.*;

import org.junit.Before;

import adt.avltree.AVLTreeImpl;
import adt.avltree.AVLTreeVerifierImpl;
import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bst.BSTVerifierImpl;

public class Test {

	BSTVerifierImpl<Integer> bstVerify;
	BSTImpl<Integer> bst;

	AVLTreeVerifierImpl<Integer> avlVerify;
	AVLTreeImpl<Integer> avl;
	
	BSTNode<Integer> nil;

	@Before
	public void setUp() throws Exception {
		bst = new BSTImpl<>();
		bstVerify = new BSTVerifierImpl<>(bst);

		avl = new AVLTreeImpl<>();
		avlVerify = new AVLTreeVerifierImpl<>(avl);
		
		nil = new BSTNode<Integer>();
	}

	@org.junit.Test
	public void testIsBST() {
		// Teste arvore vazia
		assertTrue(bstVerify.isBST());

		bst.insert(10);
		bst.insert(5);
		bst.insert(12);

		// Teste apos insercoes simples
		assertTrue(bstVerify.isBST());

		bst.insert(2);
		bst.insert(6);
		bst.insert(11);
		bst.insert(15);

		assertTrue(bstVerify.isBST());

		bst = new BSTImpl<>();

		for (int i = 0; i < 10; i++) {
			bst.insert(i);
		}

		// Teset apos insercoes ordenadas
		bstVerify = new BSTVerifierImpl<>(bst);
		assertTrue(bstVerify.isBST());

	}

	@org.junit.Test
	public void testIsAVL() {
		// Teste arvore vazia
		assertTrue(avlVerify.isAVLTree());

		avl.insert(1);
		avl.insert(2);
		avl.insert(3);

		// Teste Arvore apos balanceamento
		assertTrue(avlVerify.isAVLTree());

		avl = new AVLTreeImpl<>();

		for (int i = 0; i < 10; i++) {
			avl.insert(i);
		}

		// Teste apos insercao de elementos ordenados
		avlVerify = new AVLTreeVerifierImpl<>(avl);
		assertTrue(avlVerify.isAVLTree());
	}

	@org.junit.Test
	public void testIsAVLErro() {

		// Teste arvore vazia
		assertTrue(avlVerify.isAVLTree());

		avl.insert(1);
		avl.insert(2);

		
		nil.setData(new Integer(3));
		nil.setLeft(new BSTNode<Integer>());
		nil.setRight(new BSTNode<Integer>());
		
		
		avl.getRoot().getRight().setRight(nil);

		// Teste Arvore apos balanceamento
		assertFalse(avlVerify.isAVLTree());

	}

	
	@org.junit.Test
	public void testIsBSTErro() {

		bst.insert(2);
		bst.insert(1);
		bst.insert(3);
		
		bst.getRoot().getRight().setData(-10);
		
		bstVerify = new BSTVerifierImpl<>(bst);
		assertTrue(bstVerify.isBST());
		
	}
	
	
	
	
	
	
	
	
	
	
}
