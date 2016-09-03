package adt.bst;

import static org.junit.Assert.*;


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
		

		this.tree.remove(this.tree.getRoot().getData());
		array = new Integer[]{-40, -34, 0, 2, 5, 23, 67, 76, 232};
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
	
	@Test
	public void testSucessorPredecessor() {
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
	public void leafsTeste() { 
		fillTree();
		Assert.assertEquals(5, this.tree.getLeafs());
		Assert.assertEquals(3, this.tree.getSinglesNodes());
		Assert.assertEquals(4, this.tree.getNodesHeight2());
		
		this.tree.remove(new Integer(-40));
		Assert.assertEquals(4, this.tree.getLeafs());
		Assert.assertEquals(4, this.tree.getSinglesNodes());
		Assert.assertEquals(3, this.tree.getNodesHeight2());
		
		this.tree.remove(new Integer(67));
		Assert.assertEquals(3, this.tree.getLeafs());
		Assert.assertEquals(5, this.tree.getSinglesNodes());
		Assert.assertEquals(2, this.tree.getNodesHeight2());

		
		this.tree.remove(new Integer(12));
		Assert.assertEquals(3, this.tree.getLeafs());
		Assert.assertEquals(4, this.tree.getSinglesNodes());
		Assert.assertEquals(2, this.tree.getNodesHeight2());
		
		this.tree.remove(new Integer(9));
		Assert.assertEquals(2, this.tree.getLeafs());
		Assert.assertEquals(5, this.tree.getSinglesNodes());
		Assert.assertEquals(1, this.tree.getNodesHeight2());
		
		this.tree.remove(new Integer(6));
		Assert.assertEquals(2, this.tree.getLeafs());
		Assert.assertEquals(4, this.tree.getSinglesNodes());
		Assert.assertEquals(1, this.tree.getNodesHeight2());

		
		this.tree.remove(new Integer(232));
		Assert.assertEquals(2, this.tree.getLeafs());
		Assert.assertEquals(3, this.tree.getSinglesNodes());
		Assert.assertEquals(1, this.tree.getNodesHeight2());

		
		this.tree.remove(new Integer(76));
		Assert.assertEquals(1, this.tree.getLeafs());
		Assert.assertEquals(4, this.tree.getSinglesNodes());
		Assert.assertEquals(0, this.tree.getNodesHeight2());

		this.tree.remove(new Integer(0));
		Assert.assertEquals(1, this.tree.getLeafs());
		Assert.assertEquals(3, this.tree.getSinglesNodes());
		Assert.assertEquals(0, this.tree.getNodesHeight2());

		this.tree.remove(new Integer(2));
		Assert.assertEquals(1, this.tree.getLeafs());
		Assert.assertEquals(2, this.tree.getSinglesNodes());
		Assert.assertEquals(0, this.tree.getNodesHeight2());
		
		this.tree.remove(new Integer(5));
		Assert.assertEquals(1, this.tree.getLeafs());
		Assert.assertEquals(1, this.tree.getSinglesNodes());
		Assert.assertEquals(0, this.tree.getNodesHeight2());

		this.tree.remove(new Integer(-34));
		Assert.assertEquals(1, this.tree.getLeafs());
		Assert.assertEquals(0, this.tree.getSinglesNodes());
		Assert.assertEquals(0, this.tree.getNodesHeight2());

		this.tree.remove(new Integer(23));
		Assert.assertEquals(0, this.tree.getLeafs());
		Assert.assertEquals(0, this.tree.getSinglesNodes());
		Assert.assertEquals(0, this.tree.getNodesHeight2());

		
	}
	
	@Test
	public void testNodesPerLevel() {
		BSTImpl<Integer> newTree = new BSTImpl<>();
		
		Assert.assertArrayEquals(new int[] {}, newTree.nodesPerLevel());
		
		
		newTree.insert(new Integer(1));
		Assert.assertArrayEquals(new int[] {1}, newTree.nodesPerLevel());
		
		
		
		fillTree();
		Assert.assertEquals(4, this.tree.height());
		Assert.assertArrayEquals(new int[] {1, 2, 4, 4, 1}, this.tree.nodesPerLevel());
		
		this.tree.insert(new Integer(-50));
		Assert.assertArrayEquals(new int[] {1, 2, 4, 5, 1}, this.tree.nodesPerLevel());
		
		this.tree.insert(new Integer(8));
		Assert.assertArrayEquals(new int[] {1, 2, 4, 6, 1}, this.tree.nodesPerLevel());
		
		this.tree.insert(new Integer(-38));
		Assert.assertArrayEquals(new int[] {1, 2, 4, 7, 1}, this.tree.nodesPerLevel());
		
		this.tree.insert(new Integer(-100));
		Assert.assertArrayEquals(new int[] {1, 2, 4, 7, 2}, this.tree.nodesPerLevel());
		
		this.tree.insert(new Integer(-45));
		Assert.assertArrayEquals(new int[] {1, 2, 4, 7, 3}, this.tree.nodesPerLevel());
		
		this.tree.insert(new Integer(-39));
		Assert.assertArrayEquals(new int[] {1, 2, 4, 7, 4}, this.tree.nodesPerLevel());
		
		this.tree.insert(new Integer(-36));
		Assert.assertArrayEquals(new int[] {1, 2, 4, 7, 5}, this.tree.nodesPerLevel());
		
		this.tree.insert(new Integer(3));
		Assert.assertArrayEquals(new int[] {1, 2, 4, 7, 6}, this.tree.nodesPerLevel());
		
		this.tree.insert(new Integer(7));
		Assert.assertArrayEquals(new int[] {1, 2, 4, 7, 7}, this.tree.nodesPerLevel());
		
		this.tree.insert(new Integer(11));
		Assert.assertArrayEquals(new int[] {1, 2, 4, 7, 8}, this.tree.nodesPerLevel());
		
		
		this.tree.insert(new Integer(13));
		Assert.assertArrayEquals(new int[] {1, 2, 4, 7, 9}, this.tree.nodesPerLevel());
		
		
		this.tree.insert(new Integer(65));
		Assert.assertArrayEquals(new int[] {1, 2, 4, 7, 10}, this.tree.nodesPerLevel());
		

		this.tree.insert(new Integer(68));
		Assert.assertArrayEquals(new int[] {1, 2, 4, 7, 11}, this.tree.nodesPerLevel());
		
		this.tree.insert(new Integer(200));
		Assert.assertArrayEquals(new int[] {1, 2, 4, 7, 12}, this.tree.nodesPerLevel());
		
		this.tree.insert(new Integer(240));
		Assert.assertArrayEquals(new int[] {1, 2, 4, 7, 13}, this.tree.nodesPerLevel());
		
	}
	
	
	
	
	@Test
	public void testBST() {
		
		fillTree();
		Assert.assertArrayEquals(new Integer[] {6, -34, 23, -40, 5, 9, 76, 2, 12, 67, 232, 0}, this.tree.bst());
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
