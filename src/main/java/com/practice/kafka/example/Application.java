package com.practice.kafka.example;

import com.practice.kafka.example.kafka.MessageListener;
import com.practice.kafka.example.kafka.MessageProducer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

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
		producer.sendMessage("Hello, World!");
		listener.latch.await(10, TimeUnit.SECONDS);

		context.close();
	}

}
