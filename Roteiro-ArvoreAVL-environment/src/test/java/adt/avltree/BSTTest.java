package adt.avltree;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import adt.bst.BSTImpl;
import adt.bt.BTNode;

public class BSTTest {

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

		fillTree(); // -40 -34 0 2 5 6 9 12 23 67 76 232

		assertEquals(null, tree.predecessor(-40));
		assertEquals(new Integer(-34), tree.sucessor(-40).getData());

		assertEquals(new Integer(-40), tree.predecessor(-34).getData());
		assertEquals(new Integer(0), tree.sucessor(-34).getData());

		assertEquals(new Integer(-34), tree.predecessor(0).getData());
		assertEquals(new Integer(2), tree.sucessor(0).getData());

		assertEquals(new Integer(0), tree.predecessor(2).getData());
		assertEquals(new Integer(5), tree.sucessor(2).getData());
	}
	
	
	
	public void testSucessorPredecessor2() {
		fillTree();
		Assert.assertEquals(new Integer(9), this.tree.sucessor(this.tree.getRoot().getData()).getData());

		this.tree.remove(new Integer(23));
		Assert.assertEquals(new Integer(9), this.tree.sucessor(this.tree.getRoot().getData()).getData());

		this.tree.remove(new Integer(67));
		Assert.assertEquals(new Integer(9), this.tree.sucessor(this.tree.getRoot().getData()).getData());

		this.tree.remove(new Integer(76));
		Assert.assertEquals(new Integer(9), this.tree.sucessor(this.tree.getRoot().getData()).getData());

		this.tree.remove(new Integer(232));
		Assert.assertEquals(new Integer(9), this.tree.sucessor(this.tree.getRoot().getData()).getData());
		
		this.tree.remove(new Integer(9));
		Assert.assertEquals(new Integer(12), this.tree.sucessor(this.tree.getRoot().getData()).getData());

		this.tree.remove(new Integer(12));
		Assert.assertEquals(null, this.tree.sucessor(new Integer(6)));
		
		this.tree.remove(new Integer(6));
		Assert.assertEquals(new Integer(-34), this.tree.getRoot().getData());
		Assert.assertEquals(new Integer(0), this.tree.sucessor(this.tree.getRoot().getData()).getData());

		this.tree.remove(new Integer(0));
		Assert.assertEquals(new Integer(2), this.tree.sucessor(this.tree.getRoot().getData()).getData());

		this.tree.remove(new Integer(2));
		Assert.assertEquals(new Integer(5) , this.tree.sucessor(this.tree.getRoot().getData()).getData());

		this.tree.remove(new Integer(5));
		Assert.assertEquals(new Integer(-34), this.tree.getRoot().getData());
		
		Assert.assertEquals(null, this.tree.sucessor(this.tree.getRoot().getData()));

		this.tree.remove(new Integer(-40));
		Assert.assertEquals(null , this.tree.sucessor(this.tree.getRoot().getData()));

		this.tree.remove(new Integer(5));
		Assert.assertEquals(null, this.tree.sucessor(this.tree.getRoot().getData()));

	}
	
	@Test
	public void sucessorTest() {
		fillTree();

		assertEquals(new Integer(9), tree.sucessor(6).getData());
		assertEquals(new Integer(5), tree.predecessor(6).getData());

		assertEquals(new Integer(0), tree.sucessor(-34).getData());
		assertEquals(new Integer(-40), tree.predecessor(-34).getData());

		assertEquals(new Integer(-34), tree.sucessor(-40).getData());
		assertEquals(null, tree.predecessor(-40));

		assertEquals(new Integer(6), tree.sucessor(5).getData());
		assertEquals(new Integer(2), tree.predecessor(5).getData());

		assertEquals(new Integer(5), tree.sucessor(2).getData());
		assertEquals(new Integer(0), tree.predecessor(2).getData());

		assertEquals(new Integer(2), tree.sucessor(0).getData());
		assertEquals(new Integer(-34), tree.predecessor(0).getData());

		assertEquals(new Integer(67), tree.sucessor(23).getData());
		assertEquals(new Integer(12), tree.predecessor(23).getData());

		assertEquals(new Integer(12), tree.sucessor(9).getData());
		assertEquals(new Integer(6), tree.predecessor(9).getData());

		assertEquals(new Integer(23), tree.sucessor(12).getData());
		assertEquals(new Integer(9), tree.predecessor(12).getData());

		assertEquals(new Integer(232), tree.sucessor(76).getData());
		assertEquals(new Integer(67), tree.predecessor(76).getData());

		assertEquals(new Integer(76), tree.sucessor(67).getData());
		assertEquals(new Integer(23), tree.predecessor(67).getData());

		assertEquals(null, tree.sucessor(232));
		assertEquals(new Integer(76), tree.predecessor(232).getData());
	}
	

	@Test
	public void testOrder() {
		fillTree(); // -40 -34 0 2 5 6 9 12 23 67 76 232

		Integer[] order = { -40, -34, 0, 2, 5, 6, 9, 12, 23, 67, 76, 232 };
		//System.out.println(Arrays.toString(tree.order()));
		assertArrayEquals(order, tree.order());
	}
}
