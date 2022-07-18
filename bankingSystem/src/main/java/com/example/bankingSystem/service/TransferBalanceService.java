package com.example.bankingSystem.service;

import com.example.bankingSystem.DTORequest.TransferBalanceRequest;
import com.example.bankingSystem.DTOResponse.TransferBalanceResponse;

public interface TransferBalanceService {
	
	TransferBalanceResponse transferAccountBalance(String transferedNumber,TransferBalanceRequest transferBalanceRequest);
}
