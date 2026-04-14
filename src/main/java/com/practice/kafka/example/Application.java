package com.practice.kafka.example;

import com.practice.kafka.example.kafka.MessageListener;
import com.practice.kafka.example.kafka.MessageProducer;
import com.practice.kafka.example.pojo.Greeting;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import tools.jackson.databind.ObjectMapper;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class Application {



	public static void main(String[] args) throws Exception{
		ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);

		MessageProducer producer = context.getBean(MessageProducer.class);
		MessageListener listener = context.getBean(MessageListener.class);


		/**
		 * Sending a Hello World message to topic 'pingo'
		 * Must be received by both listeners with group foo
		 * and bar with containerFactory fooKafkaListenerContainerFactory
		 * and barKafkaListenerContainerFactory respectively.
		 * It will also be received by the listener with
		 * headersKafkaListenerContainerFactory as container factory.
		 */
		//producer.sendMessage("Hello, World!");
		//listener.latch.await(10, TimeUnit.SECONDS);


		/**
		 * Sending message to 'greeting' topic. This will send
		 * and received a java object with the help of
		 * greetingKafkaListenerContainerFactory
		 */
		//producer.sendGreetingMessage(new Greeting("Greetings", "World!"));
		//listener.greetingLatch.await(10, TimeUnit.SECONDS);


		/**
		 * Sending message with JSON String format to topic 'pingo'
		 * Must be received by both listeners with group foo and bar
		 */
		ObjectMapper objectMapper  = new ObjectMapper();

		String jsonString = objectMapper.writeValueAsString(new Greeting("Hello World!", "Greeting"));
		producer.sendMessage(jsonString);
		listener.latch.await(10, TimeUnit.SECONDS);

		context.close();
	}

}
