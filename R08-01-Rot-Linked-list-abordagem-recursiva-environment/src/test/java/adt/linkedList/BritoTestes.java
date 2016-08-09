package adt.linkedList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import adt.stack.StackOverflowException;
import adt.stack.StackRecursiveDoubleLinkedListImpl;
import adt.stack.StackUnderflowException;

public class BritoTestes {

	private DoubleLinkedList<Integer> lista1;
	private DoubleLinkedList<Integer> lista2;
	private DoubleLinkedList<Integer> lista3;

	private LinkedList<Integer> minhaListaVazia;
	private LinkedList<Integer> minhaListaUnitaria;
	private LinkedList<Integer> minhaListaComDoisElementos;
	private LinkedList<Integer> minhaListaComTresElementos;
	private LinkedList<Integer> minhaListaComElementosRepetidos;
	private LinkedList<Integer> minhaListaElementosIguais;
	
	public StackRecursiveDoubleLinkedListImpl<Integer> stack1;
	public StackRecursiveDoubleLinkedListImpl<Integer> stack2;
	public StackRecursiveDoubleLinkedListImpl<Integer> stack3;
	public StackRecursiveDoubleLinkedListImpl<Integer> stack4;

	@Before
	public void setUp() throws Exception {
		getImplementations();

		// Lista com 3 elementos.
		lista1.insert(3);
		lista1.insert(2);
		lista1.insert(1);

		// Lista com 1 elemento.
		lista3.insert(1);

		minhaListaUnitaria.insert(10);

		minhaListaElementosIguais.insert(5);
		minhaListaElementosIguais.insert(5);
		minhaListaElementosIguais.insert(5);

		minhaListaComDoisElementos.insert(2);
		minhaListaComDoisElementos.insert(7);

		minhaListaComTresElementos.insert(4);
		minhaListaComTresElementos.insert(6);
		minhaListaComTresElementos.insert(3);

		minhaListaComElementosRepetidos.insert(1);
		minhaListaComElementosRepetidos.insert(9);
		minhaListaComElementosRepetidos.insert(7);
		minhaListaComElementosRepetidos.insert(1);
		minhaListaComElementosRepetidos.insert(9);
		
		stack1 = new StackRecursiveDoubleLinkedListImpl<Integer>(6);
		stack1.push(1);
		stack1.push(2);
		stack1.push(3);
 
		stack2 = new StackRecursiveDoubleLinkedListImpl<Integer>(2);
		stack2.push(1);
		stack2.push(2);
 
		stack3 = new StackRecursiveDoubleLinkedListImpl<Integer>(6);
		stack3.push(34);
		stack3.pop();
 
		stack4 = new StackRecursiveDoubleLinkedListImpl<Integer>(10);
	}

	private void getImplementations() {
		// TODO O aluno deve ajustar aqui para instanciar sua implementação
		lista1 = new RecursiveDoubleLinkedListImpl<Integer>();
		lista2 = new RecursiveDoubleLinkedListImpl<Integer>();
		lista3 = new RecursiveDoubleLinkedListImpl<Integer>();

		minhaListaVazia = new RecursiveDoubleLinkedListImpl<Integer>();
		minhaListaUnitaria = new RecursiveDoubleLinkedListImpl<Integer>();
		minhaListaElementosIguais = new RecursiveDoubleLinkedListImpl<Integer>();
		minhaListaComDoisElementos = new RecursiveDoubleLinkedListImpl<Integer>();
		minhaListaComTresElementos = new RecursiveDoubleLinkedListImpl<Integer>();
		minhaListaComElementosRepetidos = new RecursiveDoubleLinkedListImpl<Integer>();

	}

	@Test
	public void testIsEmpty() {
		Assert.assertTrue(lista2.isEmpty());

		Assert.assertFalse(lista3.isEmpty());
		lista3.removeLast();
		Assert.assertTrue(lista3.isEmpty());
		lista3.insert(1);
		Assert.assertFalse(lista3.isEmpty());
		lista3.removeFirst();
		Assert.assertTrue(lista3.isEmpty());
		lista3.insert(1);
		Assert.assertFalse(lista3.isEmpty());
		lista3.remove(1);
		Assert.assertTrue(lista3.isEmpty());
	}

	@Test
	public void testSize() {
		Assert.assertEquals(3, lista1.size());
		lista1.insert(4);
		Assert.assertEquals(4, lista1.size());
		lista1.remove(4);
		Assert.assertEquals(3, lista1.size());

		Assert.assertEquals(0, lista2.size());

		Assert.assertEquals(0, minhaListaVazia.size());
		Assert.assertEquals(1, minhaListaUnitaria.size());
		Assert.assertEquals(2, minhaListaComDoisElementos.size());
		Assert.assertEquals(3, minhaListaComTresElementos.size());
	}

	@Test
	public void testSearch() {
		Assert.assertNull(minhaListaComDoisElementos.search(9));
		Assert.assertNull(minhaListaVazia.search(9));
		Assert.assertEquals(10, (int) minhaListaUnitaria.search(10));

		Assert.assertTrue(lista1.search(1) == 1);
		Assert.assertTrue(lista1.search(2) == 2);
		Assert.assertTrue(lista1.search(3) == 3);
		Assert.assertTrue(lista1.search(4) == null);

		lista1.removeLast();
		Assert.assertTrue(lista1.search(1) == null);

		lista1.removeFirst();
		Assert.assertTrue(lista1.search(1) == null);

		Assert.assertTrue(lista2.search(1) == null);

		Assert.assertTrue(lista3.search(1) == 1);
	}

	@Test
	public void testInsert() {
		Assert.assertEquals(0, minhaListaVazia.size());
		minhaListaVazia.insert(1);
		Assert.assertEquals(1, minhaListaVazia.size());

		Assert.assertEquals(1, minhaListaUnitaria.size());
		minhaListaUnitaria.insert(3);
		Assert.assertEquals(2, minhaListaUnitaria.size());
	}

	@Test
	public void testRemove() {
		Assert.assertEquals(0, minhaListaVazia.size());
		minhaListaVazia.remove(3);
		Assert.assertEquals(0, minhaListaVazia.size());

		Assert.assertEquals(1, minhaListaUnitaria.size());
		minhaListaUnitaria.remove(10);
		Assert.assertEquals(0, minhaListaUnitaria.size());

		Assert.assertEquals(3, minhaListaElementosIguais.size());
		minhaListaElementosIguais.remove(5);
		Assert.assertEquals(2, minhaListaElementosIguais.size());

		Assert.assertTrue(lista1.size() == 3);

		lista1.remove(1);
		Assert.assertTrue(lista1.size() == 2);

		lista1.remove(10);
		Assert.assertTrue(lista1.size() == 2);

		lista1.remove(null);
		Assert.assertTrue(lista1.size() == 2);

		lista1.remove(2);
		Assert.assertTrue(lista1.size() == 1);

		lista1.remove(3);
		Assert.assertTrue(lista1.size() == 0);

	}

	@Test
	public void testToArray() {
		Assert.assertArrayEquals(new Integer[] {}, lista2.toArray());
		Assert.assertArrayEquals(new Integer[] { 3, 2, 1 }, lista1.toArray());

		Assert.assertArrayEquals(new Integer[] {}, lista2.toArray());
		Assert.assertArrayEquals(new Integer[] { 1 }, lista3.toArray());

		lista2.insert(1);
		Assert.assertArrayEquals(new Integer[] { 1 }, lista2.toArray());

		lista2.remove(1);
		Assert.assertArrayEquals(new Integer[] {}, lista2.toArray());

		lista3.removeFirst();
		Assert.assertArrayEquals(new Integer[] {}, lista3.toArray());

		lista3.insertFirst(1);
		Assert.assertArrayEquals(new Integer[] { 1 }, lista3.toArray());
		lista3.removeLast();
		Assert.assertArrayEquals(new Integer[] {}, lista3.toArray());
	}

	// Métodos de DoubleLinkedList

	@Test
	public void testInsertFirst() {
		Assert.assertTrue(lista2.size() == 0);

		lista2.insertFirst(1);
		Assert.assertTrue(lista2.size() == 1);

		lista2.remove(1);
		Assert.assertTrue(lista2.size() == 0);

		lista2.insertFirst(null);
		Assert.assertTrue(lista2.size() == 0);

		lista2.insertFirst(1);
		Assert.assertTrue(lista2.size() == 1);

		lista2.insertFirst(0);
		Assert.assertTrue(lista2.size() == 2);
	}

	@Test
	public void testRemoveFirst() {
		RecursiveDoubleLinkedListImpl<Integer> list = new RecursiveDoubleLinkedListImpl<Integer>();
		Assert.assertTrue(list.size() == 0);
		list.removeFirst();
		Assert.assertTrue(list.size() == 0);
		list.insert(4);
		Assert.assertTrue(list.size() == 1);
		list.removeFirst();
		Assert.assertTrue(list.size() == 0);
		list.insert(4);
		list.insert(1);
		Assert.assertTrue(list.size() == 2);
		list.removeFirst();
		Assert.assertTrue(list.size() == 1);
		
	}

	@Test
	public void testRemoveLast() {
		RecursiveDoubleLinkedListImpl<Integer> list = new RecursiveDoubleLinkedListImpl<Integer>();
		Assert.assertTrue(list.size() == 0);
		list.removeFirst();
		Assert.assertTrue(list.size() == 0);
		list.insert(4);
		Assert.assertTrue(list.size() == 1);
		list.removeLast();
		Assert.assertTrue(list.size() == 0);
		list.insert(4);
		list.insert(1);
		Assert.assertTrue(list.size() == 2);
		list.removeLast();
		Assert.assertTrue(list.size() == 1);
		list.insert(1);
		list.insert(7);
		Assert.assertTrue(list.size() == 3);
		list.removeLast();
		Assert.assertTrue(list.size() == 2);
	}
	
	@Test
	public void testTop() {
		Assert.assertEquals((int) 3, (int) stack1.top());
		// Assert.fail("Not implemented!");
	}
 
	@Test
	public void testIsEmptyStack() {
		Assert.assertTrue(stack4.isEmpty());
	}
 
	@Test
	public void testIsFullStack() {
		Assert.assertTrue(stack2.isFull());
	}
 
	@Test
	public void testPush() throws StackOverflowException, StackUnderflowException {
		stack2.push(null);
		stack2.pop();
		stack2.push(33);
		// Assert.fail("Not implemented!");
	}
 
	@Test(expected = StackOverflowException.class)
	public void testPushComErro() throws StackOverflowException {
		stack2.push(100);
		// Assert.fail("Not implemented!");
	}
 
	@Test
	public void testPop() throws StackUnderflowException, StackOverflowException {
		stack3.push(7);
		stack3.pop();
		// Assert.fail("Not implemented!");
	}
 
	@Test(expected = StackUnderflowException.class)
	public void testPopComErro() throws StackUnderflowException {
		stack3.pop();
		// Assert.fail("Not implemented!");
	}
 
}