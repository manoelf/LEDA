package adt.splaytree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.BTNode;

public class SplayTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements SplayTree<T> {

	private void splay(BSTNode<T> node) {
		if (node != null && !node.isEmpty()) {
			if (!isRoot(node)) {
				BSTNode<T> granfather = (BSTNode<T>) node.getParent().getParent();
				// ZIG
				if (isRoot(node.getParent()) && isRightChild(node)) {
					leftRotation(node.getParent());
				} else if (isRoot(node.getParent()) && isLeftChild(node)) {
					rightRotation(node.getParent());
				}

				// ZIG-ZIG
				else if (isRightChild(node) && isRightChild(node.getParent())) {
					leftRotation(granfather);
					leftRotation(node.getParent());
				} else if (isLeftChild(node) && isLeftChild(node.getParent())) {
					rightRotation(granfather);
					rightRotation(node.getParent());
				}

				// ZIG-ZAG
				else if (isLeftChild(node) && isRightChild(node.getParent())) {
					rightRotation(node.getParent());
					leftRotation(node.getParent());
				} else if (isRightChild(node) && isLeftChild(node.getParent())) {
					leftRotation(node.getParent());
					rightRotation(node.getParent());
				}
				splay(node);
			}
		}
	}

	private void leftRotation(BTNode<T> node) {
		if (node != null && !node.isEmpty()) {
			BSTNode<T> rightNode = (BSTNode<T>) node.getRight();

			rightNode.setParent(node.getParent());

			if (isRightChild(node)) {
				node.getParent().setRight(rightNode);
			} else if (isLeftChild(node)) {
				node.getParent().setLeft(rightNode);
			}

			node.setRight(rightNode.getLeft());
			node.getRight().setParent(node);

			rightNode.setLeft(node);
			node.setParent(rightNode);

			if (isRoot(node)) {
				this.root = rightNode;
			}

		}
	}

	private void rightRotation(BTNode<T> node) {
		if (node != null && !node.isEmpty()) {
			BSTNode<T> leftNode = (BSTNode<T>) node.getLeft();

			leftNode.setParent(node.getParent());

			if (isRightChild(node)) {
				node.getParent().setRight(leftNode);
			} else if (isLeftChild(node)) {
				node.getParent().setLeft(leftNode);
			}

			node.setLeft(leftNode.getRight());
			node.getLeft().setParent(node);

			leftNode.setRight(node);
			node.setParent(leftNode);

			if (isRoot(node)) {
				this.root = leftNode;
			}

		}
	}

	private boolean isRightChild(BTNode<T> node) {
		if (node.getParent() == null) {
			return false;
		} else if (node.getParent().getRight().equals(node)) {
			return true;
		} else {
			return false;
		}
	}

	private boolean isLeftChild(BTNode<T> node) {
		if (isRoot(node)) {
			return false;
		} else if (node.getParent().getLeft().equals(node)) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	public BSTNode<T> search(T element) {
		if (element != null && !isEmpty()) {
			BSTNode<T> node = super.search(element);
			if (node.isEmpty())
				splay((BSTNode<T>) node.getParent());
			else
				splay(node);

			return node;
		}
		return new BSTNode<T>();
	}

	@Override
	public void insert(T element) {
		if (element != null) {
			super.insert(element);
			this.search(element);
		}
	}

	@Override
	public void remove(T element) {
		if (!isEmpty() && element != null) {
			BSTNode<T> node = super.search(element);
			super.remove(node);
			splay((BSTNode<T>) node.getParent());
		}

	}

}
