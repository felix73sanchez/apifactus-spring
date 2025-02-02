package fsx.fsanchez.apifactus.percistence.models.Facturas;


import lombok.Data;

@Data
public class FacturaResponse {

    private Long billId;
    private String billNumber;
    private String customerName;
    private String referenceCode;
    private String qrImage;
}
