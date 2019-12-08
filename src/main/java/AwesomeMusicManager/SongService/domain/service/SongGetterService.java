package AwesomeMusicManager.SongService.domain.service;

import AwesomeMusicManager.SongService.domain.model.Song;
import AwesomeMusicManager.SongService.domain.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SongGetterService {

    private final SongRepository songRepository;

    @Autowired
    public SongGetterService(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    public Song get(String id) {
        return songRepository.getById(id);
    }

    public Song getByName(String name) {
        return songRepository.getByName(name);
    }
}