package adt.btree;

import java.util.LinkedList;

public class BTreeImpl<T extends Comparable<T>> implements BTree<T> {

	protected BNode<T> root;
	protected int order;

	public BTreeImpl(int order) {
		this.order = order;
		this.root = new BNode<T>(order);
	}

	@Override
	public BNode<T> getRoot() {
		return this.root;
	}

	@Override
	public boolean isEmpty() {
		return this.root.isEmpty();
	}

	@Override
	public int height() {
		return height(this.root);
	}

	private int height(BNode<T> node) {
		if (!node.isEmpty()) {
			return 1 + height(node.children.get(0));
		}
		return 0;
	}

	@Override
	public BNode<T>[] depthLeftOrder() {
		@SuppressWarnings("unchecked")
		BNode<T>[] array = new BNode[size()];
		depthLeftOrder(getRoot(), array, 0);
		return array;
	}

	private void depthLeftOrder(BNode<T> node, BNode<T>[] array, int i) {
		if (!node.isEmpty()) {
			array[i++] = node;
			int index = 0;
			while (index < node.size()) {
				array[i + index] = node.children.get(index);
				index++;
			}
			i = i + index;
			index = 0;
			while (index < node.size()) {
				depthLeftOrder(node.children.get(index), array, i);
				index++;
			}
		}
	}

	@Override
	public int size() {
		// TODO Implement your code here
		throw new UnsupportedOperationException("Not Implemented yet!");
	}

	@Override
	public BNodePosition<T> search(T element) {
		if (element != null) {
			return search(this.root, element);
		}

		return new BNodePosition<>();
	}

	private BNodePosition<T> search(BNode<T> node, T element) {
		int i = 0;
		T value = node.getElementAt(i);
		while (i < node.size() && element.compareTo(value) > 0) {
			i++;
			value = node.getElementAt(i);
		}

		if (value.equals(element)) {
			return new BNodePosition<>(node, i);
		} else if (i < node.size()) {
			return search(node.getChildren().get(i), element);
		} else {
			return new BNodePosition<>();
		}
	}

	@Override
	public void insert(T element) {
		// TODO Implement your code here
		throw new UnsupportedOperationException("Not Implemented yet!");

	}

	private void split(BNode<T> node) {
		// TODO Implement your code here
		throw new UnsupportedOperationException("Not Implemented yet!");
	}

	private void promote(BNode<T> node) {
		// TODO Implement your code here
		throw new UnsupportedOperationException("Not Implemented yet!");
	}

	// NAO PRECISA IMPLEMENTAR OS METODOS ABAIXO
	@Override
	public BNode<T> maximum(BNode<T> node) {
		// NAO PRECISA IMPLEMENTAR
		throw new UnsupportedOperationException("Not Implemented yet!");
	}

	@Override
	public BNode<T> minimum(BNode<T> node) {
		// NAO PRECISA IMPLEMENTAR
		throw new UnsupportedOperationException("Not Implemented yet!");
	}

	@Override
	public void remove(T element) {
		// NAO PRECISA IMPLEMENTAR
		throw new UnsupportedOperationException("Not Implemented yet!");
	}

}
