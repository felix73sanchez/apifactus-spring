package fsx.fsanchez.apifactus.percistence.models.Facturas;


import lombok.Data;

@Data
public class FacturaRequest {
    private String status;
    private String customerName;
    private String customerIdentification;
    private String customerEmail;
    private Long billId;
    private String billNumber;
    private String referenceCode;
    private String createdAt;
    private String qrImage;
}
