package adt.stack;

import adt.linkedList.DoubleLinkedList;
import adt.linkedList.RecursiveDoubleLinkedListImpl;

public class StackRecursiveDoubleLinkedListImpl<T> implements Stack<T> {

	protected DoubleLinkedList<T> list;
	protected int size;

	public StackRecursiveDoubleLinkedListImpl(int size) {
		this.size = size;
		this.list = new RecursiveDoubleLinkedListImpl<>();
	}

	@Override
	public void push(T element) throws StackOverflowException {
		if (element != null) {
			if (isFull()) {
				throw new StackOverflowException();
			} else {
				this.list.insert(element);
			}
		}
	}

	@Override
	public T pop() throws StackUnderflowException {
		if (isEmpty()) {
			throw new StackUnderflowException();
		} else {
			T element =  top();
			this.list.removeLast();
			return element;
		}
	}

	@Override
	public T top() {
		if (isEmpty()) {
			return null;
		} else {
			return this.list.toArray()[list.size()-1];
		}
	}

	@Override
	public boolean isEmpty() {
		return this.list.isEmpty();
	}

	@Override
	public boolean isFull() {
		return (this.list.size() == this.size);
	}

}
