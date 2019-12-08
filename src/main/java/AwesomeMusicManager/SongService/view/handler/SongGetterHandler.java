package AwesomeMusicManager.SongService.view.handler;

import AwesomeMusicManager.SongService.domain.model.Song;
import AwesomeMusicManager.SongService.domain.service.SongGetterService;
import AwesomeMusicManager.SongService.domain.service.SongSaverService;
import AwesomeMusicManager.SongService.view.controller.SongCollectionController;
import AwesomeMusicManager.SongService.view.model.request.VagalumeSongRequest;
import AwesomeMusicManager.SongService.view.model.response.SongResponse;
import AwesomeMusicManager.SongService.view.service.FetchSong;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SongGetterHandler {

    private final SongGetterService songGetterService;
    private final SongSaverService songSaverService;
    private final FetchSong fetchSong;

    @Autowired
    public SongGetterHandler(SongGetterService songGetterService, SongSaverService songSaverService, FetchSong fetchSong) {
        this.songGetterService = songGetterService;
        this.songSaverService = songSaverService;
        this.fetchSong = fetchSong;
    }

    public ResponseEntity get(String id) {

        Song song = songGetterService.get(id);
        SongResponse response = SongResponse.builder()
                .album(song.getAlbum())
                .singer(song.getSinger())
                .name(song.getName())
                .build();

        String url = getUrl(song);

        return ResponseEntity.ok(response);
    }

    @Cacheable
    public ResponseEntity getByName(String name) {

        Song song = songGetterService.getByName(name);

        if (song.getName() == null) {
            ResponseEntity<VagalumeSongRequest> vagalumeSongRequest  = fetchSong.makeVagalumeRequest(name);

            if (vagalumeSongRequest.getBody().getType().equals("song_notfound")) {
                return (ResponseEntity) ResponseEntity.notFound();
            }

            new Thread(() -> {
                Song mappedSong = Song.builder()
                        .singer(vagalumeSongRequest.getBody().getArt().getName())
                        .name(vagalumeSongRequest.getBody().getMus().get(0).getName())
                        .build();
                songSaverService.save(mappedSong);
            }).start();

            SongResponse response = SongResponse.builder()
                    .singer(vagalumeSongRequest.getBody().getArt().getName())
                    .name(vagalumeSongRequest.getBody().getMus().get(0).getName())
                    .build();

            return ResponseEntity.ok(response);
        }


        SongResponse response = SongResponse.builder()
                .album(song.getAlbum())
                .singer(song.getSinger())
                .name(song.getName())
                .build();

        String url = getUrl(song);

        return ResponseEntity.ok(response);
    }

    private String getUrl(Song song) {

        StringBuilder sb = new StringBuilder();
        return sb.append(SongCollectionController.RESOURCE)
                .append("/")
                .append(song.getId())
                .toString();
    }

}
