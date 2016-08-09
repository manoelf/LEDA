package adt.linkedList;

import static org.junit.Assert.*;

import org.junit.*;

public class RafaelTeste {
	RecursiveDoubleLinkedListImpl<Integer> list;

	@Before
	public void setUp() {
		list = new RecursiveDoubleLinkedListImpl<Integer>();
	}

	@Test
	public void testInsert() {
		list.insert(999);
		Assert.assertEquals(1, list.size(), 0.001);
		Assert.assertEquals(999, list.search(999), 0.001);
		list.setData(888);
		Assert.assertEquals(null, list.search(999));
		list.insert(999);
		Assert.assertEquals(2, list.size(), 0.001);
		Assert.assertEquals(999, list.search(999), 0.001);
	}

	@Test
	public void testRemove() {
		Assert.assertTrue(list.isEmpty());
		list.insert(5);
		Assert.assertEquals(1, list.size(), 0.001);
		list.insert(50);
		Assert.assertEquals(2, list.size(), 0.001);
		list.remove(60);
		Assert.assertEquals(2, list.size(), 0.001);
		list.remove(50);
		Assert.assertEquals(1, list.size(), 0.001);
		Assert.assertEquals(null, list.search(50));
		list.remove(5);
		Assert.assertEquals(0, list.size(), 0.001);
		Assert.assertEquals(null, list.search(5));
	}

	@Test
	public void testInsertFirst() {
		Assert.assertTrue(list.isEmpty());
		list.insertFirst(5);
		Assert.assertEquals(1, list.size(), 0.001);
		list.insertFirst(9);
		Assert.assertEquals(2, list.size(), 0.001);
		list.insertFirst(8);
		Assert.assertEquals(3, list.size(), 0.001);
		Integer[] array = new Integer[3];
		array[0] = 8;
		array[1] = 9;
		array[2] = 5;
		Assert.assertArrayEquals(array, list.toArray());
		list.insert(66);
		list.insertFirst(-1);
		array = new Integer[5];
		array[0] = -1;
		array[1] = 8;
		array[2] = 9;
		array[3] = 5;
		array[4] = 66;
		Assert.assertArrayEquals(array, list.toArray());
	}

	@Test
	public void testRemoveFirst() {
		list.removeFirst();
		list.insert(9);
		list.removeFirst();
		Integer[] array = new Integer[0];
		Assert.assertArrayEquals(array, list.toArray());
		list.insertFirst(2);
		array = new Integer[1];
		array[0] = 2;
		Assert.assertArrayEquals(array, list.toArray());
		list.removeFirst();
		array = new Integer[0];
		Assert.assertArrayEquals(array, list.toArray());

		array = new Integer[4];
		int valor = 6;
		for (int i = 0; i < array.length; i++) {
			array[i] = valor;
			list.insert(valor - 1);
			valor++;
		}
		list.insert(valor - 1);
		list.removeFirst();
		Assert.assertArrayEquals(array, list.toArray());
	}

	@Test
	public void testRemoveLast() {
		list.removeLast();
		Integer[] array = new Integer[0];
		list.insert(3);
		list.removeLast();
		Assert.assertArrayEquals(array, list.toArray());
		array = new Integer[2];
		array[0] = 2;
		array[1] = 9;
		list.insert(2);
		list.insert(9);
		list.insert(10);
		list.removeLast();
		Assert.assertArrayEquals(array, list.toArray());
		list.removeLast();
		list.removeLast();
		for (int i = 0; i < 10; i++)
			list.insert(i);

		array = new Integer[list.size()];

		for (int i = 0; i < array.length; i++)
			array[i] = i;

		Assert.assertArrayEquals(array, list.toArray());

		array = new Integer[list.size() - 1];
		list.removeLast();
		for (int i = 0; i < array.length; i++)
			array[i] = i;

		Assert.assertArrayEquals(array, list.toArray());

	}

}