package adt.bst;

import adt.bt.BTNode;

public class BSTImpl<T extends Comparable<T>> implements BST<T> {

	protected BSTNode<T> root;

	public BSTImpl() {
		root = new BSTNode<T>();
	}

	public BSTNode<T> getRoot() {
		return this.root;
	}

	@Override
	public boolean isEmpty() {
		return root.isEmpty();
	}

	@Override
	public int height() {
		return height(this.root);
	}

	private int height(BTNode<T> node) {
		int leftHeight = -1;
		int rightHeight = -1;
		if (!node.isEmpty()) {
			leftHeight = 1 + height(node.getLeft());
			rightHeight = 1 + height(node.getRight());
		}
		return leftHeight > rightHeight ? leftHeight : rightHeight;
	}

	@Override
	public BSTNode<T> search(T element) {
		if (element == null || isEmpty()) {
			return new BSTNode<>();
		} else {
			return search(this.root, element);
		}
	}

	private BSTNode<T> search(BSTNode<T> node, T element) {
		if (node.isEmpty()) {
			return new BSTNode<>();
		} else if (element.equals(node.getData())) {
			return node;
		} else if (element.compareTo(node.getData()) < 0) {
			return search((BSTNode<T>) node.getLeft(), element);
		} else {
			return search((BSTNode<T>) node.getRight(), element);
		}

	}

	@Override
	public void insert(T element) {
		if (element != null) {
			insert(this.root, element);
		}
	
	}

	private void insert(BSTNode<T> node, T element) {
		if (node.isEmpty()) {
			BSTNode<T> nilLeft = new BSTNode<>();
			nilLeft.setParent(node);
			BSTNode<T> nilRight = new BSTNode<>();
			nilRight.setParent(node);
			node.setData(element);
			node.setLeft(nilLeft);
			node.setRight(nilRight);
		} else if (element.compareTo(node.getData()) < 0) {
			insert((BSTNode<T>) node.getLeft(), element);
		} else if (element.compareTo(node.getData()) > 0){
			insert((BSTNode<T>) node.getRight(), element);
		}
	}
	

	@Override
	public BSTNode<T> maximum() {
		if (isEmpty()) return null;
		else return maximum(this.root);
	
	}

	private BSTNode<T> maximum(BSTNode<T> node) {
		if (node.getRight().isEmpty()){
			return node;
		} else {
			return maximum((BSTNode<T>) node.getRight());
		}
	}

	@Override
	public BSTNode<T> minimum() {
		if (isEmpty()) return null;
		else return minimum(this.root);
	}

	private BSTNode<T> minimum(BSTNode<T> node) {
		if (node.getLeft().isEmpty()) {
			return node;
		} else {
			return minimum((BSTNode<T>) node.getLeft());
		}
	
	}

	@Override
	public BSTNode<T> sucessor(T element) {
		BSTNode<T> node = search(element);
		if (node.isEmpty()) {
			return null;
		} else {
			return sucessor(node, element);
		}
		
	}

	private BSTNode<T> sucessor(BSTNode<T> node, T element) {
		if (!node.getRight().isEmpty()) {
			return minimum((BSTNode<T>) node.getRight());
		} else {
			return maxParent(node);
		}
	}
	
	private BSTNode<T> maxParent(BSTNode<T> node) {
		if (node.getParent() == null) {
			return null;
		} else if (node.getParent().getData().compareTo(node.getData()) > 0) {
			return (BSTNode<T>) node.getParent();
		} else {
			return maxParent((BSTNode<T>) node.getParent());
		}
	}

	@Override
	public BSTNode<T> predecessor(T element) {
		BSTNode<T> node =  search(element);
		if (node.isEmpty()) {
			return null;
		} else {
			return predecessor(node);
		}
	}

	private BSTNode<T> predecessor(BSTNode<T> node) {
		if (!node.getLeft().isEmpty()){
			return maximum((BSTNode<T>) node.getLeft());
		} else {
			return minorParent((BSTNode<T>) node);
		}
	}

	private BSTNode<T> minorParent(BSTNode<T> node) {
		if (node.getParent() == null) {
			return null;
		} else if (node.getParent().getData().compareTo(node.getData()) < 0) {
			return (BSTNode<T>) node.getParent();
		} else {
			return minorParent((BSTNode<T>) node.getParent());
		}
	}

	@Override
	public void remove(T element) {
		BSTNode<T> node =  search(element);
		if (!node.isEmpty()) {
			if (isLeaf(node)) {
				node.setData(null);
				node.setLeft(null);
				node.setRight(null);
			} else if (singleChildLeft(node)) {
				node.setData(node.getLeft().getData());
				node.setLeft(new BSTNode<T>());
			}
			
			
		}
	}
	
	private boolean isLeaf(BSTNode<T> node) {
		return (node.getLeft().isEmpty() && node.getRight().isEmpty());
	}
	
	
	private boolean singleChildLeft(BSTNode<T> node) {
		return node.getLeft().isEmpty() && !node.getRight().isEmpty();
	}
	
	private boolean singleChildRight(BSTNode<T> node) {
		return node.getLeft().isEmpty() && !node.getRight().isEmpty();
	}
	
	private boolean isRoot(BSTNode<T> node) {
		return node.equals(this.root);
	}

	private void removeRoot(BSTNode<T> node) {
		BSTNode<T> predecessor = predecessor(node);
		node.setData(predecessor.getData());
	}


	@Override
	public T[] preOrder() {
		@SuppressWarnings("unchecked")
		T[] array = (T[]) new Comparable[size()];
		preOrder(array, this.root, 0);
		return array;
	}

	private int preOrder(T[] array, BSTNode<T> node, int i) {
		if (!node.isEmpty()) {
			array[i] = node.getData();
			i = preOrder(array, (BSTNode<T>) node.getLeft(), ++i);
			i = preOrder(array, (BSTNode<T>) node.getRight(), ++i);
		}
		return i;
	}

	@Override
	public T[] order() {
		@SuppressWarnings("unchecked")
		T[] array = (T[]) new Comparable[size()];
		order(array, this.root, 0);
		return array;
	}

	private int order(T[] array, BSTNode<T> node, int i) {
		if (!node.isEmpty()) {
			i = order(array, (BSTNode<T>) node.getLeft(), i);
			array[i++] = node.getData();
			i = order(array, (BSTNode<T>) node.getRight(), i);
		}
		return i;
	}

	@Override
	public T[] postOrder() {
		@SuppressWarnings("unchecked")
		T[] array = (T[]) new Comparable[size()];
		postOrder(array, this.root, 0);
		return array;
	}

	private int postOrder(T[] array, BSTNode<T> node, int i) {
		if (!node.isEmpty()) {
			i = postOrder(array, (BSTNode<T>) node.getLeft(), i);
			i = postOrder(array, (BSTNode<T>) node.getRight(), i);
			array[i++] = node.getData();
		}
		return i;
	}

	/**
	 * This method is already implemented using recursion. You must understand
	 * how it work and use similar idea with the other methods.
	 */
	@Override
	public int size() {
		return size(root);
	}

	private int size(BSTNode<T> node) {
		int result = 0;
		// base case means doing nothing (return 0)
		if (!node.isEmpty()) { // indusctive case
			result = 1 + size((BSTNode<T>) node.getLeft()) + size((BSTNode<T>) node.getRight());
		}
		return result;
	}

}
