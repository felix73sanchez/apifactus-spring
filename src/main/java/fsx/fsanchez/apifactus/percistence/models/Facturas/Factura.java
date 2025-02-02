package fsx.fsanchez.apifactus.percistence.models.Facturas;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "invoices")
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "customer_name", nullable = false)
    private String customerName;

    @Column(name = "customer_identification", nullable = false)
    private String customerIdentification;

    @Column(name = "customer_email", nullable = false)
    private String customerEmail;

    @Column(name = "bill_id", nullable = false)
    private Long billId;

    @Column(name = "bill_number", nullable = false)
    private String billNumber;

    @Column(name = "reference_code", nullable = false)
    private String referenceCode;

    @Column(name = "created_at", nullable = false)
    private Date createdAt;

    @Column(name = "qr_image", columnDefinition = "TEXT", nullable = false)
    private String qrImage;
}
