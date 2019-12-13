package AwesomeMusicManager.SongService.view.controller;

import AwesomeMusicManager.SongService.view.handler.SongCreatorHandler;
import AwesomeMusicManager.SongService.view.model.request.SongCreateRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(SongCollectionController.RESOURCE)
public class SongCollectionController {

    public static final String RESOURCE = "song/new";
    private final SongCreatorHandler songCreatorHandler;

    public SongCollectionController(SongCreatorHandler songCreatorHandler) {
        this.songCreatorHandler = songCreatorHandler;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Void> create(@RequestBody SongCreateRequest request) {
        return songCreatorHandler.create(request);
    }


}
