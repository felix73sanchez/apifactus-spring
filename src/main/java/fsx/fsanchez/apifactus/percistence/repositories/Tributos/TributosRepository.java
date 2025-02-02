package fsx.fsanchez.apifactus.percistence.repositories.Tributos;

import fsx.fsanchez.apifactus.percistence.models.Tributos.Tributos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TributosRepository extends JpaRepository<Tributos, Long> {
    Optional<Tributos> findByCode(String code);
}

