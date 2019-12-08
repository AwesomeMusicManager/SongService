package AwesomeMusicManager.SongService.view.controller;

import AwesomeMusicManager.SongService.view.handler.SongGetterHandler;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(value = "Songs")
@RestController
@RequestMapping(SongMemberController.RESOURCE)
public class SongMemberController {

    public static final String RESOURCE = "song/{name}";

    private final SongGetterHandler songGetterHandler;

    @Autowired
    public SongMemberController(SongGetterHandler songGetterHandler) {
        this.songGetterHandler = songGetterHandler;
    }

    @ApiOperation(value = "Greets the world or Niteroi")
    @GetMapping
    public ResponseEntity get(@PathVariable String name) {
        return songGetterHandler.getByName(name);
    }
}
