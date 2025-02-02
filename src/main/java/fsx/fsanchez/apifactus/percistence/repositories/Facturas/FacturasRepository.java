package fsx.fsanchez.apifactus.percistence.repositories.Facturas;

import fsx.fsanchez.apifactus.percistence.models.Facturas.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FacturasRepository extends JpaRepository<Factura, Long> {

  Optional<Factura> findByBillId(Long billId);

}

