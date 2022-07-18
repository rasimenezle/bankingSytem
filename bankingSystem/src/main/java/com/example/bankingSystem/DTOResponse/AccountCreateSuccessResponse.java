package com.example.bankingSystem.DTOResponse;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountCreateSuccessResponse implements Serializable {
	
	private int accountNumber;
	private String message;
	
	
	
}
