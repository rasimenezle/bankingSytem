package com.example.bankingSystem.service.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Instant;
import java.util.Date;
import java.util.Random;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bankingSystem.DTORequest.AccountCreateRequest;
import com.example.bankingSystem.entity.Account;
import com.example.bankingSystem.service.CreateAccountService;


@Service

public class CreateAccountServiceImpl implements CreateAccountService {

	
	
	@Autowired
	ModelMapper modelMapper;

	@Override
	public Account createAccount(AccountCreateRequest accountCreateRequest) {
		//post request convert to Account entity
		Account accountRequest=modelMapper.map(accountCreateRequest, Account.class);
		Random rand = new Random();
		int num = rand.nextInt(9000000) + 1000000;
		accountRequest.setNumber(num);
		accountRequest.setBalance(0);
		Date date=new Date();
			accountRequest.setLastModified(date.getTime());
		

		
		try {
			File output = new File("src/main/resources/"+accountRequest.getNumber()+".txt");
			BufferedWriter bw = new BufferedWriter(new FileWriter(output));
			bw.write(accountRequest.toString());
			bw.newLine();
			bw.close();
		} catch (IOException e) {

			e.printStackTrace();
		}

		return accountRequest;
	}

	

}
