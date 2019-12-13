package AwesomeMusicManager.SongService.helpers.rabbitmq;

import AwesomeMusicManager.SongService.domain.model.Song;
import AwesomeMusicManager.SongService.domain.service.SongSaverService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQReceiver {

    private final SongSaverService songSaverService;

    public RabbitMQReceiver(SongSaverService songSaverService) {
        this.songSaverService = songSaverService;
    }

    @RabbitListener(queues = "${javainuse.rabbitmq.queue}")
    public void receive(Song song) {
        System.out.println("Starting to save: " + song.getName());
        songSaverService.save(song);
    }
}
