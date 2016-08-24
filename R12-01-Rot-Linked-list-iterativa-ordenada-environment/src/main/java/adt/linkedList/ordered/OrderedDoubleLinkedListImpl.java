package adt.linkedList.ordered;

import java.util.Comparator;

import adt.linkedList.DoubleLinkedList;
import adt.linkedList.DoubleLinkedListNode;
import adt.linkedList.SingleLinkedListNode;

/**
 * Para testar essa classe voce deve implementar seu comparador. Primeiro
 * implemente todos os métodos requeridos. Depois implemente dois comparadores
 * (com idéias opostas) e teste sua classe com eles. Dependendo do comparador
 * que você utilizar a lista funcionar como ascendente ou descendente, mas a
 * implemntação dos métodos é a mesma.
 * 
 * @author Adalberto
 *
 * @param <T>
 */
public class OrderedDoubleLinkedListImpl<T> extends OrderedSingleLinkedListImpl<T>
		implements OrderedLinkedList<T>, DoubleLinkedList<T> {

	private DoubleLinkedListNode<T> last;

	public OrderedDoubleLinkedListImpl() {
	}

	public OrderedDoubleLinkedListImpl(Comparator<T> comparator) {
		super(comparator);
	}

	@Override
	public void insert(T element) {
		if (element != null) {
			DoubleLinkedListNode<T> nil = new DoubleLinkedListNode<>();
			DoubleLinkedListNode<T> newNode = new DoubleLinkedListNode<T>(element, nil, nil);

			if (isEmpty()) {
				newNode.setPrevious(null);
				this.head = newNode;
				this.last = newNode;

			} else {

				DoubleLinkedListNode<T> aux = (DoubleLinkedListNode<T>) this.head;

				while (!aux.getNext().isNIL() && getComparator().compare(aux.getNext().getData(), element) < 0) {
					aux = (DoubleLinkedListNode<T>) aux.getNext();
				}

				if (aux.getNext().isNIL()) {
					if (getComparator().compare(aux.getData(), element) > 0) {
						newNode.setNext(aux);
						newNode.setPrevious(null);
						aux.setPrevious(newNode);
						setHead(newNode);
					} else {
						newNode.setPrevious(aux);
						aux.setNext(newNode);
						this.last = newNode;
					}
				} else {
					newNode.setPrevious(aux);
					newNode.setNext(aux.getNext());
					((DoubleLinkedListNode<T>) aux.getNext()).setPrevious(newNode);
					aux.setNext(newNode);
					
				}

			}
		}
	}


	/**
	 * Este método faz sentido apenas se o elemento a ser inserido pode
	 * realmente ficar na primeira posição (devido a ordem)
	 */
	@Override
	public void insertFirst(T element) {
		if (element != null) {
			if (isEmpty()) {
				this.head = new DoubleLinkedListNode<T>(element, new DoubleLinkedListNode<>(), null);
				this.last = (DoubleLinkedListNode<T>) head;
			} else if (((Comparable<T>) this.head).compareTo(element) > 0) {
				DoubleLinkedListNode<T> newHead = new DoubleLinkedListNode<T>(element,
						(DoubleLinkedListNode<T>) this.head, null);
				((DoubleLinkedListNode<T>) this.head).setPrevious(newHead);
				this.head = newHead;
			}
		}
	}

	@Override
	public void removeFirst() {
		if (!isEmpty()) {

			if (this.head.getData().equals(last.getData())) {
				this.head = new DoubleLinkedListNode<>();
				this.last = (DoubleLinkedListNode<T>) head;
			} else {
				this.head = head.getNext();
				((DoubleLinkedListNode<T>) this.head).setPrevious(null);
			}
		}
	}

	@Override
	public void removeLast() {
		if (!isEmpty()) {
			if (this.head.getData().equals(last.getData())) {
				this.head = new DoubleLinkedListNode<>();
				this.last = (DoubleLinkedListNode<T>) head;
			} else {
				this.last.getPrevious().setNext(new DoubleLinkedListNode<>());
				this.last = last.getPrevious();
			}
		}
	}

}
