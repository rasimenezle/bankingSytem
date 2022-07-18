package com.example.bankingSystem.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;

import com.example.bankingSystem.entity.Account;

/*

public class Sender {

	private static final Logger LOGGER = LoggerFactory.getLogger(Sender.class);

	  @Value("${kafka.topic.json}")
	  private String jsonTopic;

	  @Autowired
	  private KafkaTemplate<String, Account> kafkaTemplate;

	  public void send(Account account) {
	    LOGGER.info("sending car='{}'", account.toString());
	    kafkaTemplate.send(jsonTopic, account);
	  }
}
*/