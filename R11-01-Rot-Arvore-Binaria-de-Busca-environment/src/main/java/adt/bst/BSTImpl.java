package adt.bst;

import java.util.Arrays;

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
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	@Override
	public BSTNode<T> search(T element) {
		if (isEmpty()) {
			return new BSTNode<T>();
		} else {
			return search(this.root, element);
		}
	}

	private BSTNode<T> search(BSTNode<T> node, T element) {
		if (!node.isEmpty()) {
			if (node.getData().equals(element)) {
				return node;
			} else if (node.getData().compareTo(element) > 0) {
				return search((BSTNode<T>) node.getLeft(), element);
			} else {
				return search((BSTNode<T>) node.getRight(), element);
			}
		}
		return new BSTNode<>();
	}

	@Override
	public void insert(T element) {
		if (element != null) {
			insert(this.root, element);
		}
	}

	private void insert(BSTNode<T> node, T element) {
		if (node.isEmpty()) {

			node.setData(element);
			node.setLeft(new BSTNode<T>());
			node.getLeft().setParent(node);
			node.setRight(new BSTNode<T>());
			node.getRight().setParent(node);
		} else if (node.getData().compareTo(element) > 0) {
			insert((BSTNode<T>) node.getLeft(), element);
		} else if (node.getData().compareTo(element) < 0) {
			insert((BSTNode<T>) node.getRight(), element);
		}
	}

	@Override
	public BSTNode<T> maximum() {
		if (isEmpty()) {
			return null;
		} else {
			return maximum(this.root);
		}
	}

	private BSTNode<T> maximum(BSTNode<T> node) {
		if (node.getRight().isEmpty()) {
			return node;
		} else {
			return maximum((BSTNode<T>) node.getRight());
		}
	}

	@Override
	public BSTNode<T> minimum() {
		if (isEmpty()) {
			return null;
		} else {
			return minimum(this.root);
		}
	}

	private BSTNode<T> minimum(BTNode<T> node) {
		if (node.getLeft().isEmpty()) {
			return (BSTNode<T>) node;
		} else {
			return minimum(node.getLeft());
		}
	}

	@Override
	public BSTNode<T> sucessor(T element) {
		BSTNode<T> node = search(element);
		if (element != null && !node.isEmpty()) {
			if (!node.getRight().isEmpty()) {
				return minimum(node.getRight());
			} else {
				return sucessor(node, (BSTNode<T>) node.getParent());
			}
		} else {
			return null;
		}
	}

	private BSTNode<T> sucessor(BSTNode<T> node, BSTNode<T> parent) {
		if (parent == null) {
			return null;
		} else if (!search((BSTNode<T>) parent.getLeft(), node.getData()).isEmpty()) {
			return (BSTNode<T>) parent;
		} else {
			return sucessor(node, (BSTNode<T>) parent.getParent());
		}
	}

	@Override
	public BSTNode<T> predecessor(T element) {
		BSTNode<T> node = search(element);
		if (element == null || node.isEmpty()) {
			return null;
		} else {
			if (!node.getLeft().isEmpty()) {
				return maximum((BSTNode<T>) node.getLeft());
			} else {
				return predecessor(node, node.getParent());
			}
		}
	}

	private BSTNode<T> predecessor(BSTNode<T> node, BTNode<T> parent) {
		if (parent == null) {
			return null;
		} else if (!search((BSTNode<T>) parent.getRight(), node.getData()).isEmpty()) {
			return (BSTNode<T>) parent;
		} else {
			return predecessor(node, parent.getParent());
		}
	}

	@Override
	public void remove(T element) {
		if (element != null && !isEmpty()) {
			if (this.root.getLeft().isEmpty() && this.root.getRight().isEmpty()) {
				this.root.setData(null);
			} else {
				
				
			}
		}
	}

	@Override
	public T[] preOrder() {
		T[] array = (T[]) new Comparable[this.size()];
		preOrder(this.root, array, 0);
		return array;
	}

	private int preOrder(BTNode<T> node, T[] array, int index) {
		if (!node.isEmpty()) {
			array[index] = node.getData();
			index = preOrder(node.getLeft(), array, ++index);
			index = preOrder(node.getRight(), array, index);
		}
		return index;
	}

	@Override
	public T[] order() {
		T[] array = (T[]) new Comparable[this.size()];
		order(this.root, array, 0);
		return array;
	}

	private int order(BSTNode<T> node, T[] array, int index) {
		if (!node.isEmpty()) {
			index = order((BSTNode<T>) node.getLeft(), array, index);
			array[index++] = node.getData();
			index = order((BSTNode<T>) node.getRight(), array, index);
		}
		return index;
	}

	@Override
	public T[] postOrder() {
		T[] array = (T[]) new Comparable[this.size()];
		postOrder(this.root, array, 0);
		return array;
	}

	private int postOrder(BSTNode<T> node, T[] array, int index) {
		if (!node.isEmpty()) {
			index = postOrder((BSTNode<T>) node.getLeft(), array, index);
			index = postOrder((BSTNode<T>) node.getRight(), array, index);
			array[index++] = node.getData();
		}
		return index;
	}

	/**
	 * This method is already implemented using recursion. You must understand
	 * how it work and use similar idea with the other methods.
	 */
	@Override
	public int size() {
		return size(root);
	}

	private int size(BTNode<T> node) {
		int result = 0;
		// base case means doing nothing (return 0)
		if (!node.isEmpty()) { // indusctive case
			result = 1 + size(node.getLeft()) + size(node.getRight());
		}
		return result;
	}

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
		System.out.println(Arrays.toString(bst.preOrder()));
		System.out.println();
		System.out.println(Arrays.toString(bst.order()));
		System.out.println();
		System.out.println(Arrays.toString(bst.postOrder()));
		System.out.println();
		System.out.println(bst.search(20));
		System.out.println(bst.search(100));
		System.out.println(bst.search(60));
		System.out.println(bst.search(120));
		System.out.println(bst.search(70));
		System.out.println(bst.search(100));
		System.out.println(bst.search(130));

		System.out.println("=====================================");

		System.out.println(bst.search(100).getParent());
		System.out.println(bst.search(60).getParent());
		System.out.println(bst.search(20).getParent());
		System.out.println(bst.search(70).getParent());
		System.out.println(bst.search(120).getParent());
		System.out.println(bst.search(110).getParent());
		System.out.println(bst.search(130).getParent());
		
		System.out.println("=====================================");
		System.out.println(bst.sucessor(130));
	}
}
