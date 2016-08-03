package implementacao;

public class Node {
	
	private Node next;
	private int value;
	
	public Node(int value) {
		this.value = value;
		this.next = null;
	}
	
	
	public void setNext(Node node) {
		this.next = node;
	}
	
	public void setvalue(int value) {
		this.value = value;
	}
	
	public Node getNext() {
		return this.next;
	}
	
	public int getValue() {
		return this.value;
	}
}
