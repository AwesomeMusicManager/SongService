package AwesomeMusicManager.SongService.view.service;

import AwesomeMusicManager.SongService.view.model.request.YoutubeLinkRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FetchStream {

    private final RestTemplate restTemplate;
    private final String URL = "https://amm-song-downloader.herokuapp.com/api/AudioStream/information/video/";

    public FetchStream(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<YoutubeLinkRequest> makeYoutubeLinkRequest(String song) {
        return restTemplate.getForEntity(URL + song, YoutubeLinkRequest.class);
    }
}
