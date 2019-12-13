package AwesomeMusicManager.SongService.helpers.rabbitmq;

import AwesomeMusicManager.SongService.domain.model.Song;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Value("${javainuse.rabbitmq.exchange}")
    private String exchange;

    @Value("${javainuse.rabbitmq.routingkey}")
    private String routingkey;

    public void send(Song song) {
        rabbitTemplate.convertAndSend(exchange, routingkey, song);
        System.out.println("Send msg = " + song);

    }
}