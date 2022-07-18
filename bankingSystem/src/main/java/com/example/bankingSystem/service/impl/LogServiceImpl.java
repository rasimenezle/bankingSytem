package com.example.bankingSystem.service.impl;


import java.io.BufferedReader;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


import org.springframework.stereotype.Service;

import com.example.bankingSystem.DTORequest.LogDto;
import com.example.bankingSystem.service.LogService;
import com.example.bankingSystem.util.FileReaderLog;

@Service
public class LogServiceImpl implements LogService {
	
	FileReaderLog fileReader;
	@Override
	public LogDto logdetail(String accountNumber) {
		
		
		List<String> logDto=this.readFile(accountNumber);
		if(logDto.isEmpty()) {
			System.err.print("*******");
			return  new LogDto("this account number don't created");
		}else {
		return  new LogDto(logDto.toString());}
	
	}
	public List<String> readFile(String accountNumber){
		List<String> data = new ArrayList<>();
		List<String> fileRead=new ArrayList<>();
		List<String> dto=new ArrayList<>();
		System.err.print("**************---------");
		
		try {
			
			InputStream is = getClass().getResourceAsStream("/logs.txt");
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));			
			String line;
			while ((line=bufferedReader.readLine())!=null) {
				data.add(line);
				fileRead.addAll(data);
				line = bufferedReader.readLine();
				System.err.println(fileRead);
			
				
			}
			
			bufferedReader.close();
	
		} catch (Exception e) {
			// TODO: handle exception
		}

		for(String read:fileRead ) {
			dto.add(response(read));
		}
		
		
		return dto;
	}
	
	public String response(String read){
		List<String> logString = new ArrayList<>();		
		for (String parsedText : read.split(" ")) {
			for (String parsedText2 : parsedText.split(":")) {				
				logString.add(parsedText2);
			}
		}

		if (logString.size() == 5) {
			
			return  new String(
					logString.get(0) + " nolu hesaba " +logString.get(3)+ " " +logString.get(4) + " yatırılmıştır.");
			
		}
		return  new String(
				logString.get(0) + " hesaptan " +logString.get(6) + " nolu hesaba " +logString.get(3)+" "+logString.get(4) +" transfer edilmiştir.");
	}
	
}
