package fsx.fsanchez.apifactus.controllers.Tributos;

import fsx.fsanchez.apifactus.percistence.models.Tributos.Tributos;
import fsx.fsanchez.apifactus.services.Tributos.TributoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/tributos")
public class TributosController {

    private final TributoService tributosService;

    public TributosController(TributoService tributosService) {

        this.tributosService = tributosService;
    }

    @GetMapping("/todos")
    public ResponseEntity<List<Tributos>> getTributos() {
        List<Tributos> tributos = tributosService.getTributos();
        return ResponseEntity.ok(tributos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Tributos>> obtenerTributo(@PathVariable String id) {
        Optional<Tributos> tributo = tributosService.buscaPorCodigo(id);
        if (tributo.isPresent()) {
            return ResponseEntity.ok(tributo);
        }else {
            return ResponseEntity.notFound().build();
        }
    }
}
