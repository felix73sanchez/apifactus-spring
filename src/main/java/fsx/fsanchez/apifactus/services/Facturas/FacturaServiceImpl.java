package fsx.fsanchez.apifactus.services.Facturas;

import fsx.fsanchez.apifactus.percistence.models.Facturas.Factura;
import fsx.fsanchez.apifactus.percistence.models.Facturas.FacturaResponse;
import fsx.fsanchez.apifactus.percistence.repositories.Facturas.FacturasRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FacturaServiceImpl implements FacturaService{

    private final FacturasRepository facturasRepository;

    public FacturaServiceImpl(FacturasRepository facturasRepository) {
        this.facturasRepository = facturasRepository;
    }

    @CacheEvict(value = {"facturas", "factura"}, allEntries = true) // Invalida cache al guardar
    public Factura guardaFactura(String status, String customerName, String customerIdentification,
                                 String customerEmail, Long billId, String billNumber,
                                 String referenceCode, Date createdAt, String qrImage) {
        Factura factura = new Factura();
        factura.setStatus(status);
        factura.setCustomerName(customerName);
        factura.setCustomerIdentification(customerIdentification);
        factura.setCustomerEmail(customerEmail);
        factura.setBillId(billId);
        factura.setBillNumber(billNumber);
        factura.setReferenceCode(referenceCode);
        factura.setCreatedAt(createdAt);
        factura.setQrImage(qrImage);

        return facturasRepository.save(factura);
    }

    @Cacheable(value = "factura", key = "#billId")
    public FacturaResponse buscaPorId(Long billId) {
        Optional<Factura> facturaOptional = facturasRepository.findByBillId(billId);

        if (facturaOptional.isPresent()) {
            Factura factura = facturaOptional.get();
            return dtoFacturas(factura);
        } else {
            throw new RuntimeException("Factura no encontrada con el ID: " + billId);
        }
    }

    @Cacheable(value = "facturas")
    public List<FacturaResponse> buscaTodasFacturas() {
        List<Factura> facturas = facturasRepository.findAll();
        return facturas.stream()
                .map(this::dtoFacturas)
                .collect(Collectors.toList());
    }

    private FacturaResponse dtoFacturas(Factura factura) {
        FacturaResponse response = new FacturaResponse();
        response.setBillId(factura.getBillId());
        response.setBillNumber(factura.getBillNumber());
        response.setCustomerName(factura.getCustomerName());
        response.setReferenceCode(factura.getReferenceCode());
        response.setQrImage(factura.getQrImage());

        return response;
    }
}
