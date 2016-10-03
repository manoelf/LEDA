package adt.avltree;

import adt.bst.BSTNode;
import adt.bst.BSTVerifierImpl;

/**
 * @author Claudio Campelo
 *
 * @param <T>
 */
public class AVLTreeVerifierImpl<T extends Comparable<T>> extends BSTVerifierImpl<T> implements AVLTreeVerifier<T> {

	private AVLTreeImpl<T> avlTree;

	public AVLTreeVerifierImpl(AVLTree<T> avlTree) {
		super(avlTree);
		this.avlTree = (AVLTreeImpl<T>) avlTree;
	}

	private AVLTreeImpl<T> getAVLTree() {
		return avlTree;
	}

	@Override
	public boolean isAVLTree() {
		
		return isBST() &&
				isAVLTree(avlTree.getRoot());
	}

	private boolean isAVLTree(BSTNode<T> node) {
		if (!node.isEmpty()) {
			int balance = avlTree.calculateBalance(node);
			if (balance >= -1 && balance <= 1) {
				return isAVLTree((BSTNode<T>) node.getLeft()) && isAVLTree((BSTNode<T>) node.getRight());
			} else {
				return false;
			}
		}
		
		return true;
	}

}
