package me.wonwoo.rabbitmq;


import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import me.wonwoo.activemq.Email;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.rule.OutputCapture;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * run rabbit mq server
 *
 * Created by wonwoo on 2017. 3. 23..
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RabbitAutoConfiguration.class, RabbitMQConfig.class, RabbitMQReceiver.class})
public class RabbitMQReceiverTests {

  @Autowired
  private RabbitTemplate rabbitTemplate;

  @Rule
  public OutputCapture outputCapture = new OutputCapture();

  private CountDownLatch latch = new CountDownLatch(1);

  @Test
  public void senderTest() throws InterruptedException {
    rabbitTemplate.convertAndSend(
      "spring-rabbit-sample",
      new Email("test@test.com", "hi spring jms")
    );
    latch.await(2, TimeUnit.SECONDS);
    final String actual = this.outputCapture.toString();
    assertThat(actual)
      .contains("Email{to='test@test.com', body='hi spring jms'}");
  }

}