package fsx.fsanchez.apifactus.controllers.Facturas;


import fsx.fsanchez.apifactus.percistence.models.Facturas.Factura;
import fsx.fsanchez.apifactus.percistence.models.Facturas.FacturaRequest;
import fsx.fsanchez.apifactus.percistence.models.Facturas.FacturaResponse;
import fsx.fsanchez.apifactus.services.Facturas.FacturaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/v1/facturas")
public class FacturaController {

    private final FacturaService facturaService;

    public FacturaController(FacturaService facturaService) {
        this.facturaService = facturaService;
    }

    @GetMapping("/{billId}")
    public ResponseEntity<FacturaResponse> getFacturaResponse(@PathVariable Long billId) {
        FacturaResponse response = facturaService.buscaPorId(billId);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<FacturaResponse>> getAllFacturas() {
        List<FacturaResponse> facturas = facturaService.buscaTodasFacturas();
        return ResponseEntity.ok(facturas);
    }

    @PostMapping("/guardar")
    public ResponseEntity<Factura> persistInvoice(@RequestBody FacturaRequest request) throws ParseException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date createdAtDate = dateFormat.parse(request.getCreatedAt());


        Factura facturaGuardada = facturaService.guardaFactura(
                request.getStatus(),
                request.getCustomerName(),
                request.getCustomerIdentification(),
                request.getCustomerEmail(),
                request.getBillId(),
                request.getBillNumber(),
                request.getReferenceCode(),
                createdAtDate,
                request.getQrImage()
        );

        return ResponseEntity.ok(facturaGuardada);
    }

}