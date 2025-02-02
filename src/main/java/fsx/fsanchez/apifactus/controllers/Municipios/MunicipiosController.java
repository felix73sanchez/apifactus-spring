package fsx.fsanchez.apifactus.controllers.Municipios;

import fsx.fsanchez.apifactus.percistence.models.Municipios.Municipios;
import fsx.fsanchez.apifactus.services.Municipios.MunicipioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/municipios")
public class MunicipiosController {

    private final MunicipioService municipioService;

    public MunicipiosController(MunicipioService municipioService) {
        this.municipioService = municipioService;

    }

    @GetMapping("/todos")
    public ResponseEntity<List<Municipios>> obtenerMunicipios() {
        List<Municipios> municipios = municipioService.getMunicipios();
        return ResponseEntity.ok(municipios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Municipios>> obtenerMunicipio(@PathVariable String id) {
        Optional<Municipios> municipio = municipioService.buscaPorCodigo(id);
        if (municipio.isPresent()) {
            return ResponseEntity.ok(municipio);
        }else {
            return ResponseEntity.notFound().build();
        }
    }
}
