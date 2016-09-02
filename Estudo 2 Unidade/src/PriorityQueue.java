import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PriorityQueue {
	
	private List<Integer> pilha1;
	private List<Integer> pilha2;
	
	public PriorityQueue() {
		this.pilha1 = new LinkedList<>();
	}
	
	//add(e)/offer(e), remove()/poll() e element()/peek().
	
	public void add(Integer element) {
		pilha1.add(element);
		ArrayList<Integer> lista = new ArrayList<>(10000);
	}
	
	public void remove() {
		
	}
}
