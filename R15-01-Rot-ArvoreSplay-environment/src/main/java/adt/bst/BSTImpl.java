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

	public int height(BSTNode<T> node) {
		int resultLeft = -1;
		int resultRight = -1;

		if (!node.isEmpty()) {
			resultLeft = 1 + height((BSTNode<T>) node.getLeft());
			resultRight = 1 + height((BSTNode<T>) node.getRight());
		}
		if (resultLeft > resultRight) {
			return resultLeft;
		} else {
			return resultRight;
		}
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
		return node;
	}

	@Override
	public void insert(T element) {
		if (element != null) {
			insert(this.root, element);
		}
	}

	protected BSTNode<T> insert(BSTNode<T> node, T element) {
		if (node.isEmpty()) {

			node.setData(element);
			node.setLeft(new BSTNode<T>());
			node.getLeft().setParent(node);
			node.setRight(new BSTNode<T>());
			node.getRight().setParent(node);
			return node;
		} else if (node.getData().compareTo(element) > 0) {
			return insert((BSTNode<T>) node.getLeft(), element);
		} else if (node.getData().compareTo(element) < 0) {
			return insert((BSTNode<T>) node.getRight(), element);
		}
		return node;
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
		} else if (parent.getData().compareTo(node.getData()) > 0) {
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
		} else if (parent.getData().compareTo(node.getData()) < 0) {
			return (BSTNode<T>) parent;
		} else {
			return predecessor(node, parent.getParent());
		}
	}

	@Override
	public void remove(T element) {
		BSTNode<T> node = this.search(element);
		if (element != null && !node.isEmpty()) {
			remove(node);
		}
	}

	protected BTNode<T> remove(BSTNode<T> node) {
		// FOLHA
		if (node.isLeaf()) {
			return removeLeaf(node);
			// APENAS UM FILHO
		} else if (singleChild(node)) {
			// APENAS FILHO A ESQUERDA
			if (node.getRight().isEmpty()) {
				return removeLeftNode(node);
				// APENAS FILHO A DIREITA
			} else {
				// ROOT COM FILHO APENAS A DIREITA
				return removeRight(node);
			}
			// NODE TEM DOIS FILHOS
		} else {
			return removeNodeTwoChild(node);
		}
	}

	private boolean singleChild(BTNode<T> node) {
		if (node.isEmpty()) {
			return false;
		} else if (node.getLeft().isEmpty() && !node.getRight().isEmpty()) {
			return true;
		} else if (!node.getLeft().isEmpty() && node.getRight().isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isRoot(BTNode<T> node) {
		return node.equals(this.root);
	}

	private BTNode<T> removeLeaf(BTNode<T> node) {
		node.setData(null);
		node.setLeft(null);
		node.setRight(null);
		return node;
	}

	private BTNode<T> removeLeftNode(BTNode<T> node) {
		// ROOT COM FILHO APENAS A ESQUERDA
		if (isRoot(node)) {
			removeRoot(node);
			// NAO Eh ROOT
		} else {
			node.getLeft().setParent(node.getParent());
			// NODE Eh ESQUERDA DO SEU PARENT
			if (node.getParent().getLeft().equals(node)) {
				node.getParent().setLeft(node.getLeft());
				// NODE Eh DIREITA DE SEU PARENT
			} else {
				node.getParent().setRight(node.getLeft());
			}
		}
		return node;
	}

	private BTNode<T> removeRight(BTNode<T> node) {
		if (isRoot(node)) {
			removeRoot(node);
			// NAO Eh ROOT
		} else {
			node.getRight().setParent(node.getParent());
			// NODE Eh ESQUERDA DE SEU PARENT
			if (node.getParent().getLeft().equals(node)) {
				node.getParent().setLeft(node.getRight());
				// NODE Eh DIREITA DE SEU PARENT
			} else {
				node.getParent().setRight(node.getRight());
			}
		}
		return node;
	}

	private BTNode<T> removeRoot(BTNode<T> node) {
		// APENAS FILHO A ESQUERDA
		if (node.getRight().isEmpty()) {
			this.root = (BSTNode<T>) node.getLeft();
			node.getLeft().setParent(node.getParent());
			node.setLeft(null);
		} else {
			this.root = (BSTNode<T>) node.getRight();
			node.getRight().setParent(node.getParent());
			node.setRight(null);
		}
		return node;
	}

	private BTNode<T> removeNodeTwoChild(BTNode<T> node) {
		BSTNode<T> sucessor = sucessor(node.getData());
		T sucessorData = sucessor.getData();
		sucessor.setData(node.getData());
		node.setData(sucessorData);
		return remove(sucessor);
	}

	@Override
	public T[] preOrder() {
		@SuppressWarnings("unchecked")
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
		@SuppressWarnings("unchecked")
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
		@SuppressWarnings("unchecked")
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

	private int size(BSTNode<T> node) {
		int result = 0;
		// base case means doing nothing (return 0)
		if (!node.isEmpty()) { // indusctive case
			result = 1 + size((BSTNode<T>) node.getLeft())
					+ size((BSTNode<T>) node.getRight());
		}
		return result;
	}

}
