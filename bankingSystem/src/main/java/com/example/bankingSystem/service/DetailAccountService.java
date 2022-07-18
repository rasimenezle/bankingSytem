package com.example.bankingSystem.service;

import com.example.bankingSystem.entity.Account;

public interface DetailAccountService {
	
	Account getFindByNumber(String number);

}
