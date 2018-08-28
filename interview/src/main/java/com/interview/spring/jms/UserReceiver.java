package com.interview.spring.jms;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;


@Component
public class UserReceiver {


  private int count = 1;

  @JmsListener(destination = "OrderTransactionQueue", containerFactory = "myFactory")
  public void receiveMessage(String message) {
    System.out.println("<" + count + "> Received <" + message + ">");
    count++;
  }
}
