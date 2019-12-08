package AwesomeMusicManager.SongService.domain.model;

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

    @NotNull
    private String album;

}
