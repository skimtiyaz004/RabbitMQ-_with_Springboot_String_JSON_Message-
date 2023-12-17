package com.java.tutorial.rabbitmq.controller;

import com.java.tutorial.rabbitmq.dto.WorkDTO;
import com.java.tutorial.rabbitmq.producer.RabbitMQProducer;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/message")
public class MessageController {
    @Autowired
    private RabbitMQProducer rabbitMQProducer;
    @GetMapping("/publish")
    public ResponseEntity<?> sendMessage(@RequestParam String message){
        rabbitMQProducer.sendMessage(message);
        return ResponseEntity.ok("Message Send");
    }

    @PostMapping("/publish/json")
    public ResponseEntity<?> sendMessageJson(@RequestBody WorkDTO req){
        rabbitMQProducer.sendJsonMessage(req);
        return ResponseEntity.ok("JSON Message Send");
    }
}
