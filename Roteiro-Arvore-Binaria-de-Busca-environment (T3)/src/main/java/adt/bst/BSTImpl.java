package adt.bst;

<<<<<<< HEAD
=======
import java.util.Arrays;
import java.util.Comparator;

import adt.bt.BTNode;

>>>>>>> d48ea24bba598eeb7e71565f1d0476de2a34e31c
public class BSTImpl<T extends Comparable<T>> implements BST<T> {

	protected  BSTNode<T> root;
	
	
	public BSTImpl() {
		root = new BSTNode<T>();
	}

	public BSTNode<T> getRoot(){
		return this.root;
	}
	
	@Override
	public boolean isEmpty() {
		return root.isEmpty();
	}

	@Override
	public int height() {
		// TODO Auto-generated method stub
		throw new RuntimeException("Not implemented yet!");
	}

	@Override
	public BSTNode<T> search(T element) {
		// TODO Auto-generated method stub
		throw new RuntimeException("Not implemented yet!");
	}

	@Override
	public void insert(T element) {
<<<<<<< HEAD
		// TODO Auto-generated method stub
		throw new RuntimeException("Not implemented yet!");
	}

	@Override
	public BSTNode<T> maximum() {
		// TODO Auto-generated method stub
		throw new RuntimeException("Not implemented yet!");
=======
		if (element != null) {
			insert(this.root, element);
		}
	
	}

	private void insert(BTNode<T> node, T element) {
		if (node.isEmpty()) {
			node.setData(element);
			node.setLeft(new BSTNode<T >());
			node.setRight(new BSTNode<T >());
		} else if (node.getData().compareTo(element) < 0) {
			insert(node.getRight(), element);
		} else if (node.getData().compareTo(element) > 0) {
			insert(node.getLeft(), element);
		}
	}
	
	@Override
	public BSTNode<T> maximum() {
		if (isEmpty()) {
			return null;
		}
		return maximum(this.root);
	}

	private BSTNode<T> maximum(BSTNode<T> node) {
		if (node.getRight().isEmpty()) {
			return node;
		} else {
			return maximum((BSTNode<T>)node.getRight());
		}
>>>>>>> d48ea24bba598eeb7e71565f1d0476de2a34e31c
	}

	@Override
	public BSTNode<T> minimum() {
<<<<<<< HEAD
		// TODO Auto-generated method stub
		throw new RuntimeException("Not implemented yet!");
=======
		if (isEmpty()) {
			return null;
		}
		return minimum(this.root);
	}

	private BSTNode<T> minimum(BSTNode<T> node) {
		if (node.getLeft().isEmpty()) {
			return node;
		} else {
			return minimum((BSTNode<T>)node.getLeft());
		}
>>>>>>> d48ea24bba598eeb7e71565f1d0476de2a34e31c
	}

	@Override
	public BSTNode<T> sucessor(T element) {
		// TODO Auto-generated method stub
		throw new RuntimeException("Not implemented yet!");
	}

	@Override
	public BSTNode<T> predecessor(T element) {
		// TODO Auto-generated method stub
		throw new RuntimeException("Not implemented yet!");
	}

	@Override
	public void remove(T element) {
		// TODO Auto-generated method stub
		throw new RuntimeException("Not implemented yet!");
	}

	@Override
	public T[] preOrder() {
<<<<<<< HEAD
		// TODO Auto-generated method stub
		throw new RuntimeException("Not implemented yet!");
=======
		T[] array = (T[]) new Comparable[this.size()];
		preOrder(this.root, array, 0);
		return array;
	}

	private int preOrder(BTNode<T> node, T[] array, int index) {
		if (!node.isEmpty()) {
			array[index] = node.getData();
			index = preOrder(node.getLeft(), array, index++);
			index = preOrder(node.getRight(), array, index);
		}
		return index;
>>>>>>> d48ea24bba598eeb7e71565f1d0476de2a34e31c
	}

	@Override
	public T[] order() {
<<<<<<< HEAD
		// TODO Auto-generated method stub
		throw new RuntimeException("Not implemented yet!");
=======
		T[] array = (T[]) new Comparable[this.size()];
		order(this.root, array, 0);
		return array;
	}

	private int order(BTNode<T> node, T[] array, int index) {
		if (!node.isEmpty()) {
			index = order(node.getLeft(), array, index);
			array[index++] = node.getData();
			index = order(node.getRight(), array, index);
		}
		
		return index;
>>>>>>> d48ea24bba598eeb7e71565f1d0476de2a34e31c
	}

	@Override
	public T[] postOrder() {
<<<<<<< HEAD
		// TODO Auto-generated method stub
		throw new RuntimeException("Not implemented yet!");
=======
		T[] array = (T[]) new Comparable[this.size()];
		postOrder(this.root, array, 0);
		return array;
	}

	private int postOrder(BTNode<T> node, T[] array, int index) {
		if (node.isEmpty()) {
			index = postOrder(node.getRight(), array, index);
			index = postOrder(node.getLeft(), array, index);
			array[index++] = node.getData();
		}
		return index;
>>>>>>> d48ea24bba598eeb7e71565f1d0476de2a34e31c
	}

	/**
	 * This method is already implemented using recursion. You must understand how it work and 
	 * use similar idea with the other methods. 
	 */
	@Override
	public int size() {
		return size(root);
	}
	private int size(BSTNode<T> node){
		int result = 0;
		//base case means doing nothing (return 0)
		if(!node.isEmpty()){ //indusctive case
			result = 1 + size((BSTNode<T>)node.getLeft()) + size((BSTNode<T>)node.getRight());
		}
		return result;
	}
<<<<<<< HEAD
=======
	
	
	public static void main(String[] args) {
		BSTImpl<Integer> bst = new BSTImpl<>();
		bst.insert(100);
		bst.insert(60);
		bst.insert(20);
		bst.insert(70);
		bst.insert(120);
		bst.insert(110);
		bst.insert(130);
		System.out.println(bst.size());
		System.out.println(bst.root);
		System.out.println(bst.root.getLeft());
		System.out.println(bst.root.getLeft().getLeft());
		System.out.println(bst.root.getRight());
		System.out.println(bst.root.getRight().getRight());
		System.out.println(bst.root.getRight().getRight().getRight());
		System.out.println(bst.root.getRight().getRight().getRight().getRight());
		System.out.println();
		System.out.println(bst.maximum());
		System.out.println();
		System.out.println(bst.minimum());
		System.out.println();
		System.out.println(Arrays.toString(bst.postOrder()));
		
	}
>>>>>>> d48ea24bba598eeb7e71565f1d0476de2a34e31c

}
