package com.example.bankingSystem.entity;

import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account implements Serializable {
	private String name;
	private String surname;
	private String email;
	private String tc;
	private AccountType type;
	private int number;
	private double balance;
	
	private long lastModified;

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("{ ");
		sb.append("\"name\":").append("\"").append(name).append("\"").append(",");
		sb.append("\"surname\": ").append("\"").append(surname).append("\"").append(",");
		sb.append("\"email\":").append("\"").append(email).append("\"").append(",");
		sb.append("\"tc\": ").append("\"").append(tc).append("\"").append(",");
		sb.append("\"number\": ").append("\"").append(number).append("\"").append(",");
		sb.append("\"balance\": ").append("\"").append(balance).append("\"").append(",");
		sb.append("\"lastModified\":").append("\"").append(lastModified).append("\"").append(",");
		sb.append("\"type\": ").append("\"").append(type).append("\"");
		sb.append("}");
		return sb.toString();

	}

}
