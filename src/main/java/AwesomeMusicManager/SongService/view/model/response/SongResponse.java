package AwesomeMusicManager.SongService.view.model.response;

import AwesomeMusicManager.SongService.view.model.request.YoutubeLinkRequest;
import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
public class SongResponse {

    private String singer;
    private String name;
    private String album;
    private YoutubeLinkRequest youtube;
    private String lyric;

}
