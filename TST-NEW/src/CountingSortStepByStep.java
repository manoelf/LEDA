import java.awt.FontFormatException;
import java.util.Arrays;
import java.util.Scanner;

class CountingSortStepByStep {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		String[] entrada = (sc.nextLine()).split(" ");
		int maior = sc.nextInt();
		sc.nextLine();
		
		countinSort(entrada, maior);
		
	}
	
	
	
	
	
	
	
	public static void countinSort(String[] A, int maior) {
		int[] frequencia = new int[maior+1];
		int num;
		//Frequencia 
		for (int i = 0; i < A.length; i++) {
			num = Integer.parseInt(A[i]);
			frequencia[num]++;
			System.out.println(formatador(frequencia));
		}
		
		//Acomulativa
		for (int i = 1; i < frequencia.length; i++) {
			frequencia[i] += frequencia[i - 1];
		}
		System.out.println("Cumulativa do vetor de contagem - " + formatador(frequencia));
		
		//Sorting
		int[] ordenado = new int[A.length];
		
		for (int i = 0; i < ordenado.length; i++) {
			num = Integer.parseInt(A[i]);
			ordenado[frequencia[num]-1] = num;
			frequencia[num]--;
		}
		System.out.println(formatador(frequencia));
		System.out.println(formatador(ordenado));

	}
	
	
	public static String formatador(int[] A) {
		String saida = "";
		for (int i = 0; i < A.length; i++) {
			saida += A[i];
			if (i < A.length - 1) {
				saida += " ";
			}
		}
		
		return saida;
	}
	
	

}
