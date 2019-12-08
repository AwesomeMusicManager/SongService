package AwesomeMusicManager.SongService.view.model.response;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
public class SongResponse {

    private String id;
    private String singer;
    private String name;
    private String album;

}
