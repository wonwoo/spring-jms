package me.wonwoo.rabbitmq;

import me.wonwoo.activemq.Email;
import org.springframework.stereotype.Component;

/**
 * Created by wonwoo on 2017. 3. 23..
 */
@Component
public class RabbitMQReceiver {

  public void receiveMessage(Email email) {
    System.out.println(email);
  }
}