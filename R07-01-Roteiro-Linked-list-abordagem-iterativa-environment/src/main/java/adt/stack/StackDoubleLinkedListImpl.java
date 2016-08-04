package adt.stack;

import adt.linkedList.DoubleLinkedList;
import adt.linkedList.DoubleLinkedListImpl;

public class StackDoubleLinkedListImpl<T> implements Stack<T> {

	protected DoubleLinkedList<T> list;
	protected int size;

	public StackDoubleLinkedListImpl(int size) {
		this.size = size;
		this.list = new DoubleLinkedListImpl<T>();
	}

	@Override
	public void push(T element) throws StackOverflowException {
		if (isFull()) {
			throw new StackOverflowException();
		} else if (element != null) {
			this.list.insert(element);
		}

	}

	@Override
	public T pop() throws StackUnderflowException {
		if (isEmpty()) {
			throw new StackUnderflowException();
		} else {
			T element = this.list.toArray()[list.size()-1];
			this.list.removeLast();
			return element;
		}
	}

	@Override
	public T top() {
		if (isEmpty()) {
			return null;
		}  else {
			T element = this.list.toArray()[list.size()-1];
			return element;
		}
		
	}

	@Override
	public boolean isEmpty() {
		return (list.size() == 0);
	}

	@Override
	public boolean isFull() {
		return (this.list.size() == this.size);
	}

}
