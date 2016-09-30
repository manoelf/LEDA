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
			this.root.forward[0] = this.NIL;
		}
	}

	private void updateHeight(int newHeight) {
		for (int i = this.height; i < newHeight; i++) {
			this.root.forward[i] = this.NIL;
		}
		this.height = newHeight;
	}
	
	private void recconectRootToNil() {
		for (int i = 0; i < this.height; i++) {
			if (root.forward[i] == null)
				this.root.forward[i] = this.NIL;
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
		
		if (USE_MAX_HEIGHT_AS_HEIGHT) {
			this.height = maxHeight;
			recconectRootToNil();
		} else {
			updateHeight(height);
		}

		if (height >= 1 && height <= maxHeight && newValue != null) {

			@SuppressWarnings("unchecked")
			// Array com as referencias para serem atualizadas
			SkipListNode<T>[] updates = new SkipListNode[this.height];

			// skip auxiliar para poder andar na estrutura
			SkipListNode<T> aux = this.root;

			// Percorrer a estrutura ate encontrar o local aser inserido
			for (int i = this.height - 1; i >= 0; i--) {
				while (aux.forward[i] != null  && aux.forward[i].getKey() < key) {
					aux = aux.getForward(i);
				}
				updates[i] = aux;
			}

			// Referencia do skipNode que ficara a frente do node inserido
			aux = aux.forward[0];

			// Caso as chaves sejam iguais, sera mudado apenas o valor
			if (aux.getKey() == key) {
				aux.setValue(newValue);
			} else {
				// Skipnode a ser inserido
				SkipListNode<T> newSkip = new SkipListNode<T>(key, height, newValue);

				// Caso a altura do no a ser inserido for menor do que seu
				// proximo
				if (height < aux.getHeight()) {

					for (int i = 0; i < height; i++) {
						updates[i].forward[i] = newSkip;
						newSkip.forward[i] = aux;
					}

					// Caso contrario, teremos que, em algum momento, pegar o
					// proximo node para que as refrencias do no que esta sendo
					// inserido sejam atualizada
				} else {

					for (int i = 0; i < height; i++) {
						// Quando a altura do proximo eh menor, pego o do
						// proximo dele para atualizar todos os forwrd do no
						// inserido
						if (i >= aux.getHeight()) {
							aux = aux.forward[0];
						}
						updates[i].forward[i] = newSkip;
						newSkip.forward[i] = aux;
					}
				}
			}
		}
	}

	@Override
	public void remove(int key) {
		SkipListNode<T> aux = root;
		@SuppressWarnings("unchecked")
		SkipListNode<T>[] updates = new SkipListNode[this.height];

		for (int i = this.height -1; i >= 0; i--) {
			while (aux != null && aux.forward[i].getKey() < key) {
				aux = aux.forward[i];
			}
			updates[i] = aux;
		}

		aux = aux.forward[0];

		if (aux.getKey() == key) {
			for (int i = 0; i < height; i++) {
				if (updates[i].forward[i] != aux) {
					break;
				}
				updates[i].forward[i] = aux.forward[i];
			}
		}
	}

	@Override
	public int height() {
		SkipListNode<T> aux = this.root;
		int i = 0;
		for (i = 0; i < this.maxHeight; i++) {
			if (aux.forward[i] == null || aux.forward[i].getKey() == Integer.MAX_VALUE ) {
				return i;
			}
		}
		return i;
	}

	@Override
	public SkipListNode<T> search(int key) {
		SkipListNode<T> aux = this.root;

		for (int i = this.height - 1; i >= 0; i--) {
			while (aux.forward[i] != null && aux.forward[i].getKey() < key) {
				aux = aux.forward[i];
			}
		}
		aux = aux.forward[0];

		if (aux.getKey() == key) {
			return aux;
		} else {
			return null;
		}
	}

	@Override
	public int size() {
		SkipListNode<T> aux = this.root.forward[0];
		int size = 0;
		
		while (aux != null && aux.getKey() != Integer.MAX_VALUE) {
			size++;
			aux = aux.forward[0];
		}
		
		return size;
	}

	@Override
	public SkipListNode<T>[] toArray() {
		SkipListNode<T> aux = this.root;
		@SuppressWarnings("unchecked")
		SkipListNode<T>[] array = new SkipListNode[size()+2];
		
		int i = 0;

		do {
			array[i++] = aux;
			aux  = aux.forward[0];
			
		} while (aux != null);
			
		return array;
	}

}
