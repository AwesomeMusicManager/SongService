package AwesomeMusicManager.SongService.domain.model;

import AwesomeMusicManager.SongService.view.model.request.YoutubeLinkRequest;
import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Song {

    @Id
    private String id;

    @NotNull
    private String singer;

    @NotNull
    private String name;

    private String album;

    private YoutubeLinkRequest youtube;

    private String lyric;

}
