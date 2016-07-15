import java.util.Arrays;

public class MergeSort {

	public static void main(String[] args) {
		int[] A = new int[] {5, 4, 3, 2, 1, 0};
		mergeSort(A, 0, A.length - 1);
		System.out.println(Arrays.toString(A));
	}
	
	public static void mergeSort(int[] A, int last, int right) {
		
		if (last < right) {
			int meio = (right + last)/2;
			mergeSort(A, last, meio);
			mergeSort(A, meio + 1, right);
			merge(A, last, meio, right);
			
		}
	}

	private static void merge(int[] A, int last, int meio, int right) {
		int[] aux = new int[A.length];
		for (int w = 0; w < aux.length; w++) {
			aux[w] = A[w];
		}
		
		int i = last;
		int j = meio + 1;
		int k = last;
		
		while (i <= meio && j <= right) {
			if (aux[i] < aux[j]) {
				A[k] = aux[i];
				i++;
			} else {
				A[k] = aux[j];
				j++;
			}
			k++;
		}
		
		while (i <= meio) {
			A[k] = aux[i];
			k++;
			i++;
		}
		
		while (j <= right) {
			A[k] = aux[j];
			k++;
			j++;
		}
	}
}
