package adt.bst;

import adt.bt.BTNode;

public class SimpleBSTManipulationImpl<T extends Comparable<T>> implements SimpleBSTManipulation<T> {

	@Override
	public boolean equals(BST<T> tree1, BST<T> tree2) {
		if (tree1.isEmpty() && tree2.isEmpty()) {
			return true;
		} else if (!tree1.isEmpty() && !tree2.isEmpty()) {
			return equals(tree1.getRoot(), tree2.getRoot());
		} else {
			return false;
		}

	}

	private boolean equals(BTNode<T> node1, BTNode<T> node2) {
		boolean result = false;
		if (!node1.isEmpty() && !node2.isEmpty()) {
			if (node1.getData().equals(node2.getData())) {
				result = equals(node1.getLeft(), node2.getLeft());
				result = equals(node1.getRight(), node2.getRight());
				return result;
			} else {
				return false;
			}

		} else if (node1.isEmpty() && node2.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean isSimilar(BST<T> tree1, BST<T> tree2) {
		if (tree1.isEmpty() && tree2.isEmpty()) {
			return true;
		} else if (!tree1.isEmpty() && !tree2.isEmpty()) {
			return isSimilar(tree1.getRoot(), tree2.getRoot());
		} else {
			return false;
		}
	}

	private boolean isSimilar(BTNode<T> node1, BTNode<T> node2) {
		boolean result = false;
		if (!node1.isEmpty() && !node2.isEmpty()) {
			result = isSimilar(node1.getLeft(), node2.getLeft());
			result = isSimilar(node1.getRight(), node2.getRight());
			return result;
		} else if (node1.isEmpty() && node2.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public T orderStatistic(BST<T> tree, int k) {
		if (k <= tree.size()) {
			return orderStatitistic(tree.getRoot(), k - 1, 0);
		}

		return null;
	}

	private T orderStatitistic(BTNode<T> node, int k, int index) {
		T result = null;
		if (!node.isEmpty()) {
			if (k == index) {
				return node.getData();
			} else {
				result = orderStatitistic(node.getLeft(), k, index);
				result = orderStatitistic(node.getRight(), k, ++index);
				return result;
			}
		}

		return null;
	}


}
