package com.example.bankingSystem.DTORequest;



import java.io.Serializable;

import com.example.bankingSystem.entity.AccountType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class AccountCreateRequest implements Serializable {
	
	private String name;
	private String surname;
	private String email;
	private String tc; 
	private String type;
	private long lastModified;
	



}
