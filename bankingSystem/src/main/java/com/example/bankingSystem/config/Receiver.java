package com.example.bankingSystem.config;

import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;

import com.example.bankingSystem.entity.Account;



public class Receiver {

  private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);

  private CountDownLatch latch = new CountDownLatch(1);

  public CountDownLatch getLatch() {
    return latch;
  }

  @KafkaListener(topics = "${kafka.topic.json}")
  public void receive(Account car) {
    LOGGER.info("received car='{}'", car.toString());
    latch.countDown();
  }
}