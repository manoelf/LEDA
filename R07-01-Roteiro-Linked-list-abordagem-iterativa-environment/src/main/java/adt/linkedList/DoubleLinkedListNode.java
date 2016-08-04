package adt.linkedList;

public class DoubleLinkedListNode<T> extends SingleLinkedListNode<T> {
	protected DoubleLinkedListNode<T> previous;

	public DoubleLinkedListNode() {
	}

	public DoubleLinkedListNode(T data, DoubleLinkedListNode<T> next, DoubleLinkedListNode<T> previous) {
		super(data, next);
		this.previous = previous;
		DoubleLinkedListNode<T> newElement = new DoubleLinkedListNode<>(data, next, previous);
	}

	public DoubleLinkedListNode<T> getPrevious() {
		return previous;
	}

	public void setPrevious(DoubleLinkedListNode<T> previous) {
		this.previous = previous;
	}
	
	
}
