package com.example.bankingSystem.DTORequest;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class LogDto {
	
	private String log;
	 
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("{ ");
		sb.append("\"type\": ").append("\"").append(log).append("\"");
		sb.append("}");
		return sb.toString();

	}

}
