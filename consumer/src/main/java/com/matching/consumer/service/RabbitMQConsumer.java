package com.matching.consumer.service;

import java.io.IOException;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;
import com.rabbitmq.client.Channel;

@EnableRabbit
@Service
public class RabbitMQConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQConsumer.class);

    @RabbitListener(queues = { "${rabbitmq.queue.name}" })
    public void consume(String message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag)
            throws ClassNotFoundException, SQLException, IOException {

        // sending acknowledgement to rabbitmq
        channel.basicAck(tag, false);
        LOGGER.info(String.format("Received message -> %s", message));

    }

}