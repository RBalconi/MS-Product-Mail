package com.shopms.product.amqp;

import com.shopms.rabbitmq.dtos.MessageDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RabbitMQService {


    @Value("${spring.rabbitmq.queue}")
    private String queueValue;


    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private DirectExchange directExchange;


    public void sendMessage(MessageDto object) {
        try {
            rabbitTemplate.convertAndSend(directExchange.getName(), queueValue, object);
            log.info("Message sent to queue: {}", queueValue);
        } catch (Exception e) {
            log.error("Error while sending message to queue: {}", queueValue, e);
        }
    }

}
