package com.example.bankingSystem.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum AccountType {
	@JsonProperty("TL")
	TL,
	@JsonProperty("Dolar")
	Dolar,
	@JsonProperty("Altın")
	Altın;
	
	

	
	

}
