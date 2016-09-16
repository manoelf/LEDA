package adt.splaytree;

import javax.naming.NameAlreadyBoundException;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.BTNode;
import adt.bt.Util;

public class SplayTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements SplayTree<T> {

	private void splay(BSTNode<T> node) {
		if (node != null && node.getParent() != null && !isRoot(node)) {
			BSTNode<T> parent = (BSTNode<T>) node.getParent();
			// ZIG
			// Caso 1 (terminal): se p(x) é raiz faz apenas uma rotacao
			if (isRoot(node.getParent())) {
				if (isLeftChildren(node)) {
					Util.rightRotation(parent);
				} else {
					Util.leftRotation(parent);
				}
			}
			// ZIG_ZIG
			// Caso 2: se pai(x) não é raiz e x e pai(x) sao filhos do
			// mesmo lado, faz duas rotacoes no mesmo
			// sentido começando pelo avô (pai(pai(x)))
			else if (isLeftChildren(node) && isLeftChildren(parent)) {
				Util.rightRotation((BSTNode<T>) parent.getParent());
				Util.rightRotation(parent);
			} else if (isRightChildren(node) || isRightChildren(parent)) {
				Util.leftRotation((BSTNode<T>) parent.getParent());
				Util.leftRotation(parent);

				// ZIG_ZAG
				// Caso 3: se pai(x) não é raiz e x e pai(x) sao filhos do
				// lado oposto, faz uma rotacao em p(x) e outra
				// rotacao de sentido oposto no avô.
			} else {
				if (isLeftChildren(node)) {
					Util.rightRotation(parent);
					Util.leftRotation(parent);
				} else {
					Util.leftRotation(parent);
					Util.rightRotation( parent);
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

	@Override
	public BSTNode<T> search(T element) {
		BSTNode<T> node = super.search(element);
		if (node.isEmpty() && node.getParent() != null) {
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
			BSTNode<T> node = search(element);
			splay(node);
		}
		
	}
	
	@Override
	public void remove(T element) {
		BSTNode<T> node = super.search(element);
		if (node != null && node.isEmpty()) {
			splay((BSTNode<T>) node.getParent());
		} else {
			splay(node);
		}
	}
}
