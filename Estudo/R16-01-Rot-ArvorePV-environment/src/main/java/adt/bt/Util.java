package adt.bt;

import adt.bst.BSTNode;

public class Util {


	/**
	 * A rotacao a esquerda em node deve subir e retornar seu filho a direita
	 * @param node
	 * @return
	 */
	public static <T extends Comparable<T>> BSTNode<T> leftRotation(BSTNode<T> node) {
		if (node != null && !node.isEmpty()) {
			BSTNode<T> newRoot = (BSTNode<T>) node.getRight();
			if (node.getParent() != null) {
				if (node.getParent().getLeft().equals(node)) {
					node.getParent().setLeft(newRoot);
				} else {
					node.getParent().setRight(newRoot);
				}
			}
			newRoot.setParent(node.getParent());
			node.setRight(newRoot.getLeft());
			node.getRight().setParent(node);
			newRoot.setLeft(node);
			newRoot.getLeft().setParent(newRoot);
			return newRoot;
		}
		return node;
	}

	/**
	 * A rotacao a direita em node deve subir e retornar seu filho a esquerda
	 * @param node
	 * @return
	 */
	public static <T extends Comparable<T>> BSTNode<T> rightRotation(BSTNode<T> node) {
		if (node != null && !node.isEmpty()) {
			BSTNode<T> newRoot = (BSTNode<T>) node.getLeft();
			if (node.getParent() != null) {
				if (node.getParent().getLeft().equals(node)) {
					node.getParent().setLeft(newRoot);
				} else {
					node.getParent().setRight(newRoot);
				}
			}
			newRoot.setParent(node.getParent());
			node.setLeft(newRoot.getRight());
			node.getLeft().setParent(node);
			newRoot.setRight(node);
			newRoot.getRight().setParent(newRoot);
			return newRoot;
		}
		return node;
	}

}
