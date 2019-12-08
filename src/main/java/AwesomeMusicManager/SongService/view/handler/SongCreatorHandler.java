package AwesomeMusicManager.SongService.view.handler;

import AwesomeMusicManager.SongService.domain.model.Song;
import AwesomeMusicManager.SongService.domain.service.SongSaverService;
import AwesomeMusicManager.SongService.view.controller.SongCollectionController;
import AwesomeMusicManager.SongService.view.model.request.SongCreateRequest;
import AwesomeMusicManager.SongService.view.model.response.SongResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;

@Service
public class SongCreatorHandler {

    private final SongSaverService songSaverService;

    @Autowired
    public SongCreatorHandler(SongSaverService songSaverService) {
        this.songSaverService = songSaverService;
    }

    public ResponseEntity create(SongCreateRequest songCreateRequest) {

        Song create = Song.builder()
                .album(songCreateRequest.getAlbum())
                .singer(songCreateRequest.getSinger())
                .name(songCreateRequest.getName())
                .build();

        Song created = songSaverService.save(create);

        String url = getUrl(created);

        SongResponse response = SongResponse.builder()
                .album(created.getAlbum())
                .singer(created.getSinger())
                .name(created.getName())
                .build();


        URI uri = URI.create(url);

        return ResponseEntity.created(uri)
                .body(response);
    }

    private String getUrl(Song song) {

        StringBuilder sb = new StringBuilder();
        return sb.append(SongCollectionController.RESOURCE)
                .append("/")
                .append(song.getId())
                .toString();
    }

}
