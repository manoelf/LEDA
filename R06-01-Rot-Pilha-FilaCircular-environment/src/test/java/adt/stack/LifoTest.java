package adt.stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Random;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LifoTest {

	private Random randomer;

	private final int RANGE = 10;
	private final int SIZE = 10;

	private Deque<Integer> stack;
	private Stack<Integer> myStack;

	@Before
	public void setUp() {
		this.randomer = new Random();
		this.stack = new ArrayDeque<>();
		this.myStack = new StackImpl<>(SIZE);
	}

	@Test
	public void test() {

		for (int i = 0; i <= 10000000; i++) {

			switch (randomer.nextInt(5)) {
			case 1:
				isEmptyTest();
				break;
			case 2:
				isFullTest();
				break;
			case 3:
				pushTest();
				break;
			case 4:
				popTest();
				break;
			case 5:
				topTest();
				break;
			default:
				break;

			}
		}

	}

	private void topTest() {
		Integer element = stack.peek();
		Assert.assertEquals(element, myStack.top());
	}

	private void isEmptyTest() {
		if (stack.isEmpty()) {
			if (!myStack.isEmpty()) {
				Assert.fail("Deveria estar vazia.");
			}
		} else {
			if (myStack.isEmpty()) {
				Assert.fail("Não Deveria estar vazia.");
			}
		}
	}

	private void isFullTest() {
		if (stack.size() == SIZE) {
			if (!myStack.isFull()) {
				Assert.fail("Deveria estar cheia.");
			}
		} else {
			if (myStack.isFull()) {
				Assert.fail("Não Deveria estar cheia.");
			}
		}

	}

	private void popTest() {
		if (stack.size() > 0) {
			stack.removeFirst();
			try {
				myStack.pop();
			} catch (StackUnderflowException e) {
				Assert.fail("Nao deveria estar vazia.");
			}
		} else {
			try {
				myStack.pop();
				Assert.fail("Deveria estar vazia");
			} catch (StackUnderflowException e) {
				Assert.assertEquals("Stack is empty", e.getMessage());

			}

		}
	}

	private void pushTest() {
		int element = randomer.nextInt(RANGE);
		if (stack.size() < SIZE) {
			stack.addFirst(element);

			try {
				myStack.push(element);
			} catch (StackOverflowException e) {
				Assert.fail("Nao deveria estar cheia.");
			}
		} else {

			try {
				myStack.push(element);
				Assert.fail("Deveria estar cheia");
			} catch (StackOverflowException e) {
				Assert.assertEquals("Stack is full", e.getMessage());
			}

		}
	}

}
