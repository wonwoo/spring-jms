package me.wonwoo.rabbitmq;

import org.springframework.stereotype.Component;

/**
 * Created by wonwoo on 2017. 3. 23..
 */
@Component
public class RabbitMQReceiver {

  public void receiveMessage(String body) {
    System.out.println(body);
  }
}