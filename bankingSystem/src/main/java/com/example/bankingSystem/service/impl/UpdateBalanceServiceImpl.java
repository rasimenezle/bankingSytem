package com.example.bankingSystem.service.impl;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.bankingSystem.DTORequest.UpdateBalanceRequest;
import com.example.bankingSystem.entity.Account;

import com.example.bankingSystem.service.UpdateBalanceService;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class UpdateBalanceServiceImpl implements UpdateBalanceService{
	
	
	@Autowired
	KafkaTemplate<String, String> sender;

	
	@Override
	public Account updateBalanceWithAccountNumber(String number,UpdateBalanceRequest updateBalanceRequest,File jsonInputFile) {
		
		Account account=new Account();
		File logs=new File("src/main/resources/logs.txt");
		ObjectMapper modelMapper=new ObjectMapper();
		
		modelMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
				
		
		try {
			BufferedReader buf = new BufferedReader(new FileReader(jsonInputFile));
			while (buf.ready()) {
				Date date=new Date();
				String data = buf.readLine();
				if (data.contains(number)) {
					account = modelMapper.readValue(data.getBytes(), Account.class);
					account.setBalance(account.getBalance()+updateBalanceRequest.getBalance());
					account.setLastModified(date.getTime());
					updateBalanceRequest.setOperationType(account.getType().toString());
				}
				BufferedWriter bufWriter=new BufferedWriter(new FileWriter(jsonInputFile));
				bufWriter.write(account.toString());
				bufWriter.close();
				
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		String message=number+" deposit amount: "+updateBalanceRequest.getBalance()+" "+updateBalanceRequest.getOperationType();
		sender.send("json", message);
		
		return account;
	}

	}
