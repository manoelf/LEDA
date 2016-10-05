package adt.heap.ternary;

/**
 * Classe que representa a ideia de heap ternaria, onde cada elemento possui no 
 * maximo 3 filhos.
 * @author Adalberto
 *
 * @param <T>
 */
public interface TernaryHeap<T extends Comparable<T>> {
	/**
	 * Inserts a new element into the heap and maintains the invariant.
	 */
	public abstract void insert(T element);
	
	/**
	 * Removes and returns the root element of the heap. The method returns null
	 * if the heap is empty. If the heap is min-heap the element is the minimum
	 * of the heap; otherwise, the heap is max-heap and the element is the
	 * maximum of the heap.
	 */
	public abstract T extractRootElement();
	
	/**
	 * Returns the root element without removing it. It returns null if the heap
	 * is empty. If the heap is min-heap the element is the minimum of the heap;
	 * otherwise, the heap is max-heap and the element is the maximum of the
	 * heap.
	 */
	public T rootElement();
	
	/**
	 * Returns an array representing the underlying structure (internal array)
	 * of the heap.
	 * 
	 * @return
	 */
	public T[] toArray();

	/**
	 * Returns the number of elements in this heap.
	 * 
	 * @return
	 */
	public int size();
	
	/**
	 * Encontra e retorna o k-esimo menor elemento sem alterar a heap.
	 * @param k
	 * @return
	 */
	public T findKth(int k);
	
	/**
	 * Remove o k-esimo menor elemento e mantem as propriedades da heap.
	 * Voce deve utilizar o extractRoot mas deve manter todos os elementos
	 * (exceto o removido) na heap e preservar as propriedades da heap.
	 * @param k
	 */
	public void removeKth(int k);
}
