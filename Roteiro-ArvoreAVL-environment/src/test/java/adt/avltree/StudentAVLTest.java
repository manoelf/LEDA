package adt.avltree;
import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import adt.bst.BSTNode;

public class StudentAVLTest {

    private AVLTree<Integer> tree;
	private BSTNode<Integer> NIL = new BSTNode<Integer>();
	
	private void fillTree() {
		
		for(int i = 1; i < 11 ; i++) {
			tree.insert(i);
		}
	}

	@Before
	public void setUp() {
		tree = new AVLTreeImpl<>();
	}

	@Test
	public void testInit() {
		assertTrue(tree.isEmpty());
		assertEquals(0, tree.size());
		assertEquals(-1, tree.height());

		assertEquals(NIL, tree.getRoot());

		assertArrayEquals(new Integer[] {}, tree.order());
		assertArrayEquals(new Integer[] {}, tree.preOrder());
		assertArrayEquals(new Integer[] {}, tree.postOrder());

		assertEquals(NIL, tree.search(12));
		assertEquals(NIL, tree.search(-23));
		assertEquals(NIL, tree.search(0));

		assertEquals(null, tree.minimum());
		assertEquals(null, tree.maximum());

		assertEquals(null, tree.sucessor(12));
		assertEquals(null, tree.sucessor(-23));
		assertEquals(null, tree.sucessor(0));

		assertEquals(null, tree.predecessor(12));
		assertEquals(null, tree.predecessor(-23));
		assertEquals(null, tree.predecessor(0));
	}

	@Test
	public void testMinMax() {
		tree.insert(6);
		assertEquals(new Integer(6), tree.minimum().getData());
		assertEquals(new Integer(6), tree.maximum().getData());

		tree.insert(23);
		assertEquals(new Integer(6), tree.minimum().getData());
		assertEquals(new Integer(23), tree.maximum().getData());

		tree.insert(-34);
		assertEquals(new Integer(-34), tree.minimum().getData());
		assertEquals(new Integer(23), tree.maximum().getData());

		tree.insert(5);
		assertEquals(new Integer(-34), tree.minimum().getData());
		assertEquals(new Integer(23), tree.maximum().getData());

		tree.insert(9);
		assertEquals(new Integer(-34), tree.minimum().getData());
		assertEquals(new Integer(23), tree.maximum().getData());
	}

	@Test
	public void testSucessorPredecessor() {

		fillTree(); 

		assertEquals(null, tree.predecessor(15));
		assertEquals(new Integer(3), tree.sucessor(2).getData());

		assertEquals(new Integer(3), tree.predecessor(4).getData());
		assertEquals(new Integer(5), tree.sucessor(4).getData());
	}

	@Test
	public void testSize() {
		fillTree(); 

		int size = 10;
		assertEquals(size, tree.size());

		while(!tree.isEmpty()) {
			tree.remove(tree.getRoot().getData());
			assertEquals(--size, tree.size());
		}
	}

	//@Test
	public void testHeight() {
		fillTree(); 

		Integer[] preOrder = new Integer[] {3,1,2,7,5,4,6,8,9,10,11};
		assertArrayEquals(preOrder, tree.preOrder());
		assertEquals(3, tree.height());

		tree.remove(1);
		assertEquals(3, tree.height());

		tree.remove(2);
		assertEquals(3, tree.height());
		
		tree.remove(1);
		assertEquals(3, tree.height());
		Integer[] preOrder2 = new Integer[] {7,5,3,4,6,8,9};
		assertArrayEquals(preOrder2, tree.preOrder());
	}

	@Test
	public void testRemove() {
		fillTree(); 
		//tente remover elementos e verificar se as rotacoes produzem uma AVL correta
	}

	@Test
	public void testSearch() {

		fillTree(); 

		assertEquals(NIL, tree.search(-40));
		assertEquals(new Integer(4), tree.search(4).getData());
	}
	
	
	@Test
	public void testRemove2() {
		fillTree();
		tree.remove(new Integer(6));

		assertEquals(new Integer(8), tree.search(7).getParent().getData());// parent
		assertEquals(new Integer(7), tree.search(5).getParent().getData());// parent
		assertEquals(new Integer(7), tree.search(8).getLeft().getData());// leftNode

		tree.remove(new Integer(7));
		assertEquals(new Integer(8), tree.search(5).getParent().getData());// parent
		assertEquals(new Integer(5), tree.search(8).getLeft().getData());// leftNode

		tree.remove(new Integer(5));
		assertEquals(new Integer(9), tree.search(8).getParent().getData());// parent
		assertEquals(new Integer(4), tree.search(9).getParent().getData());// parent
		assertEquals(new Integer(8), tree.search(9).getLeft().getData());// leftNode
		assertEquals(new Integer(10), tree.search(9).getRight().getData());// rightNode

		tree.remove(new Integer(3));
		tree.remove(new Integer(1));
		tree.remove(new Integer(2));

		assertEquals(new Integer(4), tree.search(8).getParent().getData());// parent
		assertEquals(new Integer(9), tree.search(4).getParent().getData());// parent
		assertEquals(null, tree.search(9).getParent());// parent
		assertEquals(new Integer(9), tree.search(10).getParent().getData());// parent

		assertEquals(null, tree.search(8).getLeft().getData());// leftNode
		assertEquals(null, tree.search(4).getLeft().getData());// leftNode
		assertEquals(new Integer(4), tree.search(9).getLeft().getData());// leftNode
		assertEquals(null, tree.search(10).getLeft().getData());// leftNode

		assertEquals(null, tree.search(8).getRight().getData());// rightNode
		assertEquals(new Integer(8), tree.search(4).getRight().getData());// rightNode
		assertEquals(new Integer(10), tree.search(9).getRight().getData());// rightNode
		assertEquals(null, tree.search(10).getRight().getData());// rightNode

	}
}
