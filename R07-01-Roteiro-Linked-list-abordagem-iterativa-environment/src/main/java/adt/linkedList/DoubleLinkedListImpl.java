package adt.linkedList;

public class DoubleLinkedListImpl<T> extends SingleLinkedListImpl<T> implements DoubleLinkedList<T> {

	protected DoubleLinkedListNode<T> last;

	@Override
	public void insertFirst(T element) {
		if (element != null) {
			DoubleLinkedListNode<T> nil = new DoubleLinkedListNode<>();
			DoubleLinkedListNode<T> newNode = new DoubleLinkedListNode<>(element, nil, nil);

			if (isEmpty()) {
				this.head = newNode;
				this.last = (DoubleLinkedListNode<T>) head;
			} else {
				DoubleLinkedListNode<T> newHead = (DoubleLinkedListNode<T>) this.head;
				newHead.setPrevious(newNode);
				newNode.setNext(getHead());
				setHead(newNode);
			}
		}
	}

	@Override
	public void insert(T element) {
		if (element != null) {
			DoubleLinkedListNode<T> nil = new DoubleLinkedListNode<>();
			DoubleLinkedListNode<T> newNode = new DoubleLinkedListNode<>(element, nil, nil);

			if (isEmpty()) {
				this.head = newNode;
			} else {
				newNode.previous = last;
				last.setNext(newNode);
			}
			
			this.last = newNode;
		}
	}

	@Override
	public void removeFirst() {
		if (!isEmpty()) {
			if (this.head.next.isNIL()) {
				this.head = new DoubleLinkedListNode<>();
				this.last = new DoubleLinkedListNode<>();
			} else {
				DoubleLinkedListNode<T> newHead = (DoubleLinkedListNode<T>) this.head;
				((DoubleLinkedListNode<T>) this.head.next).setPrevious(newHead.previous);
				newHead.previous = (DoubleLinkedListNode<T>) head.next;
				this.head = this.head.next;
			}
		}

	}

	@Override
	public void removeLast() {
		if (!isEmpty()) {
			if (this.head.next.isNIL()) {
				this.head = new DoubleLinkedListNode<>();
				this.last = new DoubleLinkedListNode<>();
			} else {
				this.last = this.last.previous;
				this.last.next = new DoubleLinkedListNode<>();
			}
		}
	}

	@Override
	public void remove(T element) {
		if (!isEmpty() && element != null) {
			if (head.getData().equals(element)) {
				removeFirst();
			} else {
				DoubleLinkedListNode<T> aux = (DoubleLinkedListNode<T>) this.head.getNext();

				while (!aux.isNIL() && !aux.getData().equals(element)) {
					aux = (DoubleLinkedListNode<T>) aux.getNext();
				}

				if (!aux.isNIL()) {
					aux.previous.next = (DoubleLinkedListNode<T>) aux.next;
					((DoubleLinkedListNode<T>) aux.next).previous = aux.previous;
					if (aux.getNext().isNIL()) {
						this.last = this.last.previous;
					}
				}
			}

		}
	}

	public DoubleLinkedListNode<T> getLast() {
		return last;
	}

	public void setLast(DoubleLinkedListNode<T> last) {
		this.last = last;
	}

}
