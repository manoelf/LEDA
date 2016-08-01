package adt.stack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StudentStackTest {

	public Stack<Integer> stack1;
	public Stack<Integer> stack2;
	public Stack<Integer> stack3;

	@Before
	public void setUp() throws StackOverflowException{

		getImplementations();
		
		// Pilha com 3 elementos não cheia.
		stack1.push(1);
		stack1.push(2);
		stack1.push(3);
		
	
		// Pilha com 2 elementos de tamanho 2, pilha cheia.
		stack2.push(1);
		stack2.push(2);
		
	}
	
	private void getImplementations(){
		//TODO O aluno deve ajustar aqui para instanciar sua implementação
		stack1 = new StackImpl<>(3);
		stack2 = new StackImpl<>(2);
		stack3 = new StackImpl<>(3);
	}
	
	//MÉTODOS DE TESTE
	@Test
	public void testTop() {
		assertEquals(new Integer(3), stack1.top());
	}

	@Test
	public void testIsEmpty() {
		assertFalse(stack1.isEmpty());
	}

	@Test
	public void testIsFull() {
		assertTrue(stack1.isFull()); //vai depender do tamanho que a pilha foi iniciada!!!!
	}

	@Test
	public void testPush() {
		try {
			stack1.push(new Integer(5));
		} catch (StackOverflowException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	}
	
	@Test(expected=StackOverflowException.class)
	public void testPushComErro() throws StackOverflowException {
		stack1.push(new Integer(5)); //levanta excecao apenas se o tamanhonao permitir outra insercao
	}

	@Test
	public void testPop() {
		try {
			assertEquals(new Integer(3), stack1.pop());
		} catch (StackUnderflowException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Evaziar para poder testar
	
	@Test(expected=StackUnderflowException.class)
	public void testPopComErro() throws StackUnderflowException {
		assertEquals(new Integer(3), stack3.pop()); //levanta excecao apenas se stack1 for vazia
	}
	
	//=======================================================================================================


	@Test
	public void meuTestPop() {
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
	public void meuTestPopComErro() throws StackUnderflowException {
		stack3.pop();
	}
	
	public void testIllegalSizes() {
		try{
			new StackImpl<Integer>(0);
			Assert.fail();
		}catch(Exception e){
			Assert.assertEquals("Size must be bigger than 0.", e.getMessage());
		}
		
		try{
			new StackImpl<Integer>(-1);
			Assert.fail();
		}catch(Exception e){
			Assert.assertEquals("Size must be bigger than 0.", e.getMessage());
		}
	}




}