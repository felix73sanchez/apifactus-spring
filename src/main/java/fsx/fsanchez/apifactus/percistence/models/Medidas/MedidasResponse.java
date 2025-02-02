package fsx.fsanchez.apifactus.percistence.models.Medidas;

import lombok.Data;


import java.util.List;

@Data
public class MedidasResponse {

    private String status;
    private String message;
    private List<Medidas> data;

}
