package adt.linkedList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import adt.queue.QueueDoubleLinkedListImpl;
import adt.queue.QueueOverflowException;
import adt.queue.QueueUnderflowException;

public class ItaloTesteQueue {

	public QueueDoubleLinkedListImpl<Integer> queue1;
	public QueueDoubleLinkedListImpl<Integer> queue2;
	public QueueDoubleLinkedListImpl<Integer> queue3;

	
	
	@Before
	public void setUp() throws QueueOverflowException{
		queue1 = new QueueDoubleLinkedListImpl<Integer>(6);
		queue1.enqueue(1);
		queue1.enqueue(2);
		queue1.enqueue(3);
		
		queue2 = new QueueDoubleLinkedListImpl<Integer>(2);
		queue2.enqueue(1);
		queue2.enqueue(2);
		
		queue3 = new QueueDoubleLinkedListImpl<Integer>(3);
	}
	
	//MÉTODOS DE TESTE
	@Test
	public void testHead() {
		try {
			// check if 1 is on head of queue 1
			Assert.assertTrue(queue1.head() == 1); 
			// add 4 at queue 1
			queue1.enqueue(4); 
			// check if head is still 1
			Assert.assertTrue(queue1.head() == 1); 
			// remove one element from queue 1
			queue1.dequeue(); 
			// check if head is no longer 1 
			Assert.assertFalse(queue1.head() == 1); 

			queue2.dequeue();
			queue2.dequeue();
			// check if head is null at queue 1, that is empty
			Assert.assertTrue(queue2.head() == null); 
		} catch (Exception e) {
			Assert.fail("Teste failed.");
		}
		
	}

	@Test
	public void testIsEmpty() {
		try {
			Assert.assertTrue(queue3.isEmpty());

			queue3.enqueue(1); 
			Assert.assertFalse(queue3.isEmpty()); 

			Assert.assertFalse(queue1.isEmpty());
			Assert.assertFalse(queue2.isEmpty()); 

			queue2.dequeue(); 
			queue2.dequeue();
			Assert.assertTrue(queue2.isEmpty()); 
		} catch (Exception e) {
			Assert.fail("Test has failed.");
		}			
	}

	@Test
	public void testIsFull() {
		try {
			Assert.assertFalse(queue3.isFull());
			Assert.assertFalse(queue1.isFull());
			
			queue1.enqueue(4);
			queue1.enqueue(5);
			queue1.enqueue(6);
			Assert.assertTrue(queue1.isFull());
			
			Assert.assertTrue(queue2.isFull());
			
			queue2.dequeue();
			Assert.assertFalse(queue2.isFull());
		} catch (Exception e) {
			Assert.fail("Test has failed.");
		}
		
	}

	@Test
	public void testEnqueue() {
		try {
			queue3.enqueue(1);
			queue3.enqueue(2);
			queue3.enqueue(3);
			Assert.assertTrue(queue3.isFull());
		} catch (Exception e) {
			Assert.fail("Test has failed.");
		}
	}
	
	@Test(expected=QueueOverflowException.class)
	public void testEnqueueComErro() throws QueueOverflowException {
		queue2.enqueue(3);
	}

	@Test
	public void testDequeue() {
		try {
			Assert.assertTrue(queue2.head().equals(1));
			
			queue2.dequeue();
			Assert.assertTrue(queue2.head().equals(2));
			
			queue2.dequeue();
			Assert.assertTrue(queue2.isEmpty());
		} catch (Exception e) {
			Assert.fail("Test has failed.");
		}
	}
	
	@Test(expected=QueueUnderflowException.class)
	public void testDequeueComErro() throws QueueUnderflowException {
		queue3.dequeue();
	}
	
}