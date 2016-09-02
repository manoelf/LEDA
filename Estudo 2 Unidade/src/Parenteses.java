import java.util.LinkedList;
import java.util.Queue;

public class Parenteses {
	
	
	
	
	public boolean validaParentes(String parenteses) { 
		String[] entrada = parenteses.split("");
		Queue<String> pilha = new LinkedList<>();
		
		if (entrada[0].equals(")")) {
			return false;
		} else {
			for (int i = 0; i < entrada.length; i++) {
				if (entrada[i].equals(")")) {
					if (pilha.isEmpty()) {
						return false;
					} else {
						pilha.remove();
					}
				} else {
					pilha.add(entrada[i]);
				}
			}
			return pilha.isEmpty();
		}
	}
	
	
	
	public static void main(String[] args) {
		Parenteses parenteses = new Parenteses();
		
		assert parenteses.validaParentes("((()))") == true;
		assert parenteses.validaParentes("((())))") == false;
		assert parenteses.validaParentes(")") == false;
		assert parenteses.validaParentes("(()") == false;
		assert parenteses.validaParentes("()") == true;
		assert parenteses.validaParentes("(((") == false;
		assert parenteses.validaParentes("()(") == false;


		
		
		
		
		
		
		
		
	}
}
