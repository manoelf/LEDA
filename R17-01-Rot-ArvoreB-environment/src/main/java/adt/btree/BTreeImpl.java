package adt.btree;

import java.util.Collections;

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
		if (!node.isEmpty() && node.children.get(0) != null) {
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
		if (node != null && !node.isEmpty()) {
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
		return size(this.root);

	}

	private int size(BNode<T> node) {
		if (!node.isEmpty()) {
			if (node.isLeaf()) {
				return node.size();
			}
			for (BNode<T> element : node.children) {
				return node.size() + size(element);
			}
		}
		return 0;
	}

	@Override
	public BNodePosition<T> search(T element) {
		if (element != null) {
			return search(this.root, element);
		}

		return new BNodePosition<>();
	}

	private BNodePosition<T> search(BNode<T> node, T element) {
		if (!node.isEmpty()) {
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
		return new BNodePosition<>();
	}

	@Override
	public void insert(T element) {
		if (element != null) {
			BNode<T> node = search(element).node;
			if (node != null) {
				insert(node, element);
			}
		}
	}

	private void insert(BNode<T> node, T element) {
		node.addElement(element);
		Collections.sort(node.getElements());
		if (node.isFull()) {
			split(node);
		}
	}

	private void split(BNode<T> node) {
		if (node.equals(root)) {
			BNode<T> newRoot = new BNode<>(node.getMaxKeys() + 1);
			newRoot.setParent(node.getParent());

			newRoot.addElement(getMedianElement(node));
			newRoot.addChild(0, divideFirstPart(node));
			newRoot.addChild(1, divideSecondPart(node));
			this.root = newRoot;
			
		} else {
			T middle = getMedianElement(node);
			BNode<T> parent = node.getParent();
			insert(parent, middle);

			int position = parent.elements.indexOf(middle);
			parent.addChild(position, divideFirstPart(node));
			parent.addChild(position + 1, divideSecondPart(node));
			node = divideFirstPart(node);
		}

	}

	private T getMedianElement(BNode<T> node) {
		return node.getElementAt(node.size() / 2);
	}

	private BNode<T> divideFirstPart(BNode<T> node) {
		BNode<T> newNode = new BNode<>(node.getMaxKeys() + 1);
		newNode.setParent(node.getParent());
		for (int i = 0; i < (node.size() / 2) - 1; i++) {
			newNode.addElement(node.getElementAt(i));
		}
		if (!node.isLeaf()) {
			for (int i = 0; i < node.getChildren().size() / 2; i++) {
				newNode.addChild(i, node.getChildren().get(i));
			}
		}

		return newNode;
	}

	private BNode<T> divideSecondPart(BNode<T> node) {
		BNode<T> newNode = new BNode<>(node.getMaxKeys() + 1);
		newNode.setParent(node.getParent());
		for (int i = (node.size() / 2) + 1;  i < node.size(); i++) {
			newNode.addElement(node.getElementAt(i));
		}
		if (!node.isLeaf())
			for (int i = (node.getChildren().size() / 2) + 1; i < node.getChildren().size(); i++) {
				newNode.addChild(i, node.getChildren().get(i));
			}

		return newNode;
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
