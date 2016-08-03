package adt.linkedList;

public class SingleLinkedListImpl<T> implements LinkedList<T> {

	protected SingleLinkedListNode<T> head;
	private int size;

	public SingleLinkedListImpl() {
		this.head = new SingleLinkedListNode<T>();
		this.size = 0;
	}

	@Override
	public boolean isEmpty() {
		return this.head.isNIL();
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public T search(T element) {
		// TODO Auto-generated method stub
		throw new RuntimeException("Not implemented!");
	}

	@Override
	public void insert(T element) {
		SingleLinkedListNode newNode = new SingleLinkedListNode<>(element, null);
		if (isEmpty()) {
			this.head = newNode;
			this.size++;
		} else {

			SingleLinkedListNode aux = this.head;

			while (aux.next != null) {
				aux = aux.getNext();
			}
			aux.setNext(newNode);
			size++;
		}

	}

	@Override
	public void remove(T element) {
		if (!isEmpty()) {

			if (this.head.data.equals(element)) {
				setHead(new SingleLinkedListNode());
				this.size--;
			} else {
				SingleLinkedListNode aux = this.head;

				while (aux.next != null && !aux.next.getData().equals(element)) {
					aux = aux.next;
				}

				if (aux.next != null) {
					SingleLinkedListNode next = aux.next.getNext();
					aux.setNext(next);
					this.size--;
				}
			}

		}
	}

	@Override
	public T[] toArray() {
		T[] array = (T[]) new Comparable[this.size];

		int cont = 0;
		SingleLinkedListNode aux = this.head;

		while (aux != null) {
			array[cont] = (T) aux.getData();
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
