package com.example.bankingSystem.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bankingSystem.entity.Account;
import com.example.bankingSystem.service.DetailAccountService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class DetailAccountServiceImpl implements DetailAccountService{


	
	@Override
	public Account getFindByNumber(String number) {
		
		Account account=new Account();
		ObjectMapper mapper=new ObjectMapper();
		
		File jsonInputFile=new File("src/main/resources/"+number+".txt");
		

		try {
			BufferedReader buf = new BufferedReader(new FileReader(jsonInputFile));
			while (buf.ready()) {
				String data = buf.readLine();
				if (data.contains(number)) {
					account = mapper.readValue(data.getBytes(), Account.class);
				}

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			return account;
	} 
	
	

}
