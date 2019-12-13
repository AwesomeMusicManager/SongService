package AwesomeMusicManager.SongService.view.service;
import AwesomeMusicManager.SongService.view.model.request.LyricSongRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FetchLyric {

    private final RestTemplate restTemplate;
    private final String URL = "http://lyric-service-app.herokuapp.com/api/v1/lyric?";

    public FetchLyric(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<LyricSongRequest> makeLyricRequest(String artist, String song) {
        return restTemplate.getForEntity(URL + "artist=" + artist + "&song=" + song, LyricSongRequest.class);
    }
}
