package me.wonwoo.activemq;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * Created by wonwoo on 2017. 3. 23..
 */
@Component
public class Receiver {

  @JmsListener(destination = "mailbox")
  public void receiveMessage(Email email) {
    System.out.println(email);
  }

}