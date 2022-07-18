package com.example.bankingSystem.service;

import java.util.List;

import com.example.bankingSystem.DTORequest.AccountCreateRequest;
import com.example.bankingSystem.entity.Account;

public interface CreateAccountService {
	
	Account createAccount(AccountCreateRequest accountCreateRequest);

}
