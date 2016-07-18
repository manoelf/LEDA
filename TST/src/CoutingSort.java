import java.util.Arrays;

/**
 * @author manoel
 *
 */
public class CoutingSort {
	
	public static void main(String[] args) {
		Integer[] A = new Integer[] {5, 1, 2, 5, 9, 1, 7};
		coutingSort(A);
		System.out.println(Arrays.toString(A));
	}
	
	
	public static void coutingSort(Integer[] A) {
		Integer[] C = new Integer[max(A)+1];
		inicializaArray(C);
		
		//Frequencia
		for (int i = 0; i < A.length; i++) {
			C[A[i]]++;
		}
		System.out.println(Arrays.toString(C));

		//Acomulativa
		for (int i = 1; i < C.length; i++) {
			C[i] += C[i - 1];
		}
		System.out.println(Arrays.toString(C));

		Integer[] B = new Integer[A.length];
		//Sort
		inicializaArray(B);
		for (int i = A.length - 1; i >= 0; i--) {
			B[C[A[i]]-1] = A[i];
			C[A[i]]--;
		}
		
		for (int i = 0; i < B.length; i++) {
			A[i] = B[i];
		}
	}
	
	
	public static Integer max(Integer[] A) {
		Integer max = A[0];
		for (int i = 0; i < A.length; i++) {
			if (A[i] > max) {
				max = A[i];
			}
		}
		return max;
	}
	
	public static void inicializaArray(Integer[] A) {
		for (int i = 0; i < A.length; i++) {
			A[i] = 0;
		}
	}
}
