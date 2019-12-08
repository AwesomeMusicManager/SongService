package AwesomeMusicManager.SongService.persistence.mongo.dao;


import AwesomeMusicManager.SongService.domain.model.Song;
import AwesomeMusicManager.SongService.domain.repository.SongRepository;
import AwesomeMusicManager.SongService.persistence.mongo.document.SongDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class SongMongoDao implements SongRepository {

    private final MongoTemplate mongoTemplate;
    public static final int UnderAge = 18;
    public static final int Elder = 65;

    @Autowired
    public SongMongoDao(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Song save(Song song) {

        SongDocument songDocument = SongDocument.builder()
                .album(song.getAlbum())
                .singer(song.getSinger())
                .name(song.getName())
                .build();

        SongDocument salvedUser = mongoTemplate.save(songDocument);

        song.setId(salvedUser.getId());

        return song;
    }

    @Override
    public Song getById(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        SongDocument songDocument = mongoTemplate.findOne(query, SongDocument.class);

        return Song.builder()
                .album(songDocument.getAlbum())
                .singer(songDocument.getSinger())
                .name(songDocument.getName())
                .build();
    }

    public Song getByName(String name) {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").regex(name));
        SongDocument songDocument = mongoTemplate.findOne(query, SongDocument.class);

        if (songDocument == null) {
            return Song.builder().build();
        }

        return Song.builder()
                .album(songDocument.getAlbum())
                .singer(songDocument.getSinger())
                .name(songDocument.getName())
                .build();
    }
}
