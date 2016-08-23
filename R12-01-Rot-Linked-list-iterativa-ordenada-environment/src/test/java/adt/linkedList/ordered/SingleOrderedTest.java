package adt.linkedList.ordered;

import static org.junit.Assert.*;

import java.util.Comparator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class SingleOrderedTest {
	
	private OrderedLinkedList<Integer> lista1;
	private OrderedLinkedList<Integer> lista2;

	@Before
	public void setUp() throws Exception {

		getImplementations();

		// Lista com 3 elementos.
		lista1.insert(3);
		lista1.insert(2);
		lista1.insert(1);

	}

	private void getImplementations() {
		// TODO O aluno deve ajustar aqui para instanciar sua implementação
		Comparator<Integer> comparador = null;
		lista1 = new OrderedSingleLinkedListImpl<>();
	}

	@Test
	public void test() {
		Assert.assertArrayEquals(new Integer[] { 1, 2, 3 }, lista1.toArray());	
		
		lista1.remove(1);
		Assert.assertArrayEquals(new Integer[] { 2, 3 }, lista1.toArray());
		
		lista1.remove(2);
		Assert.assertArrayEquals(new Integer[] { 3 }, lista1.toArray());
		
		lista1.remove(3);
		Assert.assertArrayEquals(new Integer[] { }, lista1.toArray());
		
		lista1.remove(0);
		Assert.assertArrayEquals(new Integer[] { }, lista1.toArray());
	}
	
	@Test
	public void testInsert() {
		OrderedDoubleLinkedListImpl<Integer> lista = new OrderedDoubleLinkedListImpl<>();
		
		lista.insert(5);
		Assert.assertArrayEquals(new Integer[] { 5 }, lista.toArray());
		Assert.assertTrue(!lista.isEmpty());
		Assert.assertEquals(1, lista.size());
		Assert.assertEquals(new Integer(5), lista.search(5));
		
		lista.insert(4);
		Assert.assertArrayEquals(new Integer[] { 4, 5 }, lista.toArray());
		Assert.assertTrue(!lista.isEmpty());
		Assert.assertEquals(2, lista.size());
		Assert.assertEquals(new Integer(4), lista.search(4));
		
		lista.insert(6);
		Assert.assertArrayEquals(new Integer[] { 4, 5, 6 }, lista.toArray());
		Assert.assertTrue(!lista.isEmpty());
		Assert.assertEquals(3, lista.size());
		Assert.assertEquals(new Integer(6), lista.search(6));
		
		lista.remove(5);
		Assert.assertArrayEquals(new Integer[] { 4, 6 }, lista.toArray());
		Assert.assertTrue(!lista.isEmpty());
		Assert.assertEquals(2, lista.size());
		Assert.assertEquals(null, lista.search(5));
		Assert.assertEquals(new Integer(4), lista.search(4));
		Assert.assertEquals(new Integer(6), lista.search(6));
		
	}

}
