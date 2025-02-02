package fsx.fsanchez.apifactus.percistence.models.Tributos;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;


@Getter
@Data
@Entity
@Table(name = "tributos")
public class Tributos {

    @Id
    private Long id;
    @Column(unique = true, nullable = false)
    private String code;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;

}
