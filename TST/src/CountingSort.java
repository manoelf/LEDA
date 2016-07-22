import java.util.Arrays;

public class CountingSort {
	
	public static void main(String[] args) {
		int[] A = new int[] {9, 3, 3, 1, 8, 7, 6, 6, 5};
		
		counting(A, 9);
		
		System.out.println(Arrays.toString(A));
		
	}
	
	
	public static void counting(int[] A, int maior) {
		int[] C = new int[maior];
		
		//Frequencia
		for (int i = 0; i < A.length; i++) {
			C[A[i]-1]++;
		}
		
		
		//Acomulativa
		for (int i = 1; i < C.length; i++) {
			C[i] += C[i-1];
		}
		
		int[] B = new int[A.length];
		
		//Ordenacao
		for (int i = A.length - 1; i >= 0 ; i--) {
			B[C[A[i] - 1] - 1] = A[i];
			C[A[i] - 1]--;
		}
		
		for (int i = 0; i < B.length; i++) { {
			A[i] = B[i];
		}
			
		}
	}

}
