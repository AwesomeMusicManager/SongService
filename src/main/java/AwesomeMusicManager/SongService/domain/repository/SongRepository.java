package AwesomeMusicManager.SongService.domain.repository;

import AwesomeMusicManager.SongService.domain.model.Song;

import java.util.List;

public interface SongRepository {

    Song save(Song song);
    Song getById(String id);
    Song getByName(String name);
}
