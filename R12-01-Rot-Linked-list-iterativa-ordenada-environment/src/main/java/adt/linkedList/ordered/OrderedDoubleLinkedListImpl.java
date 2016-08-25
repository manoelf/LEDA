package adt.linkedList.ordered;

import java.util.Comparator;

import adt.linkedList.DoubleLinkedList;
import adt.linkedList.DoubleLinkedListNode;

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
public class OrderedDoubleLinkedListImpl<T> extends OrderedSingleLinkedListImpl<T> implements OrderedLinkedList<T>,
      DoubleLinkedList<T> {

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
         DoubleLinkedListNode<T> newNode = new DoubleLinkedListNode<T>(element, nil, null);
         newNode.setNext(this.head);
         this.head = newNode;
         newNode.setPrevious((DoubleLinkedListNode<T>) this.head);
         if (!this.head.getNext().isNIL()) {

            DoubleLinkedListNode<T> aux = (DoubleLinkedListNode<T>) this.head;
            while (!aux.getNext().isNIL() && getComparator().compare(aux.getData(), aux.getNext().getData()) > 0) {
               T temp = aux.getData();
               aux.setData(aux.getNext().getData());
               aux.getNext().setData(temp);
               aux = (DoubleLinkedListNode<T>) aux.getNext();
            }
            if (aux.getNext().isNIL()) {
               this.last = aux;
            }
         } else {
            this.last = (DoubleLinkedListNode<T>) head;
         }
      }
   }

   /**
    * Este método faz sentido apenas se o elemento a ser inserido pode
    * realmente ficar na primeira posição (devido a ordem)
    */
   @Override
   public void insertFirst(T element) {
      if (!isEmpty()) {
         if (this.head.getNext().isNIL()) {
            this.head = new DoubleLinkedListNode<>();
            this.last = new DoubleLinkedListNode<>();
         } else if (((Comparable<T>) this.head).compareTo(element) > 0) {
            DoubleLinkedListNode<T> newHead = (DoubleLinkedListNode<T>) this.head;
            ((DoubleLinkedListNode<T>) this.head.getNext()).setPrevious(newHead.getPrevious());
            newHead.setPrevious((DoubleLinkedListNode<T>) head.getNext());
            this.head = this.head.getNext();
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

   @Override
   public void remove(T element) {
      if (!isEmpty() && element != null) {
         if (head.getData().equals(element)) {
            removeFirst();
         } else {
            DoubleLinkedListNode<T> aux = (DoubleLinkedListNode<T>) this.head;

            while (!aux.getNext().isNIL() && !aux.getNext().getData().equals(element)) {
               aux = (DoubleLinkedListNode<T>) aux.getNext();
            }

            if (!aux.getNext().isNIL()) {
               if (aux.getNext().equals(last)) {
                  this.last = aux;
               }
               aux.setNext(aux.getNext().getNext());
               ((DoubleLinkedListNode<T>) aux.getNext()).setPrevious(aux.getPrevious());

               if (aux.getNext().isNIL()) {
                  this.last = this.last.getPrevious();
               }
            }
         }

      }

   }

}
