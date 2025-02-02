package fsx.fsanchez.apifactus.services.Tributos;

import fsx.fsanchez.apifactus.percistence.models.Tributos.Tributos;
import fsx.fsanchez.apifactus.percistence.models.Tributos.TributoResponse;
import fsx.fsanchez.apifactus.percistence.repositories.Tributos.TributosRepository;
import fsx.fsanchez.apifactus.services.Auth.AuthServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class TributoServiceImpl implements TributoService {

    private final AuthServiceImpl authServiceImpl;
    private final TributosRepository tributosRepository;


    @Value("${api.url}")
    private String apiUrl;

    public TributoServiceImpl(AuthServiceImpl authServiceImpl, TributosRepository tributosRepository) {
        this.authServiceImpl = authServiceImpl;
        this.tributosRepository = tributosRepository;
        
    }
    @Override
    public List<Tributos> getTributos() {
        HttpHeaders headers = new HttpHeaders();
        RestTemplate restTemplate = new RestTemplate();
        headers.setBearerAuth(authServiceImpl.obtenerToken());

        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            // Solicitud GET a la API
            ResponseEntity<TributoResponse> response = restTemplate.exchange(
                    apiUrl + "/v1/tributes/products?name=", // Asegúrate de que el endpoint sea correcto
                    HttpMethod.GET,
                    entity,
                    new ParameterizedTypeReference<>() {}
            );

            // Obtener la lista de Tributos desde la respuesta
            TributoResponse tributoResponse = response.getBody();
            if (tributoResponse.getData() != null) {
                return saveIfNotExists(tributoResponse.getData()); // Guardar en BD si no existen
            } else {
                throw new RuntimeException("Error al obtener los Tributos");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al consumir la API externa: " + e.getMessage(), e);
        }
    }

    @Override
    public Optional<Tributos> buscaPorCodigo(String code){
        return tributosRepository.findByCode(code);
    }

    private List<Tributos> saveIfNotExists(List<Tributos> tributos) {
        for (Tributos tributo : tributos) {
            tributosRepository.findByCode(tributo.getCode()).orElseGet(() -> tributosRepository.save(tributo));
        }
        return tributosRepository.findAll();
    }
}
