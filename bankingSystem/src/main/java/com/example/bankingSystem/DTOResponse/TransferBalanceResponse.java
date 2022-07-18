package com.example.bankingSystem.DTOResponse;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransferBalanceResponse {
	private String message;

}
