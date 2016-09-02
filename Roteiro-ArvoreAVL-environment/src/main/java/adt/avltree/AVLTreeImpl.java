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
	public void leftRotation(BSTNode<T> node){
		//FILHO DA DIREITA VIRA NOVA RAIZ
		//SE O FILHO DA DIREITA JA TEM UM FILHO DA ESQUERDA
			//O FILHO DA ESQUERDA DO FILHO DA DIREITA VIRA FILHO
			//DA DIREITA DO FILHO DA ESQUERDA 
		//RAIZ ORIGINAL VIRA FILHO DA ESQUERDA DA NOVA RAIZ
	
	}
	
	//AUXILIARY
	protected void rightRotation(BSTNode<T> node){
		BSTNode<T> pivot = (BSTNode<T>) node.getLeft();
		node.setLeft(pivot.getRight());
		pivot.getRight().setParent(node);//Parent
		pivot.setRight(node);
		pivot.getRight().setParent(pivot);//Parent
		node = pivot;
		
		// TODO Auto-generated method stub
		throw new RuntimeException("Not implemented yet!");
	}
	
}
