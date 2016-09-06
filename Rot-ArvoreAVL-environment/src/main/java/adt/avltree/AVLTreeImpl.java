package adt.avltree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;

/**
 * 
 * Performs consistency validations within a AVL Tree instance
 * 
 * @author Claudio Campelo
 *
 * @param <T>
 */
public class AVLTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements
		AVLTree<T> {

	@Override
	public void insert(T element) {
		if (element != null) {
			rebalanceUp(super.insert(this.root, element));
		}
	
	}

	@Override
	public void remove(T element) {
		BSTNode<T> node =  search(element);
		if (!node.isEmpty()) {
			node = (BSTNode<T>) super.remove(node).getParent();
			rebalanceUp(node);
		}
	}
	

	// AUXILIARY
	protected int calculateBalance(BSTNode<T> node) {
		int leftHeight = height(node.getLeft());
		int rightHeight = height(node.getRight());
		return rightHeight - leftHeight;
	}

	// AUXILIARY
	protected void rebalance(BSTNode<T> node) {
		if (node != null && !node.isEmpty()) {
			int balance = calculateBalance(node);

			if (balance > 1) {
				if (calculateBalance((BSTNode<T>) node.getRight()) < 0) {
					// ROTACAO DUPLA A ESQUERDA
					rightRotation((BSTNode<T>) node.getRight());
					leftRotation(node);
				} else {
					// ROTACAO A ESQUERDA
					leftRotation(node);
				}
			} else if (balance < -1) {
				if (calculateBalance((BSTNode<T>) node.getLeft()) > 0) {
					// ROTACAO DUPLA A DIREITA
					leftRotation((BSTNode<T>) node.getLeft());
					rightRotation(node);
				} else {
					// ROTACAO A DIREITA
					rightRotation(node);
				}
			}
		}
	}

	// AUXILIARY
	protected void rebalanceUp(BSTNode<T> node) {
		if (node != null && !node.isEmpty()) {
			int balance = calculateBalance(node);
			if (balance < -1 || balance > 1) {
				rebalance(node);
			} else {
				rebalanceUp((BSTNode<T>) node.getParent());
			}
		}
	}

	// AUXILIARY
	protected void leftRotation(BSTNode<T> node) {
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
		
			if (node.equals(this.root)) {
				this.root = newRoot;
			}

		}
	}

	// AUXILIARY
	protected void rightRotation(BSTNode<T> node) {
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
			
			if (node.equals(this.root)) {
				this.root = newRoot;
			}
		}
	}
}
