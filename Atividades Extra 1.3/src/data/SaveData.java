package data;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SaveData {

	public void saveData(String info) {
		//File file;
		FileWriter fileWrite;
		BufferedWriter bufferWrite;
		
		//Writer arquivo = new BufferedWriter(new FileWriter("arquivo", true));
		try {
			//file = new File("sort.data");
			//if (!file.exists()) {
				fileWrite = new FileWriter("sort.data", true);
				bufferWrite = new BufferedWriter(fileWrite);
			
			bufferWrite.write("alg time sample\n");
			bufferWrite.append(info);
			bufferWrite.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
