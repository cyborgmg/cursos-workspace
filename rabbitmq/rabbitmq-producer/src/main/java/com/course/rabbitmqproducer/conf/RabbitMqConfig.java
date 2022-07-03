package com.course.rabbitmqproducer.conf;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig 
{

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange("cyborg.exchange");
    }

    @Bean
    public Queue queueDILI() {
        return new Queue("cyborg.queue");
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(this.queueDILI()).to(this.directExchange()).with("cyborg.routingkey");
    }

    @Bean
    public MessageConverter jsonMessageConverter() 
    {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) 
    {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(jsonMessageConverter());
        return template;
    }
}