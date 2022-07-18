package com.example.bankingSystem.util;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.stereotype.Component;

@Component
public class LogsFolder {
	String logs="src/main/resources/logs.txt";
	File filePath=new File(logs);
	public void logFileWriter(String message) {
	
	try {
		
    
		PrintWriter out = new PrintWriter(new FileWriter(logs, true));
		out.append(message+"\n");
		out.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}}

}
