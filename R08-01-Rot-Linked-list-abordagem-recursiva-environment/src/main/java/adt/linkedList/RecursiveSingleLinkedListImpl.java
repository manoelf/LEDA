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
		} else {
			return 1 + next.size();
		}
	}

	@Override
	public T search(T element) {
		if (isEmpty() || element == null) {
			return null;
		} else if (this.data.equals(element)) {
			return this.data;
		} else {
			return next.search(element);
		}
	}

	@Override
	public void insert(T element) {
		if (element != null) {
			if (isEmpty()) {
				this.data = element;
				this.next = makeNil();
			} else {
				next.insert(element);
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
					this.data = next.getData();
					this.next = next.next;
				}
			} else {
				next.remove(element);
			}
		}
	}

	@Override
	public T[] toArray() {
		@SuppressWarnings("unchecked")
		T[] array = (T[]) new Object[size()];
		helpToArray(this, array, 0);
		return array;
	}

	private void helpToArray(RecursiveSingleLinkedListImpl<T> node, T[] array, int index) {
		if (!node.isEmpty()) {	
			array[index] = node.getData();
			helpToArray(node.getNext(), array, index + 1);
		}
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

	private RecursiveSingleLinkedListImpl<T> makeNil() {
		return new RecursiveSingleLinkedListImpl<>();
	}

}
