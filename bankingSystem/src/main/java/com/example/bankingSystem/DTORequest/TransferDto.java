package com.example.bankingSystem.DTORequest;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransferDto {
	
	private int transfer;
	private int transfered;
	private  double transferedAmount;
	private long lastModified;
	private String operationType;

}
