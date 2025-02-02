package fsx.fsanchez.apifactus.services.Medidas;

import fsx.fsanchez.apifactus.percistence.models.Medidas.Medidas;
import fsx.fsanchez.apifactus.percistence.models.Medidas.MedidasResponse;
import fsx.fsanchez.apifactus.percistence.repositories.Medidas.MedidasRepository;
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
public class MedidaServiceImpl implements MedidaService {

    private final AuthServiceImpl authServiceImpl;
    private final MedidasRepository medidasRepository;


    @Value("${api.url}")
    private String apiUrl;

    public MedidaServiceImpl(AuthServiceImpl authServiceImpl, MedidasRepository medidasRepository) {
        this.authServiceImpl = authServiceImpl;
        this.medidasRepository = medidasRepository;

        
    }

    @Override
    public List<Medidas> getMedidas() {
        HttpHeaders headers = new HttpHeaders();
        RestTemplate restTemplate = new RestTemplate();
        headers.setBearerAuth(authServiceImpl.obtenerToken());

        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {

            ResponseEntity<MedidasResponse> response = restTemplate.exchange(
                    apiUrl + "/v1/measurement-units?name=", // Asegúrate de que el endpoint sea correcto
                    HttpMethod.GET,
                    entity,
                    new ParameterizedTypeReference<>() {}
            );


            MedidasResponse medidasResponse = response.getBody();
            if (medidasResponse.getData() != null) {
                return saveIfNotExists(medidasResponse.getData());
            } else {
                throw new RuntimeException("Error al obtener los Medidas");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al consumir la API externa: " + e.getMessage(), e);
        }
    }

    @Override
    public Optional<Medidas> buscaPorId(Long id){
        return medidasRepository.findById(id);
    }

    private List<Medidas> saveIfNotExists(List<Medidas> medidas) {
        for (Medidas medida : medidas) {
            medidasRepository.findById(medida.getId()).orElseGet(() -> medidasRepository.save(medida));
        }
        return medidasRepository.findAll();
    }
}
