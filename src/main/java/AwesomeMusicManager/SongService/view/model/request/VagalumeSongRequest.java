package AwesomeMusicManager.SongService.view.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VagalumeSongRequest {

    private String type;
    private List<VagalumeSongRequestMusic> mus;
    private VagalumeSongRequestArtist art;

}
