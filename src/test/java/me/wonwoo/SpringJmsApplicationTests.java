package me.wonwoo;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import me.wonwoo.activemq.Email;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.rule.OutputCapture;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringJmsApplicationTests {

	@Autowired
	private JmsTemplate jmsTemplate;

	@Rule
	public OutputCapture outputCapture = new OutputCapture();

	private CountDownLatch latch = new CountDownLatch(1);

	@Test
	public void senderTest() throws InterruptedException {
		jmsTemplate.convertAndSend(
			"mailbox",
			new Email("test@test.com", "hi spring jms")
		);
		latch.await(1, TimeUnit.SECONDS);
		final String actual = this.outputCapture.toString();
		assertThat(actual)
			.contains("Email{to='test@test.com', body='hi spring jms'}");

	}
}
