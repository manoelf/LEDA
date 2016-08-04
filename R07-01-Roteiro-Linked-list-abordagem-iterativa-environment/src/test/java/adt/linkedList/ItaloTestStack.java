package adt.linkedList;


import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import adt.stack.StackDoubleLinkedListImpl;
import adt.stack.StackOverflowException;
import adt.stack.StackUnderflowException;

public class ItaloTestStack {

	public StackDoubleLinkedListImpl<Integer> stack1;
	public StackDoubleLinkedListImpl<Integer> stack2;
	public StackDoubleLinkedListImpl<Integer> stack3;

	@Before
	public void setUp() throws StackOverflowException{
		stack1 = new StackDoubleLinkedListImpl<Integer>(6);
		stack1.push(1);
		stack1.push(2);
		stack1.push(3);
		
		stack2 = new StackDoubleLinkedListImpl<Integer>(2);
		stack2.push(1);
		stack2.push(2);
		
		stack3 = new StackDoubleLinkedListImpl<Integer>(6);
	}
	
	//MÉTODOS DE TESTE
	@Test
	public void testTop() {
		Assert.assertTrue(stack1.top().equals(3));
		Assert.assertTrue(stack2.top().equals(2));
		Assert.assertTrue(stack3.top() == null);
	}

	@Test
	public void testIsEmpty() {
		Assert.assertEquals(false, stack1.isEmpty());
		Assert.assertEquals(false, stack2.isEmpty());
		Assert.assertEquals(true,  stack3.isEmpty());
	}

	@Test
	public void testIsFull() {
		Assert.assertEquals(false, stack1.isFull());
		Assert.assertEquals(true,  stack2.isFull());
		Assert.assertEquals(false, stack3.isFull());
	}

	@Test
	public void testPush() {
		
		// test if some null element is added
		try{
			stack3.push(null);
			Assert.assertTrue(stack3.isEmpty());
			Assert.assertFalse(stack3.isFull());		
		} catch(StackOverflowException e){
			Assert.fail();
		}
		
		// normal push test
		try {
			stack1.push(4);
			stack1.push(5);
			stack1.push(6);
		} catch (Exception e) {
			Assert.fail();
		}
	}
	
	@Test(expected=StackOverflowException.class)
	public void testPushComErro() throws StackOverflowException {
		stack2.push(3);
	}

	@Test
	public void testPop() {
		try {
			
			stack1.push(4);
			stack1.push(5);
			stack1.push(6);
			
			Assert.assertEquals(true, stack1.isFull());
			
			Assert.assertTrue(stack1.pop().equals(6));
			Assert.assertTrue(stack1.pop().equals(5));
			Assert.assertTrue(stack1.pop().equals(4));
			Assert.assertTrue(stack1.pop().equals(3));
			Assert.assertTrue(stack1.pop().equals(2));
			Assert.assertTrue(stack1.pop().equals(1));
			
			stack1.pop();
			
		} catch (Exception e) {
			Assert.assertEquals("Stack is empty", e.getMessage());
		}
	}
	
	@Test(expected=StackUnderflowException.class)
	public void testPopComErro() throws StackUnderflowException {
		stack3.pop();
	}
	
}