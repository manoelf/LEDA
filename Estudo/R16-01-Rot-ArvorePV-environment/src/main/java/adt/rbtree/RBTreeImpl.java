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
		return blackHeight(this.root);
	}

	private int blackHeight(BTNode<T> node) {
		if (!node.isEmpty()) {
			if (isBlack(node)) {
				return 1 + blackHeight(node.getLeft()) + blackHeight(node.getRight());
			} else {
				return blackHeight(node.getLeft()) + blackHeight(node.getRight());
			}
		}
		return 0;
	}

	protected boolean verifyProperties() {
		boolean resp = verifyNodesColour()
				&& verifyNILNodeColour()
				&& verifyRootColour()
				&& verifyChildrenOfRedNodes()
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
		if (isEmpty()) {
			return true;
		}
		return verifyChildrenOfRedNodes(this.root);
	}

	private boolean verifyChildrenOfRedNodes(BTNode<T> node) {
		if (node.isEmpty()) {
			return true;
		} else if (isRed((RBNode<T>) node)) {
			if (isBlack(node.getLeft()) && isBlack(node.getRight())) {
				return verifyChildrenOfRedNodes(node.getLeft()) && verifyChildrenOfRedNodes(node.getRight());
			} else {
				return false;
			}
		} else {
			return verifyChildrenOfRedNodes(node.getLeft()) && verifyChildrenOfRedNodes(node.getRight());
		}
	}

	private boolean isBlack(BTNode<T> node) {
		return ((RBNode<T>) node).getColour().equals(Colour.BLACK);
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
			int leftHight = blackHeight(node.getLeft());
			int rightHight = blackHeight(node.getRight());
			
			if (leftHight == rightHight) {
				return verifyBlackHeight(node.getLeft()) && verifyBlackHeight(node.getRight());
			} else {
				return false;
			}
		}
		
		return true;
	}

	@Override
	public void insert(T value) {
		if (value != null) {
			this.insert((RBNode<T>)this.root, value);
		}
	}
	
	private void insert(RBNode<T> node, T value) {
		if (node.isEmpty()) {
			
			node.setData(value);
			
			RBNode<T> leftNode = new RBNode<T>();
			leftNode.setParent(node);
			node.setLeft(leftNode);
			
			RBNode<T> rightNode = new RBNode<T>();
			rightNode.setParent(node);
			node.setRight(rightNode);
			
			node.setColour(Colour.RED);
			fixUpCase1(node);
			
		} else if (node.getData().compareTo(value) < 0) {
			insert((RBNode<T>) node.getRight(), value);
		} else if (node.getData().compareTo(value) > 0) {
			insert((RBNode<T>) node.getLeft(), value);
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
			array[i] = node;
			i = rbPreOrder(array, (RBNode<T>) node.getLeft(), i++);
			i = rbPreOrder(array, (RBNode<T>) node.getRight(), i++);
		}
		return i;
	}

	// Invariantes
	// BST
	// Root preto
	// Filhos de vermelhos sao pretos
	// Folhas pretas
	// Mesma altura preta

	// FIXUP methods
	protected void fixUpCase1(RBNode<T> node) {
		if (isRoot(node)) {
			node.setColour(Colour.BLACK);
		} else {
			fixUpCase2(node);
		}
	}

	private boolean isRed(RBNode<T> node) {
		return node.getColour().equals(Colour.RED);
	}

	protected void fixUpCase2(RBNode<T> node) {
		if (isRed((RBNode<T>) node.getParent())) {
			fixUpCase3(node);
		}
	}

	protected void fixUpCase3(RBNode<T> node) {
		RBNode<T> granfather = (RBNode<T>) node.getParent().getParent();
		if (granfather != null) {
			if (isLeftChild((RBNode<T>) node.getParent())) {
				if (isRed((RBNode<T>) granfather.getRight())) {
					((RBNode<T>) granfather.getRight()).setColour(Colour.BLACK);
					((RBNode<T>) node.getParent()).setColour(Colour.BLACK);
					granfather.setColour(Colour.RED);

					fixUpCase1(granfather);
				} else {
					fixUpCase4(node);
				}

			} else {
				if (isRed((RBNode<T>) granfather.getLeft())) {
					((RBNode<T>) granfather.getRight()).setColour(Colour.BLACK);
					((RBNode<T>) granfather.getLeft()).setColour(Colour.BLACK);
					granfather.setColour(Colour.RED);

					fixUpCase1(granfather);
				} else {
					fixUpCase4(node);
				}

			}
		}

	}

	private boolean isLeftChild(RBNode<T> node) {
		if (node.getParent() == null) {
			return false;
		} else if (node.getParent().getLeft().equals(node)) {
			return true;
		} else {
			return false;
		}

	}

	private boolean isRightChild(RBNode<T> node) {
		if (node.getParent() == null) {
			return false;
		} else if (node.getParent().getRight().equals(node)) {
			return true;
		} else {
			return false;
		}

	}

	protected void fixUpCase4(RBNode<T> node) {
		RBNode<T> next = node;
		if (isLeftChild(node) && isRightChild((RBNode<T>) node.getParent())) {
			rightRotation((RBNode<T>) node.getParent());
			next = (RBNode<T>) node.getRight();
		} else if (isRightChild(node) && isLeftChild((RBNode<T>) node.getParent())) {
			leftRotation((RBNode<T>) node.getParent());
			next = (RBNode<T>) node.getLeft();
		}
		fixUpCase5(next);
	}

	protected void fixUpCase5(RBNode<T> node) {
		RBNode<T> granfather = (RBNode<T>) node.getParent().getParent();
		granfather.setColour(Colour.RED);
		((RBNode<T>) node.getParent()).setColour(Colour.BLACK);

		if (isRightChild(node)) {
			leftRotation(granfather);
		} else {
			rightRotation(granfather);
		}

	}
	
	private void leftRotation(RBNode<T> node) {
		RBNode<T> newNode = (RBNode<T>) Util.leftRotation(node);
		
		if (isRoot(node)) {
			this.root = newNode;
		}
	}
	
	private void rightRotation(RBNode<T> node) {
		RBNode<T> newNode = (RBNode<T>)Util.rightRotation(node);
		
		if (isRoot(newNode)) {
			this.root = newNode;
		}
		
	}

}
 