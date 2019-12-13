package AwesomeMusicManager.SongService.persistence.mongo.document;

import AwesomeMusicManager.SongService.view.model.request.YoutubeLinkRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "songs")
public class SongDocument {

    @MongoId
    private String id;

    private String singer;

    private String name;

    private String album;

    private String lyric;

    private YoutubeLinkRequest youtube;

}
