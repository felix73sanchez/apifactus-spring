package fsx.fsanchez.apifactus.percistence.repositories.Municipios;

import fsx.fsanchez.apifactus.percistence.models.Municipios.Municipios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MunicipiosRepository extends JpaRepository<Municipios, Long> {
    Optional<Municipios> findByCode(String code);
}

