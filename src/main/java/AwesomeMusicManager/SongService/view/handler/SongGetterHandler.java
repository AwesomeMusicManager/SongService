package AwesomeMusicManager.SongService.view.handler;

import AwesomeMusicManager.SongService.domain.model.Song;
import AwesomeMusicManager.SongService.domain.service.SongGetterService;
import AwesomeMusicManager.SongService.domain.service.SongSaverService;
import AwesomeMusicManager.SongService.view.model.request.LyricSongRequest;
import AwesomeMusicManager.SongService.view.model.request.VagalumeSongResponse;
import AwesomeMusicManager.SongService.view.model.request.YoutubeLinkRequest;
import AwesomeMusicManager.SongService.view.model.response.SongResponse;
import AwesomeMusicManager.SongService.view.service.FetchLyric;
import AwesomeMusicManager.SongService.view.service.FetchSong;
import AwesomeMusicManager.SongService.view.service.FetchStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SongGetterHandler {

    private final SongGetterService songGetterService;
    private final SongSaverService songSaverService;
    private final FetchSong fetchSong;
    private final FetchLyric fetchLyric;
    private final FetchStream fetchStream;

    private final String link = "https://www.youtube.com/watch?v=";

    @Autowired
    public SongGetterHandler(SongGetterService songGetterService, SongSaverService songSaverService, FetchSong fetchSong, FetchLyric fetchLyric, FetchStream fetchStream) {
        this.songGetterService = songGetterService;
        this.songSaverService = songSaverService;
        this.fetchSong = fetchSong;
        this.fetchLyric = fetchLyric;
        this.fetchStream = fetchStream;
    }

    public ResponseEntity getByName(String name) {

        Song song = songGetterService.getByName(name);

        SongResponse response = SongResponse.builder()
                .album(song.getAlbum())
                .singer(song.getSinger())
                .name(song.getName())
                .lyric(song.getLyric())
                .youtube(song.getYoutube())
                .build();

        if (song.getName() == null) {
            ResponseEntity<VagalumeSongResponse> vagalumeSongRequest  = fetchSong.makeVagalumeRequest(name);

            if (vagalumeSongRequest.getBody().getResponse().getNumFound() == 0) {
                return ResponseEntity.notFound().build();
            }

            String title = vagalumeSongRequest.getBody().getResponse().getDocs().get(0).getTitle();
            String band = vagalumeSongRequest.getBody().getResponse().getDocs().get(0).getBand();

            ResponseEntity<LyricSongRequest> lyric = fetchLyric.makeLyricRequest(band, title);
            ResponseEntity<YoutubeLinkRequest> youtubeLinkRequest = fetchStream.makeYoutubeLinkRequest(title);
            youtubeLinkRequest.getBody().setLink(link + youtubeLinkRequest.getBody().getId());

            response.setName(title);
            response.setSinger(band);
            response.setLyric(lyric.getBody().getLyric());
            response.setYoutube(youtubeLinkRequest.getBody());

            new Thread(() -> {
                Song mappedSong = Song.builder()
                        .singer(band)
                        .name(title)
                        .lyric(lyric.getBody().getLyric())
                        .youtube(youtubeLinkRequest.getBody())
                        .build();
                songSaverService.save(mappedSong);
            }).start();

            return ResponseEntity.ok(response);
        }

        return ResponseEntity.ok(response);
    }
}
