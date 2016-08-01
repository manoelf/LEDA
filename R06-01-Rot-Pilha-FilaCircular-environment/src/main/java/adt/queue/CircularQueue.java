package adt.queue;

public class CircularQueue<T> implements Queue<T> {

	private T[] array;
	private int tail;
	private int head;
	private int elements;

	public CircularQueue(int size) {
		array = (T[]) new Object[size];
		head = -1;
		tail = -1;
		elements = 0;
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if (isFull()) {
			throw new QueueOverflowException();
		} else if (isEmpty()) {
			this.head = 0;
			this.tail = 0;
			this.array[tail] = element;
		} else if (element != null) {
			tail = (tail + 1) % this.array.length;
			this.array[tail] = element;
		}
		elements++;
	}
	//[1, x2, 3]
	//h=2
	//t=0
	
	
	@Override
	public T dequeue() throws QueueUnderflowException {
		T element;
		if (isEmpty()) {
			throw new QueueUnderflowException();
		} else if (elements == array.length -1) {
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
		return (this.head == -1 && this.tail == -1);
	}


	@Override
	public boolean isFull() {
		if ((this.tail + 1) % array.length == this.head) {
			return true;
		} else {
			return false;
		}
	}
	
}
