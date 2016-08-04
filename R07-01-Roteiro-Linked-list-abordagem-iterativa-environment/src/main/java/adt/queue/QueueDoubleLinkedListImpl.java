package adt.queue;

import adt.linkedList.DoubleLinkedList;
import adt.linkedList.DoubleLinkedListImpl;

public class QueueDoubleLinkedListImpl<T> implements Queue<T> {

	protected DoubleLinkedList<T> list;
	protected int size;

	public QueueDoubleLinkedListImpl(int size) {
		this.size = size;
		this.list = new DoubleLinkedListImpl<T>();
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if (isFull()) {
			throw new QueueOverflowException();
		}
		if (element != null) {
			this.list.insert(element);
		}

	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		if (isEmpty()) {
			throw new QueueUnderflowException();
		} else {
			T element = list.toArray()[0];
			list.removeFirst();
			return element;
		}
	}

	@Override
	public T head() {
		if (isEmpty()) {
			return null;
		} else {
			return list.toArray()[0];
		}
	}

	@Override
	public boolean isEmpty() {
		return (list.isEmpty());
	}

	@Override
	public boolean isFull() {
		return (list.size() == size);
	}

}
