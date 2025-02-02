package fsx.fsanchez.apifactus.services.Facturas;

import fsx.fsanchez.apifactus.percistence.models.Facturas.Factura;
import fsx.fsanchez.apifactus.percistence.models.Facturas.FacturaResponse;

import java.util.Date;
import java.util.List;

public interface FacturaService {

    Factura guardaFactura(String status, String customerName, String customerIdentification,
                                 String customerEmail, Long billId, String billNumber,
                                 String referenceCode, Date createdAt, String qrImage);

    FacturaResponse buscaPorId(Long billId);

    List<FacturaResponse> buscaTodasFacturas();
}
