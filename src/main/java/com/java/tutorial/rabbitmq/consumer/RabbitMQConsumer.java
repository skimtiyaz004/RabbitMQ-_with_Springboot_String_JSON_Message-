package com.java.tutorial.rabbitmq.consumer;

import com.java.tutorial.rabbitmq.dto.WorkDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQConsumer {
    private static final Logger LOGGER= LoggerFactory.getLogger(RabbitMQConsumer.class);


//    @RabbitListener(queues = {"${rabbitmq.query.name}"})
    public void Consume(String message){
        LOGGER.info("Message Received======>"+message);
    }
    @RabbitListener(queues = {"${rabbitmq.query.name}"})
    public void Consume(WorkDTO workDTO){

        try {
            // Sleep for 5 minutes (300,000 milliseconds)
            System.out.println("Waiting .........");
            Thread.sleep(10000);

            // Process the transaction status
            System.out.println("Transaction status received after 5 minutes: " + workDTO);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Error while sleeping: " + e.getMessage(), e);
        }
    }
}
