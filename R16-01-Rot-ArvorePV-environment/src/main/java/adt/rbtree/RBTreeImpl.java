package adt.rbtree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.BTNode;
import adt.bt.Util;
import adt.rbtree.RBNode.Colour;

public class RBTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements RBTree<T> {

	public RBTreeImpl() {
		this.root = new RBNode<T>();
	}

	protected int blackHeight() {
		return blackHeight((RBNode<T>) this.root);
	}

	private int blackHeight(RBNode<T> node) {
		if (!node.isEmpty()) {
			if (isBlack(node)) {
				return 1 + Math.max(blackHeight((RBNode<T>) node.getLeft()), blackHeight((RBNode<T>) node.getRight()));
			} else {
				return Math.max(blackHeight((RBNode<T>) node.getLeft()), blackHeight((RBNode<T>) node.getRight()));
			}
		}
		return 0;
	}

	protected boolean verifyProperties() {
		boolean resp = verifyNodesColour() && verifyNILNodeColour() && verifyRootColour() && verifyChildrenOfRedNodes()
				&& verifyBlackHeight();

		return resp;
	}

	/**
	 * The colour of each node of a RB tree is black or red. This is guaranteed
	 * by the type Colour.
	 */
	private boolean verifyNodesColour() {
		return true; // already implemented
	}

	/**
	 * The colour of the root must be black.
	 */
	private boolean verifyRootColour() {
		return ((RBNode<T>) root).getColour() == Colour.BLACK; // already
																// implemented
	}

	/**
	 * This is guaranteed by the constructor.
	 */
	private boolean verifyNILNodeColour() {
		return true; // already implemented
	}

	/**
	 * Verifies the property for all RED nodes: the children of a red node must
	 * be BLACK.
	 */
	private boolean verifyChildrenOfRedNodes() {
		return verifyChildrenOfRedNodes((RBNode<T>) this.root);
	}

	private boolean verifyChildrenOfRedNodes(RBNode<T> node) {
		if (!node.isEmpty()) {
			if (isRed(node)) {
				if (isBlack((RBNode<T>) node.getLeft()) && isBlack((RBNode<T>) node.getRight())) {
					return verifyChildrenOfRedNodes((RBNode<T>) node.getLeft())
							&& verifyChildrenOfRedNodes((RBNode<T>) node.getRight());
				} else {
					return false;
				}
			} else {
				return verifyChildrenOfRedNodes((RBNode<T>) node.getLeft())
						&& verifyChildrenOfRedNodes((RBNode<T>) node.getRight());
			}
		}
		return true;
	}

	private boolean isRed(RBNode<T> node) {
		return node.getColour().equals(Colour.RED);
	}

	private boolean isBlack(RBNode<T> btNode) {
		return btNode.getColour().equals(Colour.BLACK);
	}

	/**
	 * Verifies the black-height property from the root. The method blackHeight
	 * returns an exception if the black heights are different.
	 */
	private boolean verifyBlackHeight() {
		return verifyBlackHeight(this.root);
	}

	private boolean verifyBlackHeight(BTNode<T> node) {
		if (!node.isEmpty()) {
			if (blackHeight((RBNode<T>) node.getLeft()) == blackHeight((RBNode<T>) node.getRight())) {
				return verifyBlackHeight(node.getLeft()) && verifyBlackHeight(node.getRight());
			} else {
				return false;
			}
		}
		return true;
	}

	@Override
	public void insert(T value) {
		if (value != null)
			this.insert((RBNode<T>) this.root, value);
	}

	private void insert(RBNode<T> node, T value) {
		if (node.isEmpty()) {
			node.setData(value);
			RBNode<T> nilLeft = new RBNode<T>();
			nilLeft.setParent(node);
			node.setLeft(nilLeft);
			RBNode<T> nilRight = new RBNode<T>();
			nilRight.setParent(node);
			node.setRight(nilRight);
			node.setColour(Colour.RED);
			fixUpCase1(node);
		} else if (node.getData().compareTo(value) < 0) {
			this.insert((RBNode<T>) node.getRight(), value);
		} else if (node.getData().compareTo(value) > 0) {
			this.insert((RBNode<T>) node.getLeft(), value);
		}
	}

	@Override
	public RBNode<T>[] rbPreOrder() {
		@SuppressWarnings("unchecked")
		RBNode<T>[] array = new RBNode[size()];
		rbPreOrder(array, (RBNode<T>) this.root, 0);
		return array;
	}

	private int rbPreOrder(RBNode<T>[] array, RBNode<T> node, int i) {
		if (!node.isEmpty()) {
			array[i++] = node;
			i = rbPreOrder(array, (RBNode<T>) node.getLeft(), i);
			i = rbPreOrder(array, (RBNode<T>) node.getRight(), i);
		}
		return i;
	}

	// FIXUP methods
	protected void fixUpCase1(RBNode<T> node) {
		if (node != null) {
			if (isRoot(node)) {
				node.setColour(Colour.BLACK);
			} else {
				fixUpCase2(node);
			}
		}
	}

	protected void fixUpCase2(RBNode<T> node) {
		if (node.getParent() != null) {
			if (isRed((RBNode<T>) node.getParent())) {
				fixUpCase3(node);
			}
		}
	}

	protected void fixUpCase3(RBNode<T> node) {
		RBNode<T> uncle = getUncle(node);
		if (uncle != null) {
			if (isRed(uncle)) {
				uncle.setColour(Colour.BLACK);
				((RBNode<T>) node.getParent()).setColour(Colour.BLACK);
				RBNode<T> granFather = getGranFather(node);
				granFather.setColour(Colour.RED);
				fixUpCase1(granFather);
			} else {
				fixUpCase4(node);
			}
		}
	}

	protected void fixUpCase4(RBNode<T> node) {
		if (isRightChildren(node) && isLeftChildren((BSTNode<T>) node.getParent())) {
			leftRotation((RBNode<T>) node.getParent());
			fixUpCase5((RBNode<T>) node.getLeft());
		} else if (isLeftChildren(node) && isRightChildren((BSTNode<T>) node.getParent())) {
			rightRotation((RBNode<T>) node.getParent());
			fixUpCase5((RBNode<T>) node.getRight());
		} else {
			fixUpCase5(node);
		}
	}

	protected void fixUpCase5(RBNode<T> node) {
		((RBNode<T>) node.getParent()).setColour(Colour.BLACK);
		RBNode<T> granFather = getGranFather(node);
		granFather.setColour(Colour.RED);
		if (isLeftChildren(node)) {
			rightRotation(granFather);
		} else {
			leftRotation(granFather);

		}

	}

	// AUXILIARY
	protected void leftRotation(RBNode<T> node) {
		if (node != null && !node.isEmpty()) {

			BSTNode<T> newRoot = Util.leftRotation(node);

			if (node.equals(this.root)) {
				this.root = newRoot;
			}

		}
	}

	// AUXILIARY
	protected void rightRotation(RBNode<T> node) {
		if (node != null && !node.isEmpty()) {

			BSTNode<T> newRoot = Util.rightRotation(node);

			if (node.equals(this.root)) {
				this.root = newRoot;
			}
		}
	}

	private RBNode<T> getGranFather(BSTNode<T> node) {
		BSTNode<T> parent = (BSTNode<T>) node.getParent();
		if (parent != null) {
			return (RBNode<T>) node.getParent().getParent();
		} else {
			return null;
		}
	}

	private boolean isLeftChildren(BSTNode<T> node) {
		BSTNode<T> parent = (BSTNode<T>) node.getParent();
		if (parent != null) {
			if (node.equals(parent.getLeft())) {
				return true;
			}
		}
		return false;
	}

	private boolean isRightChildren(BSTNode<T> node) {
		BSTNode<T> parent = (BSTNode<T>) node.getParent();
		if (parent != null) {
			if (node.equals(parent.getRight())) {
				return true;
			}
		}
		return false;
	}

	private RBNode<T> getUncle(BTNode<T> node) {
		BSTNode<T> granFather = getGranFather((BSTNode<T>) node);
		if (granFather != null) {
			if (isLeftChildren((BSTNode<T>) node.getParent())) {
				return (RBNode<T>) granFather.getRight();
			} else {
				return (RBNode<T>) granFather.getLeft();
			}
		}
		return null;
	}
}
