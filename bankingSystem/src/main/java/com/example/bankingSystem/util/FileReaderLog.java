package com.example.bankingSystem.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class FileReaderLog {
	public List<String> readFile(String filePath) {

		List<String> data = new ArrayList<>();

		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
			String line = bufferedReader.readLine();
			while (line != null) {
				data.add(line);
				line = bufferedReader.readLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return data;
	}

}
