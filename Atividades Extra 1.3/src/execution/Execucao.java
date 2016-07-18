package execution;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;

import javax.management.RuntimeErrorException;

import data.SaveData;
import sorting.AbstractSorting;
import sorting.implementations.InsertionSort;
import sorting.implementations.MergeSort;
import sorting.implementations.QuickSort;
import sorting.implementations.SelectionSort;

public class Execucao {
	private FileWriter fileW;
	public BufferedWriter bufferW;
	
	public Execucao() {
		try {
			fileW =  new FileWriter("sort.data", true);
			bufferW = new BufferedWriter(fileW);
			fileW.write("alg time sample\n");
		} catch (IOException erro) {
			throw new RuntimeException("Erro na gravação de dados");
		}

	}
	
	public Integer[] getRandomVector(int tamanho) {
		Integer[] array = new Integer[tamanho];	
		Random randomGenerator = new Random();
		
		for (int i = 0; i < array.length; i++) {
			array[i] = randomGenerator.nextInt(10000);
		}
		return array;
	}
	
	
	public void runSort() {
		AbstractSorting sorting;
		Integer[] array;
		long startTime; 
		long endTime;
		long totalTime;
		
		for (int i = 20000; i <= 50000; i+=1000) {
			array = getRandomVector(i);
			Integer[] copy = Arrays.copyOf(array, array.length);
			Integer[] copy2 = Arrays.copyOf(array, array.length);
			Integer[] copy3 = Arrays.copyOf(array, array.length);
			Integer[] copy4 = Arrays.copyOf(array, array.length);
			
			
			sorting = new MergeSort();
			startTime = new Date().getTime();
			sorting.sort(copy4);
			endTime = new Date().getTime();
			
			totalTime = endTime - startTime;
			writeInfo("MergeSort", totalTime, i);
			
			
			
			sorting = new QuickSort();
			startTime = new Date().getTime();
			sorting.sort(copy3);
			endTime = new Date().getTime();
			
			totalTime = endTime - startTime;
			writeInfo("QuickSort", totalTime, i);
			
			
			
			sorting = new InsertionSort();
			startTime = new Date().getTime();
			sorting.sort(copy2);
			endTime = new Date().getTime();
			
			totalTime = endTime - startTime;
			writeInfo("InsertionSort", totalTime, i);
			
			
			
			sorting = new SelectionSort();
			startTime = new Date().getTime();
			sorting.sort(copy);
			endTime = new Date().getTime();
			
			totalTime = endTime - startTime;
			writeInfo("SelectionSort", totalTime, i);
			

		}
	}


	private void writeInfo(String sortingName, long totalTime, int sample) {
		String info = sortingName + " " + totalTime + " " + sample + "\n";
		saveData(info);
	}
	
	private void saveData(String info) {
		try {
			fileW.append(info);
		} catch(IOException erro) {
			throw new RuntimeException( "Erro na gravacao de dados");
		}
	}

}
