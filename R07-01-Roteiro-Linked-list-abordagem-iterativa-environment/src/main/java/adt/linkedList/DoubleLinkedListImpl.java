package adt.linkedList;

public class DoubleLinkedListImpl<T> extends SingleLinkedListImpl<T> implements
		DoubleLinkedList<T> {

	protected DoubleLinkedListNode<T> last;
	
	@Override
	public void insertFirst(T element) {//element, next, previous
		
		DoubleLinkedListNode<T> newHead = new DoubleLinkedListNode<>(element, (DoubleLinkedListNode<T>)getHead(), (DoubleLinkedListNode<T>)nil);
		SingleLinkedListNode<T> nil = new SingleLinkedListNode<>(null, newHead);
		
		if (isEmpty()) {
			this.last = (DoubleLinkedListNode<T>) nil;
			
		}
		
		
	}

	@Override
	public void removeFirst() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	@Override
	public void removeLast() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	public DoubleLinkedListNode<T> getLast() {
		return last;
	}

	public void setLast(DoubleLinkedListNode<T> last) {
		this.last = last;
	}

	
	
	
}
