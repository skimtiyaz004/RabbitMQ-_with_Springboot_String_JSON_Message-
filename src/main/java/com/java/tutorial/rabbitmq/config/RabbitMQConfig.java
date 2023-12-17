package com.java.tutorial.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    @Value("${rabbitmq.query.name}")
    private String queueName;
    @Value("${rabbitmq.query.exchange}")
    private String exchangeName;
    @Value("${rabbitmq.query.routingKey}")
    private String routingKey;
    // Creating Queue
    @Bean
    public Queue queue(){
        return new Queue(queueName);
    }
    // Creating Exchange
    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(exchangeName);
    }
    //Bing queue and exchange using routing key
    @Bean
    public Binding binding(){
        return BindingBuilder.bind(queue()).to(exchange()).with(routingKey);
    }

    //Connection Factory
    //Rabbit Template for JSON Message
    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }
    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate=new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }

    //Rabbit Admin
}
