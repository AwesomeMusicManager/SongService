package AwesomeMusicManager.SongService.view.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class YoutubeLinkRequest {

    private String id;
    private String link;
    private String author;
    private String title;
    private String description;


}
