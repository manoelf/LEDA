package adt.heap.ternary;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Classe que implementa a heap ternaria. Como cada no pode ter agora ate 3 filhos,
 * eles poderao ser capturados com left(i), middle(i), right(i). Por sua vez, dado um
 * indice i, o pai desse noh da heap serah calculado por parent(i). 
 *  
 * @author Adalberto
 *
 * @param <T>
 */
public class TernaryHeapImpl<T extends Comparable<T>> implements TernaryHeap<T> {

   protected T[] heap;
   protected int index = -1;
   protected Comparator comparator;
   private static final int INITIAL_SIZE = 20;
   private static final int INCREASING_FACTOR = 10;

   public TernaryHeapImpl() {
      this.heap = (T[]) new Comparable[INITIAL_SIZE];
   }

   public TernaryHeapImpl(Comparator<T> comparator) {
      this.heap = (T[]) new Comparable[INITIAL_SIZE];
      this.comparator = comparator;
   }

   private void reset() {
      this.heap = (T[]) new Comparable[INITIAL_SIZE];
      index = -1;
   }

   @Override
   public void insert(T element) {
      if (index == heap.length - 1) {
         heap = Arrays.copyOf(heap, heap.length + INCREASING_FACTOR);
      }

      this.heap[++index] = element;
      int i = index;

      int pai = parent(i);
      while (i >= 1 && compare(heap[i], heap[parent(i)]) > 0) {
         swap(i, parent(i));
         i = parent(i);
      }

   }

   @Override
   public T extractRootElement() {
      if (isEmpty()) {
         return null;
      } else {
         T element = heap[0];
         swap(0, index);
         index--;
         heapify(0);
         return element;
      }

   }

   @Override
   public T rootElement() {
      if (isEmpty()) {
         return null;
      } else {
         return heap[0];
      }
   }

   @Override
   public T[] toArray() {
      T[] resp = (T[]) new Comparable[index + 1];
      for (int i = 0; i <= index; i++) {
         resp[i] = this.heap[i];
      }
      return resp;
   }

   @Override
   public int size() {
      return index + 1;
   }

   @Override
   public T findKth(int k) {
      if (k - 1 < 0 || k - 1 > index) {
         return null;
      } else {
         return heapsort()[k - 1];
      }
   }

   private void buildHeap(T[] array) {
      if (array != null & array.length > 0) {
         this.heap = Arrays.copyOf(array, array.length);
         index = array.length - 1;
         for (int i = array.length / 2; i >= 0; i--) {
            heapify(i);
         }
      }
   }

   private T[] heapsort() {
      T[] array = Arrays.copyOf(this.heap, index);
      Comparator<T> comparatorTemp = this.comparator;

      this.comparator = new Comparator<T>() {

         @Override
         public int compare(T o1, T o2) {
            return o1.compareTo(o2);
         }
      };

      buildHeap(array);
      @SuppressWarnings("unchecked")
      T[] aux = (T[]) new Comparable[array.length];

      for (int i = index; i >= 0; i--) {
         aux[i] = extractRootElement();
      }
      this.heap = array;
      this.index = array.length - 1;
      this.comparator = comparatorTemp;
      return aux;
   }

   @Override
   public void removeKth(int k) {
      if (k - 1 >= 0 && k - 1 <= index) {
         if (isMinHeap()) {
            swap(k - 1, index);
            index--;
         } else {
            T element = findKth(k);
            for (int i = 0; i < index; i++) {
               if (heap[i].equals(element)) {
                  swap(i, index);
                  index--;
               }
            }
         }
      }
   }

   //metodos auxiliares
   protected int parent(int i) {
      return (i - 1) / 3;
   }

   /**
    * Deve retornar o indice que representa o filho a esquerda do elemento indexado pela posicao i no vetor
    */
   protected int left(int i) {
      return (i * 3) + 1;
   }

   /**
    * Deve retornar o indice que representa o filho do meio do elemento indexado pela posicao i no vetor
    */
   protected int middle(int i) {
      return ((i * 3) + 1) + 1;
   }

   /**
    * Deve retornar o indice que representa o filho a direita do elemento indexado pela posicao i no vetor
    */
   protected int right(int i) {
      return ((i * 3) + 1) + 1 + 1;
   }

   private void heapify(int position) {
      if (position >= 0 && !isEmpty()) {
         int left = left(position);
         int middle = middle(position);
         int right = right(position);

         int max = getMax(left, middle, right);

         if (max >= 0 && compare(heap[position], heap[max]) < 0) {
            swap(position, max);
            heapify(max);
         }
      }
   }

   private void swap(int position, int max) {
      T temp = heap[position];
      heap[position] = heap[max];
      heap[max] = temp;
   }

   private int getMax(int indexLeft, int indexMiddle, int indexRight) {
      if (indexLeft > index) {
         return -1;
      } else if (indexMiddle > index) {
         return indexLeft;
      } else if (indexRight > index) {
         return Math.max(indexLeft, indexMiddle);

      } else if (indexLeft < size() && indexRight < size() && indexMiddle < size()) {
         int firstMax = Math.max(indexLeft, indexMiddle);
         return Math.max(firstMax, indexRight);
      }
      return -1;
   }

   public int compare(T element, T otherElement) {
      return this.comparator.compare(element, otherElement);
   }

   public boolean isEmpty() {
      return (index == -1);
   }

   @Override
   public String toString() {
      return Arrays.toString(this.heap);
   }

   private boolean isMinHeap() {
      Comparator<T> comparatorTemp = this.comparator;

      this.comparator = new Comparator<T>() {

         @Override
         public int compare(T o1, T o2) {
            return o1.compareTo(o2);
         }
      };

      boolean result = true;
      if (isEmpty()) {
         result = true;
      } else if (size() >= 2) {
         if (compare(heap[0], heap[index]) < 0) {
            result = true;
         } else {
            result = false;
         }
      }
      this.comparator = comparatorTemp;
      return result;
   }

}
