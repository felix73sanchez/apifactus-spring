
package fsx.fsanchez.apifactus.services.Auth;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.time.Instant;

@Service
public class AuthServiceImpl implements AuthService {

    private String accessToken;
    private String refreshToken;
    private Instant tokenExpiration;

    @Value("${api.url}")
    private String apiurl;

    @Value("${api.id}")
    private String clientId;

    @Value("${api.secret}")
    private String clientSecret;

    @Value("${api.user}")
    private String username;

    @Value("${api.pass}")
    private String password;

    @Override
    public String obtenerToken() {
        if (accessToken == null || Instant.now().isAfter(tokenExpiration)) {
            if (refreshToken != null) {
                boolean refreshed = renovarTokenConRefresh();
                if (!refreshed) {
                    renovarToken(); // Si el refreshToken falla, hace login completo
                }
            } else {
                renovarToken(); // Si no hay refreshToken, hace login completo
            }
        }
        return accessToken;
    }

    private void renovarToken() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        String requestBody = "grant_type=password&client_id=" + clientId +
                "&client_secret=" + clientSecret +
                "&username=" + username +
                "&password=" + password;

        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<AuthResponse> response = restTemplate.exchange(apiurl + "/oauth/token", HttpMethod.POST, entity, AuthResponse.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            response.getBody();
            actualizarTokens(response.getBody());
        }
    }

    private boolean renovarTokenConRefresh() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        String requestBody = "grant_type=refresh_token&client_id=" + clientId +
                "&client_secret=" + clientSecret +
                "&refresh_token=" + refreshToken;

        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<AuthResponse> response = restTemplate.exchange(apiurl + "/oauth/token", HttpMethod.POST, entity, AuthResponse.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            response.getBody();
            actualizarTokens(response.getBody());
            return true;
        }

        return false;
    }

    private void actualizarTokens(AuthResponse response) {
        this.accessToken = response.getAccess_token();
        this.refreshToken = response.getRefresh_token();
        this.tokenExpiration = Instant.now().plusSeconds(response.getExpires_in() - 60); // Margen de seguridad de 1 min
    }

    @Getter
    private static class AuthResponse {
        private String access_token;
        private String refresh_token;
        private long expires_in;
    }
}
