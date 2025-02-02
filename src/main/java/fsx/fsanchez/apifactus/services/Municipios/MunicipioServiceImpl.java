package fsx.fsanchez.apifactus.services.Municipios;


import fsx.fsanchez.apifactus.percistence.models.Municipios.MunicipioResponse;
import fsx.fsanchez.apifactus.percistence.models.Municipios.Municipios;
import fsx.fsanchez.apifactus.percistence.repositories.Municipios.MunicipiosRepository;
import fsx.fsanchez.apifactus.services.Auth.AuthServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class MunicipioServiceImpl implements MunicipioService {

    private final AuthServiceImpl authServiceImpl;
    private final MunicipiosRepository municipioRepository;


    @Value("${api.url}")
    private String apiUrl;

    public MunicipioServiceImpl(AuthServiceImpl authServiceImpl, MunicipiosRepository municipioRepository) {
        this.authServiceImpl = authServiceImpl;
        this.municipioRepository = municipioRepository;
    }

    @Override
    public List<Municipios> getMunicipios() {
        HttpHeaders headers = new HttpHeaders();
        RestTemplate restTemplate = new RestTemplate();
        headers.setBearerAuth(authServiceImpl.obtenerToken());

        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            // Solicitud GET a la API
            ResponseEntity<MunicipioResponse> response = restTemplate.exchange(
                    apiUrl + "/v1/municipalities?name=", // Asegúrate de que el endpoint sea correcto
                    HttpMethod.GET,
                    entity,
                    new ParameterizedTypeReference<>() {}
            );

            // Obtener la lista de municipios desde la respuesta
            MunicipioResponse municipioResponse = response.getBody();
            if (municipioResponse.getData() != null) {
                return saveIfNotExists(municipioResponse.getData()); // Guardar en BD si no existen
            } else {
                throw new RuntimeException("Error al obtener los municipios");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al consumir la API externa: " + e.getMessage(), e);
        }
    }

    @Override
    public Optional<Municipios> buscaPorCodigo(String code){
        return municipioRepository.findByCode(code);
    }

    private List<Municipios> saveIfNotExists(List<Municipios> municipios) {
        for (Municipios municipio : municipios) {
            municipioRepository.findByCode(municipio.getCode()).orElseGet(() -> municipioRepository.save(municipio));
        }
        return municipioRepository.findAll();
    }
}
