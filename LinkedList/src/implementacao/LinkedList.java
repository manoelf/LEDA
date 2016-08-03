package implementacao;

public class LinkedList {

	private Node head;
	private int size;

	public LinkedList() {
		this.head = null;
		this.size = -1;
	}

	public void insert(int value) {
		if (isEmpty()) {
			this.head = new Node(value);
			this.size = 1;
		} else {
			Node aux = head;
			Node newNode = new Node(value);

			while (aux.getNext() != null) {
				aux = aux.getNext();
			}

			aux.setNext(newNode);
			this.size++;
		}

	}

	public void remove(int value) {
		if (!isEmpty()) {
			Node aux = head;

			while (aux.getNext() != null && aux.getValue() != value) {
				aux = aux.getNext();
			}
			if (aux.getNext() != null) {
				if (aux.getNext().getValue() == value) {
					Node next = aux.getNext().getNext();
					aux.setNext(next);
					this.size--;
				}
			} else if (aux.getValue() == value){
				this.head = null;
				this.size--;
			}
		}

	}

	public int search(int value) {
		return -1;
	}

	public int size() {
		return this.size;
	}

	public boolean isEmpty() {
		return (this.head == null);
	}

}
