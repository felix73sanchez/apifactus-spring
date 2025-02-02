package fsx.fsanchez.apifactus.services.Municipios;

import fsx.fsanchez.apifactus.percistence.models.Municipios.Municipios;

import java.util.List;
import java.util.Optional;

public interface MunicipioService {

    List<Municipios> getMunicipios();
    Optional<Municipios> buscaPorCodigo(String code);
}
