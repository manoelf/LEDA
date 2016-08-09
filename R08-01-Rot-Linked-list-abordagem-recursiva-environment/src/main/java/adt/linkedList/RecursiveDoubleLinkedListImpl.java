package adt.linkedList;

public class RecursiveDoubleLinkedListImpl<T> extends RecursiveSingleLinkedListImpl<T> implements DoubleLinkedList<T> {

	protected RecursiveDoubleLinkedListImpl<T> previous;

	public RecursiveDoubleLinkedListImpl() {
	}

	public RecursiveDoubleLinkedListImpl(T data, RecursiveSingleLinkedListImpl<T> next,
			RecursiveDoubleLinkedListImpl<T> previous) {
		super(data, next);
		this.previous = previous;
	}

	@Override
	public void insertFirst(T element) {
		if (element != null) {
			if (isEmpty()) {
				this.data = element;
				this.previous = makeNil();
				this.next = makeNil();
			} else {
				RecursiveDoubleLinkedListImpl<T> newNode = new RecursiveDoubleLinkedListImpl<>(this.data, this.next,
						this);
				this.data = element;
				((RecursiveDoubleLinkedListImpl<T>) this.next).previous = newNode;
				this.next = newNode;
			}
		}
	}
	
	@Override
	public void insert(T element) {
		if (element != null) {
			if (isEmpty()) {
				this.data = element;
				this.previous = makeNil();
				this.next = makeNil();
			} else if (this.next.isEmpty()) {
				this.next = new RecursiveDoubleLinkedListImpl<>(element, makeNil(), this);
			} else {
				this.next.insert(element);
			}
		}
	}
	
	@Override
	public void removeFirst() {
		if (!isEmpty()) {
			if (this.next.isEmpty()) {
				this.data = null;
				this.next = null;
			} else {
				this.data = this.next.getData();
				this.next = this.next.getNext();
			}
		}
	}

	// nil <=> [0] <=> [1] <=> [2] <=> nil
	@Override
	public void removeLast() {
		if (!isEmpty()) {
			if (this.next.isEmpty()) {
				this.data = null;
				this.next = null;
			} else {
				((RecursiveDoubleLinkedListImpl<T>) this.next).removeLast();
			}
		}
	}

	@Override
	public void remove(T element) {
		if (!isEmpty() && element != null) {
			if (this.data.equals(element)) {
				if (this.next.isEmpty()) {
					this.data = null;
					this.next = null;
				} else {
					this.data = this.next.getData();
					this.next = this.next.getNext();
				}
			} else if (!this.next.isEmpty()) {
				this.next.remove(element);
			}
		}
	}

	public RecursiveDoubleLinkedListImpl<T> getPrevious() {
		return previous;
	}

	public void setPrevious(RecursiveDoubleLinkedListImpl<T> previous) {
		this.previous = previous;
	}

	private RecursiveDoubleLinkedListImpl<T> makeNil() {
		return new RecursiveDoubleLinkedListImpl<>();
	}
}
