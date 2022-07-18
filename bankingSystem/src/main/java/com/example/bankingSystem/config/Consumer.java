package com.example.bankingSystem.config;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.example.bankingSystem.util.LogsFolder;

@Component
public class Consumer {
	
	
	@KafkaListener(topics = "json",
            groupId = "json")

// Method
public void consume(@Payload String message)
{
		
		LogsFolder folder=new LogsFolder();
 // Print statement
		
		System.err.println("consumer"+message);
		folder.logFileWriter(message);
}

}