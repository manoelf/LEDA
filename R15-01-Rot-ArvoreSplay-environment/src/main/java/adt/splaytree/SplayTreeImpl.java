package adt.splaytree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.BTNode;
import adt.bt.Util;

public class SplayTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements SplayTree<T> {

	private void splay(BSTNode<T> node) {
		if (node != null  && !isRoot(node)) {
			BSTNode<T> parent = (BSTNode<T>) node.getParent();
			// ZIG
			if (isRoot(node.getParent())) {
				if (isLeftChildren(node)) {
					rightRotation(parent);
				} else {
					leftRotation(parent);
				}
			}
			// ZIG_ZIG
			else if (isLeftChildren(node) && isLeftChildren(parent)) {
				rightRotation((BSTNode<T>) parent.getParent());
				rightRotation(parent);
			} else if (isRightChildren(node) && isRightChildren(parent)) {
				leftRotation((BSTNode<T>) parent.getParent());
				leftRotation(parent);

			// ZIG_ZAG
			} else {
				if (isLeftChildren(node)) {
					rightRotation(parent);
					leftRotation((BSTNode<T>) parent.getParent());
				} else {
					leftRotation(parent);
					rightRotation((BSTNode<T>) parent.getParent());
				}

			}
			splay(node);
		}

	}

	private boolean isLeftChildren(BTNode<T> node) {
		BSTNode<T> parent = (BSTNode<T>) node.getParent();
		if (parent != null)
			return parent.getLeft().equals(node);
		else
			return false;
	}

	private boolean isRightChildren(BSTNode<T> node) {
		BSTNode<T> parent = (BSTNode<T>) node.getParent();
		if (parent != null)
			return parent.getRight().equals(node);
		else
			return false;
	}

	// AUXILIARY
	protected void leftRotation(BSTNode<T> node) {
		if (node != null && !node.isEmpty()) {

			BSTNode<T> newRoot = Util.leftRotation(node);

			if (node.equals(this.root)) {
				this.root = newRoot;
			}

		}
	}

	// AUXILIARY
	protected void rightRotation(BSTNode<T> node) {
		if (node != null && !node.isEmpty()) {

			BSTNode<T> newRoot = Util.rightRotation(node);

			if (node.equals(this.root)) {
				this.root = newRoot;
			}
		}
	}

	@Override
	public BSTNode<T> search(T element) {
		BSTNode<T> node = super.search(element);
		if (node.isEmpty()) {
			splay((BSTNode<T>) node.getParent());
		} else {
			splay(node);
		}
		return node;
	}

	@Override
	public void insert(T element) {
		if (element != null) {
			super.insert(element);
			search(element);
		}

	}

	@Override
	public void remove(T element) {
		BSTNode<T> node = super.search(element);
		super.remove(node);
		splay((BSTNode<T>) node.getParent());
	}
}
