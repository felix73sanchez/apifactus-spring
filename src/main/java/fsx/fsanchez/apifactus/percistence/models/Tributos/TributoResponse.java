package fsx.fsanchez.apifactus.percistence.models.Tributos;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
public class TributoResponse {
    private String status;
    private String message;
    @Setter
    @Getter
    private List<Tributos> data;

}
