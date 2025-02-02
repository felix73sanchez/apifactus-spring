package fsx.fsanchez.apifactus.controllers.Medidas;
import fsx.fsanchez.apifactus.percistence.models.Medidas.Medidas;
import fsx.fsanchez.apifactus.services.Medidas.MedidaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/medidas")
public class MedidasController {

    private final MedidaService medidaService;

    public MedidasController(MedidaService medidaService) {
        this.medidaService = medidaService;
    }

    @GetMapping("/todos")
    public ResponseEntity<List<Medidas>> getMedidas() {
        List<Medidas> medidas = medidaService.getMedidas();
        return ResponseEntity.ok(medidas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Medidas>> obtenerMedida(@PathVariable Long id) {
        Optional<Medidas> medida = medidaService.buscaPorId(id);
        if (medida.isPresent()) {
            return ResponseEntity.ok(medida);
        }else {
            return ResponseEntity.notFound().build();
        }
    }
}
