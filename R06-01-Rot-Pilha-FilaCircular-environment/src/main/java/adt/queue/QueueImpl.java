package adt.queue;

public class QueueImpl<T> implements Queue<T> {

	private T[] array;
	private int tail;
		
	
	@SuppressWarnings("unchecked")
	public QueueImpl(int size) {
		if (size < 0) {
			size = 0;
		}
		array = (T[])new Object[size];
		tail = -1;
		
	}

	@Override
	public T head() {
		if (isEmpty()) {
			return null;
		} else {
			return array[0];
		}
	}

	@Override
	public boolean isEmpty() {
		return tail == -1;
	}

	@Override
	public boolean isFull() {
		return tail == array.length -1;
	}
	
	private void shiftLeft(){
		for (int i = 0; i < tail; i++) {
			this.array[i] = array[i+1];
		}
		this.tail--;
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if (isFull()) {
			throw new QueueOverflowException();
		} else if (isEmpty() && element != null) {
			this.tail = 0;
			this.array[tail] = element;
		} else if (element != null) {
			this.array[++tail] = element;
		}
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		T element;
		if (isEmpty()) {
			throw new QueueUnderflowException();
		} else {
			element = this.array[0];
		}
		shiftLeft();
		return element;
	}
	


}
