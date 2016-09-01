package adt.avltree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;

public class AVLTreeImpl<T extends Comparable<T>> 
    extends BSTImpl<T> implements AVLTree<T> {

	//TODO Do not forget: you must override the methods insert and remove conveniently.

	//AUXILIARY
	protected int calculateBalance(BSTNode<T> node){
		if (node == null) return 0;
		int leftHeight = height((BSTNode<T>) node.getLeft());
		int rightHeight = height((BSTNode<T>) node.getRight());
		return Math.abs(leftHeight - rightHeight);
	}
	
	//AUXILIARY
	protected void rebalance(BSTNode<T> node){
		// TODO Auto-generated method stub
		throw new RuntimeException("Not implemented yet!");
	}
	
	//AUXILIARY
	protected void rebalanceUp(BSTNode<T> node){
		// TODO Auto-generated method stub
		throw new RuntimeException("Not implemented yet!");
	}
	
	//AUXILIARY
	protected void leftRotation(BSTNode<T> node){
		// TODO Auto-generated method stub
		throw new RuntimeException("Not implemented yet!");
	}
	
	//AUXILIARY
	protected void rightRotation(BSTNode<T> node){
		// TODO Auto-generated method stub
		throw new RuntimeException("Not implemented yet!");
	}
	
}
