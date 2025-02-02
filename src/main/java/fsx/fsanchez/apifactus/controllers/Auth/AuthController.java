package fsx.fsanchez.apifactus.controllers.Auth;

import fsx.fsanchez.apifactus.services.Auth.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;

    }

    @GetMapping("/token")
    public ResponseEntity<String> obtenerToken() {
        String token = authService.obtenerToken();
        return ResponseEntity.ok(token);
    }
}