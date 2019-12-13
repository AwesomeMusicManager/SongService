package AwesomeMusicManager.SongService.view.service;

import AwesomeMusicManager.SongService.view.model.request.SongCreateRequest;
import AwesomeMusicManager.SongService.view.model.request.VagalumeSongRequest;
import AwesomeMusicManager.SongService.view.model.request.VagalumeSongResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class FetchSong {

    private final RestTemplate restTemplate;
    private final String URL = "https://api.vagalume.com.br/search.excerpt?apikey=660a4395f992ff67786584e238f501aa&q=";

    public FetchSong(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<VagalumeSongResponse> makeVagalumeRequest(String name) {
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
        messageConverters.add(converter);
        restTemplate.setMessageConverters(messageConverters);
        return restTemplate.getForEntity(URL + name, VagalumeSongResponse.class);
    }

}
