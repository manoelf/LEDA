import java.util.Arrays;

public class QuickSort {
	
	public static void main(String[] args) {
		
		int[] A =  new int[] {5, 4, 3, 2, 1, 0};
		
		System.out.println(Arrays.toString(A));
		quickSort(A, 0, A.length -1);
		System.out.println(Arrays.toString(A));
		
	}
	
//     p = 5	
//               i    
//	   2 1 3 0 5 8
//             j
	
	public static int partition(int[] A, int left, int right) {

		int pivot = A[left];
		int i = left + 1;
		int j = right;
		
		while (i <= j) {
			if (A[i] < pivot) {
				i++;
			} else if (A[j] > pivot) {
				j--;
			} else {
				swap(A, i, j);
			}
		}
		swap(A, left, j);
		
		return j;
	}
	
	
	public static void quickSort(int[] A, int left, int right) {
		
		if (left < right) {
			int pivot = partition(A, left, right);
			quickSort(A, left, pivot -1);
			quickSort(A, pivot + 1, right);
		}
	}
	
	
	public static void swap(int[] A, int i, int j) {
		int temp = A[i];
		A[i] = A[j];
		A[j] = temp;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
