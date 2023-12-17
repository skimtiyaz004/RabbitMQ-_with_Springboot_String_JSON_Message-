package com.java.tutorial.rabbitmq.producer;

import com.java.tutorial.rabbitmq.dto.WorkDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQProducer {
    @Value("${rabbitmq.query.exchange}")
    private String exchangeName;
    @Value("${rabbitmq.query.routingKey}")
    private String routingKey;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    private static final Logger LOGGER= LoggerFactory.getLogger(RabbitMQProducer.class);

    public void sendMessage (String message){
        LOGGER.info(String.format("Message send -> %s",message));
        rabbitTemplate.convertAndSend(exchangeName,routingKey,message);
    }

    public void sendJsonMessage(WorkDTO workDTO){
        LOGGER.info("Json Message Send"+workDTO);
        rabbitTemplate.convertAndSend(exchangeName,routingKey,workDTO);
    }
}
