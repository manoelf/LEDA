package adt.queue;

public class CircularQueue<T> implements Queue<T> {

	private T[] array;
	private int tail;
	private int head;
	private int elements;

	public CircularQueue(int size) {
		if (size < 0) {
			size = 0;
		}
		
		array = (T[]) new Object[size];
		head = -1;
		tail = -1;
		elements = 0;
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if (isFull()) {
			throw new QueueOverflowException();
		} else if (isEmpty() && element != null) {
			this.head = 0;
			this.tail = 0;
			this.array[tail] = element;
		} else if (element != null) {
			tail = (tail + 1) % this.array.length;
			this.array[tail] = element;
		}
		elements++;
	}
	
	@Override
	public T dequeue() throws QueueUnderflowException {
		T element;
		if (isEmpty()) {
			throw new QueueUnderflowException();
		} else if (elements == 1) { //Verificao se eh igual a '1' para reinicializar o array
			element = this.array[head];
			this.head = -1;
			this.tail = -1;
		} else {
			element = this.array[head];
			this.head = (head + 1) % this.array.length;
		}
		elements--;
		return element;
	}

	@Override
	public T head() {
		if (isEmpty()) {
			return null;
		} else {
			return this.array[head];
		}
	}

	@Override
	public boolean isEmpty() {
		return (elements == 0);
	}


	@Override
	public boolean isFull() {
		if (elements == array.length) {
			return true;
		} else {
			return false;
		}
	}
	
	
}
