package com.practice.kafka.example.kafka;

import com.practice.kafka.example.pojo.Greeting;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.util.concurrent.CountDownLatch;

@Component
public class MessageListener {

    public CountDownLatch latch = new CountDownLatch(3);

    public CountDownLatch partitionLatch = new CountDownLatch(2);

    public CountDownLatch filterLatch = new CountDownLatch(2);

    public CountDownLatch greetingLatch = new CountDownLatch(1);

    @KafkaListener(topics = "${message.topic.name}", groupId = "foo", containerFactory = "fooKafkaListenerContainerFactory")
    public void listenGroupFoo(String message) {
        System.out.println("Received Message in group 'foo': " + message);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode actualObj = objectMapper.readTree(message);
        System.out.println("Received kafka group 'foo' message msg : " + actualObj.get("msg") + " name : " + actualObj.get("name"));

        latch.countDown();
    }

    @KafkaListener(topics = "${message.topic.name}", groupId = "bar", containerFactory = "barKafkaListenerContainerFactory")
    public void listenGroupBar(String message) {
        System.out.println("Received Message in group 'bar': " + message);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode actualObj = objectMapper.readTree(message);
        System.out.println("Received kafka group 'bar' message msg : " + actualObj.get("msg") + " name : " + actualObj.get("name"));
        latch.countDown();
    }

    @KafkaListener(topics = "${greeting.topic.name}", containerFactory = "greetingKafkaListenerContainerFactory")
    public void greetingListener(Greeting greeting) {
        System.out.println("Received greeting message: " + greeting);
        this.greetingLatch.countDown();
    }








}
