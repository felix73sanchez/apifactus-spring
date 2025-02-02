package fsx.fsanchez.apifactus.services.Tributos;

import fsx.fsanchez.apifactus.percistence.models.Tributos.Tributos;

import java.util.List;
import java.util.Optional;

public interface TributoService {

    List<Tributos> getTributos();
    Optional<Tributos> buscaPorCodigo(String code);
}
