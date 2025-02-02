package fsx.fsanchez.apifactus.controllers.root;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/hola")
public class BienvenidaController {

    @GetMapping()
    public ResponseEntity<String> Hello() {
        return ResponseEntity.ok("APP INICIADA!");
    }
}
