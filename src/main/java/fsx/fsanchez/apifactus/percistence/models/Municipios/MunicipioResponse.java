package fsx.fsanchez.apifactus.percistence.models.Municipios;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
public class MunicipioResponse {
    private String status;
    private String message;
    @Setter
    @Getter
    private List<Municipios> data;

}
