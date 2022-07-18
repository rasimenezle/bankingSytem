package com.example.bankingSystem.controller;


import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.bankingSystem.DTORequest.AccountCreateRequest;
import com.example.bankingSystem.DTORequest.TransferBalanceRequest;
import com.example.bankingSystem.DTORequest.UpdateBalanceRequest;
import com.example.bankingSystem.DTOResponse.AccountCreateSuccessResponse;
import com.example.bankingSystem.DTOResponse.AccountDetailResponse;
import com.example.bankingSystem.DTOResponse.AccountUpdateResponse;
import com.example.bankingSystem.entity.Account;
import com.example.bankingSystem.entity.AccountType;
import com.example.bankingSystem.service.CreateAccountService;
import com.example.bankingSystem.service.DetailAccountService;
import com.example.bankingSystem.service.LogService;
import com.example.bankingSystem.service.TransferBalanceService;
import com.example.bankingSystem.service.UpdateBalanceService;
import com.example.bankingSystem.util.LogsFolder;


@CrossOrigin(origins = "http://localhost:9090")
@RestController
@RequestMapping(path="/banking")
public class AccountController {
	
	@Autowired
	CreateAccountService createAccountService;
	@Autowired
	DetailAccountService detailAccountService;
	@Autowired
	UpdateBalanceService updateBalanceService;
	@Autowired
	TransferBalanceService transferBalanceService;
	@Autowired
	LogService logService;
	
	
	

	
	@PostMapping(path="/accounts",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
	
	public ResponseEntity<?> createAccount(@RequestBody  AccountCreateRequest accountCreateRequest){
		
		
		boolean typeControl=accountCreateRequest.getType().equals(AccountType.Altın.toString()) ||
				accountCreateRequest.getType().equals(AccountType.Dolar.toString()) ||
				accountCreateRequest.getType().equals(AccountType.TL.toString());
		
		if(typeControl) {
			
			Account account=createAccountService.createAccount(accountCreateRequest);
			 return new ResponseEntity<>(AccountCreateSuccessResponse.builder()
					 .message("Account Created")
					 .accountNumber(account.getNumber()).build(),HttpStatus.OK);
			
		}else {
			
			return new ResponseEntity<>(AccountCreateSuccessResponse.builder()
					.message("Invalid Account Type"+accountCreateRequest.getType().toString())
					.build(),HttpStatus.BAD_REQUEST);
		}
		
	
	}
	
	@GetMapping(path = "/accountsDetail/{number}")
	public ResponseEntity<?> detailAccount(@PathVariable(name="number") String number){
		
		
		
		Account account=detailAccountService.getFindByNumber(number);

		if(number.equals(String.valueOf(account.getNumber()))) {
		
			return ResponseEntity.ok().lastModified(account.getLastModified()).body(account);
		}else {
			
			return new ResponseEntity<>(AccountDetailResponse.builder().message("Don't created Account").build(),HttpStatus.NOT_FOUND);
		}

	}
	
	@PatchMapping(path="/accounts/{number}" ,produces = MediaType.APPLICATION_JSON_VALUE, consumes =  MediaType.APPLICATION_JSON_VALUE)
	
	public ResponseEntity<?> updateAccountBalance(@PathVariable(name="number") String number,@RequestBody UpdateBalanceRequest updateBalanceRequest){
		
		File jsonInputFile=new File("src/main/resources/"+number+".txt");
		
		if(jsonInputFile.exists() && !jsonInputFile.isDirectory()) {
		Account account=updateBalanceService.updateBalanceWithAccountNumber(number, updateBalanceRequest,jsonInputFile);
		
	
		
		return ResponseEntity.ok().body(account);
		}
		
		else {
			return new ResponseEntity<>(AccountUpdateResponse.builder()
					.message("Invalid Account Number")
					.build(),HttpStatus.BAD_REQUEST);
		}
	}
	
	
	

	@PatchMapping(path="/accountsTransfer/{number}" ,produces = MediaType.APPLICATION_JSON_VALUE, consumes =  MediaType.APPLICATION_JSON_VALUE)
	
	public ResponseEntity<?> transferBalance(@PathVariable(name="number") String number,@RequestBody TransferBalanceRequest transferBalanceRequest){
		
		
		
		return ResponseEntity.ok(transferBalanceService.transferAccountBalance(number, transferBalanceRequest));
		
	}
	
	
	@GetMapping(path="/logDetail/{number}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> logDetail(@PathVariable(name="number") String number){
		
		
		return ResponseEntity.ok().body(logService.logdetail(number));
		
		
	}}
