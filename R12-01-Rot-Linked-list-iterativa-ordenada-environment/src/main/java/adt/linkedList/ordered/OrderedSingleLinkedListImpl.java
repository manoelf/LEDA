package adt.linkedList.ordered;

import java.security.GeneralSecurityException;
import java.util.Comparator;

import adt.linkedList.SingleLinkedListImpl;
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

class ComparatorDefault<T extends Comparable<T>> implements Comparator<T> {

	@Override
	public int compare(T o1, T o2) {
		return o1.compareTo(o2);
	}

}

public class OrderedSingleLinkedListImpl<T> extends SingleLinkedListImpl<T> implements OrderedLinkedList<T> {

	private Comparator<T> comparator;

	public OrderedSingleLinkedListImpl() {
		this.comparator = new ComparatorDefault();
	}

	public OrderedSingleLinkedListImpl(Comparator<T> comparator) {
		this.comparator = comparator;
	}

	@Override
	public T minimum() {
		if (!isEmpty()) {
			T head = this.head.getData();
			T last = toArray()[this.size() - 1];
			if (((Comparable<T>) head).compareTo(last) > 0) {
				return last;
			} else {
				return head;
			}
		}
		return null;
	}

	@Override
	public T maximum() {
		if (!isEmpty()) {
			T head = this.head.getData();
			T last = toArray()[this.size() - 1];
			if (((Comparable<T>) head).compareTo(last) > 0) {
				return head;
			} else {
				return last;
			}
		}
		return null;
	}

	@Override
	public void insert(T element) {
		if (element != null) {
			SingleLinkedListNode<T> nil = new SingleLinkedListNode<>();
			SingleLinkedListNode<T> newNode = new SingleLinkedListNode<T>(element, nil);
			if (isEmpty()) {
				this.head = newNode;
			} else if (((Comparable<T>) this.head.getData()).compareTo(element) > 0) {
				newNode.setNext(this.head);
				this.head = newNode;
			} else {
				SingleLinkedListNode<T> aux = this.head;

				while (!aux.getNext().isNIL() && ((Comparable<T>) aux.getNext().getData()).compareTo(element) < 0) {
					aux = aux.getNext();
				}

				if (aux.getNext().isNIL()) {
					aux.setNext(newNode);
				} else {
					newNode.setNext(aux.getNext());
					aux.setNext(newNode);
				}
			}
		}
	}

	public Comparator<T> getComparator() {
		return comparator;
	}

	public void setComparator(Comparator<T> comparator) {
		this.comparator = comparator;
	}

}
