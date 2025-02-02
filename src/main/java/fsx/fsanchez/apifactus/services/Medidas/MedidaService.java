package fsx.fsanchez.apifactus.services.Medidas;

import fsx.fsanchez.apifactus.percistence.models.Medidas.Medidas;

import java.util.List;
import java.util.Optional;

public interface MedidaService {

    List<Medidas> getMedidas();
    Optional<Medidas> buscaPorId(Long id);
}
