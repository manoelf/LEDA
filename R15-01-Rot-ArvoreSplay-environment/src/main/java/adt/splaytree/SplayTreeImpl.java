package adt.splaytree;

import javax.naming.NameAlreadyBoundException;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.BTNode;
import adt.bt.Util;

public class SplayTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements SplayTree<T> {

	private void splay(BSTNode<T> node) {
		if (node != null && node.getParent() != null) {
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
					Util.leftRotation((BSTNode<T>) parent.getParent());
				} else {
					Util.leftRotation(parent);
					Util.rightRotation((BSTNode<T>) parent.getParent());
				}

			}

		}

	}

	private boolean isLeftChildren(BTNode<T> btNode) {
		BSTNode<T> parent = (BSTNode<T>) btNode.getParent();
		return parent.getLeft().equals(btNode);
	}

	private boolean isRightChildren(BSTNode<T> node) {
		BSTNode<T> parent = (BSTNode<T>) node.getParent();
		return parent.getRight().equals(node);
	}

	@Override
	public BSTNode<T> search(T element) {
		BSTNode<T> node = super.search(element);
		if (node.isEmpty() && node.getParent() != null) {
			node.setData(element);
			BSTNode<T> newNode = super.predecessor(element);
			if (newNode == null) {
				newNode = super.sucessor(element);
			}
			node.setData(null);
			splay(newNode);
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
}
