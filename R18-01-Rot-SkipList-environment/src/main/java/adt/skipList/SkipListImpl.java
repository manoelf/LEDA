package adt.skipList;

public class SkipListImpl<T> implements SkipList<T> {

	protected SkipListNode<T> root;
	protected SkipListNode<T> NIL;

	protected int height;
	protected int maxHeight;

	protected boolean USE_MAX_HEIGHT_AS_HEIGHT;
	protected double PROBABILITY = 0.5;

	public SkipListImpl(int maxHeight) {
		if (USE_MAX_HEIGHT_AS_HEIGHT) {
			this.height = maxHeight;
		} else {
			this.height = 1;
		}
		this.maxHeight = maxHeight;
		root = new SkipListNode(Integer.MIN_VALUE, maxHeight, null);
		NIL = new SkipListNode(Integer.MAX_VALUE, maxHeight, null);
		connectRootToNil();
	}

	/**
	 * Faz a ligacao inicial entre os apontadores forward do ROOT e o NIL Caso
	 * esteja-se usando o level do ROOT igual ao maxLevel esse metodo deve
	 * conectar todos os forward. Senao o ROOT eh inicializado com level=1 e o
	 * metodo deve conectar apenas o forward[0].
	 */
	private void connectRootToNil() {
		if (USE_MAX_HEIGHT_AS_HEIGHT) {
			for (int i = 0; i < maxHeight; i++) {
				this.root.forward[i] = this.NIL.forward[i];
			}
		} else {
			this.root.forward[0] = this.NIL.forward[0];
		}
	}

	/**
	 * Metodo que gera uma altura aleatoria para ser atribuida a um novo no no
	 * metodo insert(int,V)
	 */
	private int randomLevel() {
		int randomLevel = 1;
		double random = Math.random();
		while (Math.random() <= PROBABILITY && randomLevel < maxHeight) {
			randomLevel = randomLevel + 1;
		}
		return randomLevel;
	}

	@Override
	public void insert(int key, T newValue, int height) {

		if (height <= maxHeight && newValue != null) {
			@SuppressWarnings("unchecked")
			SkipListNode<T>[] updates = new SkipListNode[maxHeight];
			SkipListNode<T> aux = this.root.getForward(maxHeight);

			for (int i = maxHeight; i >= 0; i--) {
				while (aux.getKey() < key) {
					aux = aux.getForward(i);
				}
				updates[i] = aux;
			}
			
			SkipListNode<T> newSkip = new SkipListNode<T>(key, height, newValue);
			for (int i = 0; i < height; i++) {
				newSkip.getForward()[i] = updates[i];
				updates[i].forward[i] = newSkip;
			}
		}
	}

	@Override
	public void remove(int key) {
		SkipListNode<T> aux = root.getForward(this.height);
		
		for (int i = this.height; i <= 0; i--) {
		}
	}

	@Override
	public int height() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	@Override
	public SkipListNode<T> search(int key) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	@Override
	public SkipListNode<T>[] toArray() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

}
