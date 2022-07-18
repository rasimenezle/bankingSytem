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
public class UpdateBalanceRequest implements Serializable{

	private int balance;
	private String operationType;
}
