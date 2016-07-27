package sorting.implementation;

import java.util.Arrays;

import sorting.AbstractSorting;

public class RadixSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		// TODO Auto-generated method stub

	}

	public void radix(Integer[] array, int leftIndex, int rightIndex) {
		Integer maior = max(array, leftIndex, rightIndex);
		int tamanho = maior.toString().length();
		for (int i = 0; i < tamanho; i++) {
			countingSort(array, leftIndex, rightIndex, i);
		}

	}

	public void countingSort(Integer[] array, int leftIndex, int rightIndex, int pot) {
		int menor = min(array, leftIndex, rightIndex);
		int div = (int) Math.pow(10, pot);
		int shift = 0;

		if (menor < 0) {
			shift = menor;
		}

		int[] frequencia = new int[10 - shift];
		
		// frequencia
		for (int i = leftIndex; i <= rightIndex; i++) {
			int indice = (array[i] / div) % 10;
			frequencia[indice - shift]++;
		}

		// acomulativa
		for (int i = 1; i < frequencia.length; i++) {
			frequencia[i] += frequencia[i - 1];
		}

		// Ordenacao
		Integer[] ordenado = new Integer[array.length];

		for (int i = rightIndex; i >= leftIndex; i--) {
			int index = Math.abs((array[i] / div) % 10);
			frequencia[index - shift]--;
			ordenado[frequencia[index - shift]  + leftIndex] = array[i];
		}

		// copia
		for (int i = leftIndex; i <= rightIndex; i++) {
			array[i] = ordenado[i];
		}

	}

	public Integer max(Integer[] array, int leftIndex, int rightIndex) {
		Integer maior = array[leftIndex];
		for (int i = leftIndex; i <= rightIndex; i++) {
			if (array[i].compareTo(maior) > 0) {
				maior = array[i];
			}
		}
		return maior;
	}
	
	public Integer min(Integer[] array, int leftIndex, int rightIndex) {
		Integer menor = array[leftIndex];
		for (int i = leftIndex; i <= rightIndex; i++) {
			if (array[i].compareTo(menor) < 0) {
				menor = array[i];
			}
		}
		return menor;
	}

	public static void main(String[] args) {
		Integer[] array = new Integer[] { 3, 64, 21, 1, 9, 1213, 54, 123526, -1, 5};

		RadixSort<Integer> radix = new RadixSort<>();
		radix.radix(array, 0, array.length - 1);

		System.out.println(Arrays.toString(array));
	}
}
