package adt.avltree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;

public class AVLTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements AVLTree<T> {

	// TODO Do not forget: you must override the methods insert and remove
	// conveniently.
	
	@Override
	public void insert(T element) {
		if (element != null) {
			insert(this.root, element);
		}
	
	}

	private void insert(BSTNode<T> node, T element) {
		if (node.isEmpty()) {
			BSTNode<T> nilLeft = new BSTNode<>();
			nilLeft.setParent(node);
			BSTNode<T> nilRight = new BSTNode<>();
			nilRight.setParent(node);
			node.setData(element);
			node.setLeft(nilLeft);
			node.setRight(nilRight);
			rebalanceUp(node);
		} else if (element.compareTo(node.getData()) < 0) {
			insert((BSTNode<T>) node.getLeft(), element);
		} else if (element.compareTo(node.getData()) > 0){
			insert((BSTNode<T>) node.getRight(), element);
		}
	}
	
	
	//======================================================================
	@Override
	public void remove(T element) {
		BSTNode<T> node =  search(element);
		if (!node.isEmpty()) {
			remove(node);
		}
	}
	
	public void remove(BSTNode<T> node) {
		if (!node.isEmpty()) {
			if (isLeaf(node)) {
				removeLeaf(node);
			} else if (singleChildLeft(node)) {
				removeNodeSingleChildLleft(node);
			} else if (singleChildRight(node)) {
				removeNodeSingleChildRight(node);
			} else {
				removeNodeTwoChild(node);
			}
		}
	}
	
	private boolean isLeaf(BSTNode<T> node) {
		return (node.getLeft().isEmpty() && node.getRight().isEmpty());
	}
	
	
	private boolean singleChildLeft(BSTNode<T> node) {
		return !node.getLeft().isEmpty() && node.getRight().isEmpty();
	}
	
	private boolean singleChildRight(BSTNode<T> node) {
		return node.getLeft().isEmpty() && !node.getRight().isEmpty();
	}
	
	private boolean isRoot(BSTNode<T> node) {
		return node.equals(this.root);
	}
	
	private void removeLeaf(BSTNode<T> node) {
		node.setData(null);
		node.setLeft(null);
		node.setRight(null);
		rebalanceUp((BSTNode<T>) node.getParent());
	}
	
	private void removeNodeSingleChildLleft(BSTNode<T> node) {
		if (isRoot(node)) {
			this.root = (BSTNode<T>) node.getLeft();
			this.root.setParent(node.getParent());
			rebalance(this.root);
		} else {
			
			if (node.getParent().getRight().equals(node)) {
				node.getLeft().setParent(node.getParent());
				node.getParent().setRight(node.getLeft());
			} else {
				node.getLeft().setParent(node.getParent());
				node.getParent().setLeft(node.getLeft());
			}
			rebalanceUp(node);
		}
	}
	
	private void removeNodeSingleChildRight(BSTNode<T> node) {
		if (isRoot(node)) {
			this.root = (BSTNode<T>) node.getRight();
			this.root.setParent(node.getParent());
			rebalanceUp(this.root);
		} else {
			
			if (node.getParent().getLeft().equals(node)){
				node.getRight().setParent(node.getParent());
				node.getParent().setLeft(node.getRight());
			} else {
				node.getRight().setParent(node.getParent());
				node.getParent().setRight(node.getRight());
			}
			rebalanceUp(node);
		}
	}
	
	
	private void removeNodeTwoChild(BSTNode<T> node) {
		BSTNode<T> sucessor = sucessor(node.getData());
		T data = node.getData();
		node.setData(sucessor.getData());
		sucessor.setData(data);
		remove(sucessor);
	}

	
	//======================================================================

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
		
			if (newRoot.getLeft().equals(this.root)) {
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
			
			if (newRoot.getRight().equals(node)) {
				this.root = newRoot;
			}
		}
	}

}
