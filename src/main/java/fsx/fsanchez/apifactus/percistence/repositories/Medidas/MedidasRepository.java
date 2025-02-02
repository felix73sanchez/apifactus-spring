package fsx.fsanchez.apifactus.percistence.repositories.Medidas;

import fsx.fsanchez.apifactus.percistence.models.Medidas.Medidas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MedidasRepository extends JpaRepository<Medidas, Long> {
    Optional<Medidas> findById(Long id);
}

