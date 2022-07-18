package com.example.bankingSystem.service;

import java.io.File;

import com.example.bankingSystem.DTORequest.UpdateBalanceRequest;
import com.example.bankingSystem.entity.Account;

public interface UpdateBalanceService {
	
	Account updateBalanceWithAccountNumber(String number,UpdateBalanceRequest updateBalanceRequest,File file);

}
