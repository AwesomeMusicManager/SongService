package AwesomeMusicManager.SongService.domain.service;

import AwesomeMusicManager.SongService.domain.model.Song;
import AwesomeMusicManager.SongService.domain.repository.SongRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Validated
@Service
public class SongSaverService {

    private final SongRepository songRepository;

    public SongSaverService(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    public Song save(Song song) {
        return songRepository.save(song);
    }
}
