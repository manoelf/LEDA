package adt.linkedList;

public class RecursiveSingleLinkedListImpl<T> implements LinkedList<T> {

	protected T data;
	protected RecursiveSingleLinkedListImpl<T> next;
	
		
	public RecursiveSingleLinkedListImpl() {
		
	}

	public RecursiveSingleLinkedListImpl(T data, RecursiveSingleLinkedListImpl<T> next) {
		this.data = data;
		this.next = next;
	}

	@Override
	public boolean isEmpty() {
		return (this.data == null);
	}

	@Override
	public int size() {
		if (isEmpty()) {
			return 0;
		} else if (this.next == null) {
			return 1;
		} else {
			return 1 + next.size();
		}
	}

	@Override
	public T search(T element) {
		if (isEmpty()) {
			return null;
		} else if (this.data.equals(element)) {
			return this.data;
		} else if (this.next == null){
			return null;
		} else {
			return next.search(element);
		}
	}

	@Override
	public void insert(T element) {
		if (isEmpty()) {
			this.data =  element;
		} else if (this.next == null) {
			RecursiveSingleLinkedListImpl<T> newNode = new RecursiveSingleLinkedListImpl<>(element, null);
			this.next = newNode;
		} else {
			next.insert(element);
		}
	}

	@Override
	public void remove(T element) {
		if (!isEmpty() && element != null) {
			if (this.data.equals(element)) {
				if (this.next != null) {
					RecursiveSingleLinkedListImpl<T> aux = next;
					while (aux.getNext() != null) {
						aux.setData(aux.getNext().getData());
						aux.setNext(aux.getNext().next);
						aux = aux.getNext();
					}
				}
			} else if (this.next != null){
				if (next.getData().equals(element)) {
					this.next = this.next.next;
				} else {
					next.remove(element);
				}
			}
		}
	}
	
	
	@Override
	public T[] toArray(){
		
		// TODO Auto-generated method stub
		throw new RuntimeException("Not implemented yet!");
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public RecursiveSingleLinkedListImpl<T> getNext() {
		return next;
	}

	public void setNext(RecursiveSingleLinkedListImpl<T> next) {
		this.next = next;
	}

}
