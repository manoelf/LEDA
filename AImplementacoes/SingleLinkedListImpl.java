package adt.linkedList;

public class SingleLinkedListImpl<T> implements LinkedList<T> {

	protected SingleLinkedListNode<T> head;

	public SingleLinkedListImpl() {
		this.head = new SingleLinkedListNode<T>();
	}

	@Override
	public boolean isEmpty() {
		return this.head.isNIL();
	}

	@Override
	public int size() {
		int size = 0;
		SingleLinkedListNode<T> aux = this.head;
		
		while (!aux.isNIL()) {
			size++;
			aux = aux.getNext();
		}
		return size;
	}

	@Override
	public T search(T element) {
		if (isEmpty()) {
			return null;
		} else {
			SingleLinkedListNode<T> aux = this.head;

			while (!aux.isNIL() && !aux.getData().equals(element)) {
				aux = aux.getNext();
			}

			if (aux.isNIL()) {
				return null;
			} else if (aux.getData().equals(element)) {
				return aux.getData();
			} else {
				return null;
			}
		}
	}

	@Override
	public void insert(T element) {
		if (element != null) {
			SingleLinkedListNode<T> nodeElement = new SingleLinkedListNode<>();
			SingleLinkedListNode<T> newNode = new SingleLinkedListNode<>(element, nodeElement);
			if (isEmpty()) {
				this.head = newNode;
			} else {

				SingleLinkedListNode<T> aux = this.head;

				while (!aux.next.isNIL()) {
					aux = aux.getNext();
				}
				aux.setNext(newNode);
			}
		}
	}

	@Override
	public void remove(T element) {
		if (!isEmpty() && element != null) {
			if (this.head.getData().equals(element)) {
				SingleLinkedListNode<T> newHead = head.next;
				setHead(newHead);
			} else {
				SingleLinkedListNode<T> aux = this.head;

				while (!aux.next.isNIL() && !aux.next.getData().equals(element)) {
					aux = aux.next;
				}
				
				if (!aux.next.isNIL()) {
					SingleLinkedListNode<T> newNext = aux.next.getNext();
					aux.setNext(newNext);
				}
			}
		}
	}

	@Override
	public T[] toArray() {
		T[] array = (T[]) new Object[size()];

		int cont = 0;
		SingleLinkedListNode<T> aux = this.head;

		while (!aux.isNIL()) {
			array[cont] = aux.getData();
			aux = aux.getNext();
			cont++;
		}
		return array;
	}

	public SingleLinkedListNode<T> getHead() {
		return head;
	}

	public void setHead(SingleLinkedListNode<T> head) {
		this.head = head;
	}

}
