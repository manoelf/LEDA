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

	private int blackHeight(BSTNode<T> node) {
		if (!isEmpty()) {
			return 1 + blackHeight((BSTNode<T>) node.getLeft());
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
		return verifyBlackHeight(this.root.getLeft()) == verifyBlackHeight(this.root.getRight());
	}

	private int verifyBlackHeight(BTNode<T> node) {
		if (!node.isEmpty()) {
			if (isBlack((RBNode<T>) node)) {
				return 1 + verifyBlackHeight(node.getLeft()) + verifyBlackHeight(node.getRight());
			} else {
				return verifyBlackHeight(node.getLeft()) + verifyBlackHeight(node.getRight());
			}
		}
		return 0;
	}

	@Override
	public void insert(T value) {
	}
	
	private void insert(RBNode<T> node, T value) {
		//TODO
	}

	@Override
	public RBNode<T>[] rbPreOrder() {
		// TODO Implement your code here
		throw new UnsupportedOperationException("Not implemented yet!");
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
				((RBNode<T>)node.getParent()).setColour(Colour.BLACK);
				RBNode<T> granFa = getGranFather(node);
				granFa.setColour(Colour.RED);
				fixUpCase1(granFa);
			} else {
				fixUpCase4(node);
			}
		}
	}

	protected void fixUpCase4(RBNode<T> node) {
		if (isRightChildren(node) && isLeftChildren((BSTNode<T>) node.getParent())) {
			Util.leftRotation((BSTNode<T>) node.getParent());
			fixUpCase5((RBNode<T>) node.getRight());
		} else if (isLeftChildren(node) && isRightChildren((BSTNode<T>) node.getParent())) {
			Util.rightRotation((BSTNode<T>) node.getParent());
			fixUpCase5((RBNode<T>) node.getLeft());
		}
	}

	protected void fixUpCase5(RBNode<T> node) {
		((RBNode<T>)node.getParent()).setColour(Colour.BLACK);
		RBNode<T> granFa = getGranFather(node);
		granFa.setColour(Colour.RED);
		if (isLeftChildren(node)) {
			Util.rightRotation(granFa);
		} else {
			Util.leftRotation(granFa);
			
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
