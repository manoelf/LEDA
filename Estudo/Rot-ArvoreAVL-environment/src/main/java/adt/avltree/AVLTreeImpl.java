package adt.avltree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.BTNode;

/**
 * 
 * Performs consistency validations within a AVL Tree instance
 * 
 * @author Claudio Campelo
 *
 * @param <T>
 */
public class AVLTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements AVLTree<T> {

	// TODO Do not forget: you must override the methods insert and remove
	// conveniently.

	// AUXILIARY
	protected int calculateBalance(BSTNode<T> node) {
			int leftHeight = height((BSTNode<T>) node.getLeft());
			int rightHeight = height((BSTNode<T>) node.getRight());
			return rightHeight - leftHeight;
	}

	// AUXILIARY
	protected void rebalance(BSTNode<T> node) {
		if (node != null && !isEmpty()) {
			int balance = calculateBalance(node);
			// Desbalanceado pra direita
			if (balance > 1) {
				int balanceSon = calculateBalance((BSTNode<T>) node.getRight());
				if (balanceSon >= 0) {
					leftRotation(node);
				} else {
					rightRotation((BSTNode<T>) node.getRight());
					leftRotation(node);
				}

			} else if (balance < -1) {
				int balanceSon = calculateBalance((BSTNode<T>) node.getLeft());
				if (balanceSon <= 0) {
					rightRotation(node);
				} else {
					leftRotation((BSTNode<T>) node.getLeft());
					rightRotation(node);
				}
			}
		}
	}

	// AUXILIARY
	protected void rebalanceUp(BSTNode<T> node) {
		if (node != null) {
			rebalance((BSTNode<T>) node);
			rebalanceUp((BSTNode<T>) node.getParent());
		}

	}

	// AUXILIARY
	protected void leftRotation(BSTNode<T> node) {
		if (node != null) {
			BSTNode<T> rightNode = (BSTNode<T>) node.getRight();
			rightNode.setParent(node.getParent());

			if (isLeftChildren(node)) {
				node.getParent().setLeft(rightNode);
			} else if (isRightChildren(node)) {
				node.getParent().setRight(rightNode);
			}

			node.setRight(rightNode.getLeft());
			node.getRight().setParent(node);
			
			rightNode.setLeft(node);
			node.setParent(rightNode);

			if (node.equals(root)) {
				this.root = rightNode;
			}
		}
	}


	// AUXILIARY
	protected void rightRotation(BSTNode<T> node) {
		if (node != null) {
			BSTNode<T> leftNode = (BSTNode<T>) node.getLeft();

			leftNode.setParent(node.getParent());

			if (isLeftChildren(node)) {
				node.getParent().setLeft(leftNode);
			} else if (isRightChildren(node)) {
				node.getParent().setRight(leftNode);
			}

			node.setLeft(leftNode.getRight());
			node.getLeft().setParent(node);
			
			leftNode.setRight(node);
			node.setParent(leftNode);

			if (node.equals(root)) {
				this.root = leftNode;
			}

		}

	}

	private boolean isLeftChildren(BSTNode<T> node) {
		if (isRoot(node)) {
			return false;
		} else if (node.getParent().getLeft().equals(node)) {
			return true;
		} else {
			return false;
		}
	}

	private boolean isRightChildren(BSTNode<T> node) {
		if (isRoot(node)) {
			return false;
		} else if (node.getParent().getRight().equals(node)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void insert(T element) {
		if (element != null) {
			super.insert(element);
			BTNode<T> node = search(element);
			rebalanceUp((BSTNode<T>) node);
		}
	}

	@Override
	public void remove(T element) {
		if (element != null) {
			BSTNode<T> node = search(element);
			BSTNode<T> toBeBalanced;;
			if (!node.isEmpty()) {
				if (hasTwoChild(node)) {
					toBeBalanced = super.sucessor(element);
				} else {
					toBeBalanced = node;
				}
				
				super.remove(element);
				rebalanceUp((BSTNode<T>) toBeBalanced.getParent());
				
			}
			
		}
	}
	
	public boolean hasTwoChild(BSTNode<T> node) {
		if (node == null && node.isEmpty()) {
			return false;
		} else if (!node.getLeft().isEmpty() && !node.getRight().isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

}
