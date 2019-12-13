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
public class VagalumeSongResponse {

    private VagalumeSongRequest response;

}
