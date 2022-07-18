package com.example.bankingSystem.service.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.bankingSystem.DTORequest.TransferBalanceRequest;
import com.example.bankingSystem.DTORequest.TransferDto;
import com.example.bankingSystem.DTOResponse.TransferBalanceResponse;
import com.example.bankingSystem.entity.Account;
import com.example.bankingSystem.entity.AccountType;
import com.example.bankingSystem.service.TransferBalanceService;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class TransferBalanceServiceImpl implements TransferBalanceService {

	@Autowired
	KafkaTemplate<String, String> sender;

	@Override
	public TransferBalanceResponse transferAccountBalance(String transferNumber,TransferBalanceRequest transferBalanceRequest) {
		
//		 try {
//	            String url = "https://api.collectapi.com/economy/singleCurrency?int=1&tag=USD";
//	            USD_TRY = HttpUtil.sendGetRequest(url, accessToken)
//	                    .getJSONArray("result")
//	                    .getJSONObject(0)
//	                    .getDouble("selling");
//	        } catch (Exception e) {
//	            throw new ResponseStatusException(
//	                    HttpStatus.INTERNAL_SERVER_ERROR,
//	                    String.format("Failed to get TRY/USD exchange data: %s", e)
//	            );
//	        }
		
		String result="Insufficient balance";
		double  dolar=17;
		double altın=1000;
		double altınDolar=55;
		Account transferAccount=new Account();
		Account transferAccount2=new Account();
		
		ObjectMapper modelMapper=new ObjectMapper();
		
		modelMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
		File transferOutput = new File("src/main/resources/"+transferNumber+".txt");
		File transferedOutput = new File("src/main/resources/"+transferBalanceRequest.getNumber()+".txt");
		double amount=0.;
		try {
			BufferedReader buf = new BufferedReader(new FileReader(transferOutput));
			while (buf.ready()) {
				Date date=new Date();
				String data = buf.readLine();
				if (data.contains(transferNumber)) {
					transferAccount = modelMapper.readValue(data.getBytes(), Account.class);
			
					
					if(transferAccount.getBalance()>=transferBalanceRequest.getBalance()) {
						
						transferAccount.setBalance(transferAccount.getBalance()-transferBalanceRequest.getBalance());
						transferAccount.setLastModified(date.getTime());
						transferBalanceRequest.setOperationType(transferAccount.getType().toString());
//						System.err.println(transferBalanceRequest.getOperationType());
						result="Transferred Successfully";
					}
					
					
				}
				BufferedWriter bufWriter=new BufferedWriter(new FileWriter(transferOutput));
				bufWriter.write(transferAccount.toString());
				
				bufWriter.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			BufferedReader buf = new BufferedReader(new FileReader(transferedOutput));
			while (buf.ready()) {
				Date date=new Date();
				String data = buf.readLine();
				if (data.contains(transferBalanceRequest.getNumber())) {
					transferAccount2 = modelMapper.readValue(data.getBytes(), Account.class);
					if(transferAccount.getType().equals(AccountType.TL) && transferAccount2.getType().equals(AccountType.TL)) {
						amount=transferBalanceRequest.getBalance();
						
					}
					else if(transferAccount.getType().equals(AccountType.TL)&& transferAccount2.getType().equals(AccountType.Dolar)) {
						amount=transferBalanceRequest.getBalance()/dolar;
					}
					else if(transferAccount.getType().equals(AccountType.TL)&& transferAccount2.getType().equals(AccountType.Altın)) {
						amount=transferBalanceRequest.getBalance()/altın;
					}
					else if(transferAccount.getType().equals(AccountType.Altın)&& transferAccount2.getType().equals(AccountType.TL)) {
						amount=transferBalanceRequest.getBalance()*altın;
					}
					else if(transferAccount.getType().equals(AccountType.Altın)&& transferAccount2.getType().equals(AccountType.Altın)) {
						amount=transferBalanceRequest.getBalance();
					}
					else if(transferAccount.getType().equals(AccountType.Altın)&& transferAccount2.getType().equals(AccountType.Dolar)) {
						amount=transferBalanceRequest.getBalance()*altınDolar;
					}
					else if(transferAccount.getType().equals(AccountType.Dolar)&& transferAccount2.getType().equals(AccountType.Dolar)) {
						amount=transferBalanceRequest.getBalance();
					}
					else if(transferAccount.getType().equals(AccountType.Dolar)&& transferAccount2.getType().equals(AccountType.Altın)) {
						amount=transferBalanceRequest.getBalance()/altınDolar;
					}
					else {
						amount=transferBalanceRequest.getBalance()*dolar;
					}

						
						transferAccount2.setBalance(transferAccount2.getBalance()+amount);
						transferAccount.setLastModified(date.getTime());
						
				
				}
				BufferedWriter bufWriter=new BufferedWriter(new FileWriter(transferedOutput));
				bufWriter.write(transferAccount2.toString());
				bufWriter.close();
				
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String message=transferNumber+" transfer amount: "+transferBalanceRequest.getBalance()+" "+transferBalanceRequest.getOperationType()+" ,transferred_account: "+transferBalanceRequest.getNumber();
		
		System.err.println(transferBalanceRequest.getOperationType());
		sender.send("json", message);
		return TransferBalanceResponse.builder().message(result).build();
		
	}

}
