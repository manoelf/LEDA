package sorting.linearSorting;

import java.util.Arrays;

import sorting.AbstractSorting;

/**
 * Classe que implementa do Counting Sort vista em sala. 
 * Desta vez este algoritmo deve satisfazer os seguitnes requisitos:
 * - Alocar o tamanho minimo possivel para o array de contadores (C)
 * - Ser capaz de ordenar arrays contendo numeros negativos
 */                                                                                                                                                                                                        
public class ExtendedCountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array,int leftIndex, int rightIndex) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}
	
	
	public void countingSort(Integer[] array, int leftIndex, int rightIndex) {
		int min = min(array, leftIndex, rightIndex);
		int max = max(array, leftIndex, rightIndex);
		int tamanho = max;
		int shift = 0;
		if (min < 0) {
			tamanho = max + Math.abs(min);
			shift = Math.abs(min);
		}
		int[] frequencia = new int[tamanho];
		
		//Frequencia
		for (int i = leftIndex; i <= rightIndex; i++) {
			frequencia[array[i]+shift]++;
		}
		
		//Acomulativa
		for (int i = 1; i < frequencia.length; i++) {
			frequencia[i] += frequencia[i -1];
		}
		
		//Sorting
		Integer[] ordenado = new Integer[array.length];
		
		for (int i = rightIndex; i >= leftIndex; i--) {
			ordenado[frequencia[array[i] + shift]] = array[i];
			frequencia[array[i] + shift]--;
		}
		
		
		//Copia
		for (int i = leftIndex; i <= rightIndex; i++) {
			array[i] = ordenado[i];
		}
		
		
	}
	
	
	public Integer max(Integer[] array, int leftIndex, int rightIndex) {
		int max = array[leftIndex];
		
		for (int i = leftIndex; i <= rightIndex; i++) {
			if (array[i].compareTo(max) > 0) {
				max = array[i];
			}
		}
		
		return max;
	}

	
	
	public Integer min(Integer[] array, int leftIndex, int rightIndex) {
		int min = array[leftIndex];
		
		for (int i = leftIndex; i <= rightIndex; i++) {
			if (array[i].compareTo(min) < 0) {
				min = array[i];
			}
		}
		
		return min;
	}
	
	
	public static void main(String[] args) {
		ExtendedCountingSort counting = new ExtendedCountingSort();
		
		
		Integer[] array = new Integer[] {3, 5, 2, 8, 9, 0, 1, 10, 2};
		int left = 0;
		int right = array.length - 1;
		
		
		Integer[] auxiliar = Arrays.copyOf(array, array.length);
		Arrays.sort(auxiliar, left, right + 1);
		System.out.println("Array:    " + Arrays.toString(array));
		
		System.out.println("Esperado: " + Arrays.toString(auxiliar));
		
		counting.sort(array, left, right);
		
		System.out.println("Obtido:   " + Arrays.toString(array));

	}
}
