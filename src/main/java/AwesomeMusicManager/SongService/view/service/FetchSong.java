package AwesomeMusicManager.SongService.view.service;

import AwesomeMusicManager.SongService.view.model.request.SongCreateRequest;
import AwesomeMusicManager.SongService.view.model.request.VagalumeSongRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FetchSong {

    private final RestTemplate restTemplate;
    private final String URL = "https://api.vagalume.com.br/search.php?apikey=660a4395f992ff67786584e238f501aa&art=U2&mus=";

    public FetchSong(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<VagalumeSongRequest> makeVagalumeRequest(String name) {
        return restTemplate.getForEntity(URL + name, VagalumeSongRequest.class);
    }


}
