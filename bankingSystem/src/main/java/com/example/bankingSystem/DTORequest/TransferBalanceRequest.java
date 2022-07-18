package com.example.bankingSystem.DTORequest;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransferBalanceRequest implements Serializable {
	private String number;
	private double balance;
	private String operationType;
	

}
