package fsx.fsanchez.apifactus.services.NumerosDeRangos;

import fsx.fsanchez.apifactus.dto.RangosDto.NumberingRangeDto;
import fsx.fsanchez.apifactus.dto.RangosDto.NumberingRangeResponse;
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

@Service
public class NumberingRangeServiceImpl implements NumberingRangeService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final AuthServiceImpl authServiceImpl;

    @Value("${api.url}")
    private String apiUrl;

    public NumberingRangeServiceImpl(AuthServiceImpl authServiceImpl) {
        this.authServiceImpl = authServiceImpl;
    }

    @Override
    public List<NumberingRangeDto> getNumberingRanges() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(authServiceImpl.obtenerToken());
        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity<NumberingRangeResponse> response = restTemplate.exchange(
                    apiUrl + "/v1/numbering-ranges",
                    HttpMethod.GET,
                    entity,
                    new ParameterizedTypeReference<>() {}
            );

            response.getBody();
            return response.getBody().getData();
        } catch (Exception e) {
            throw new RuntimeException("Error al consumir la API externa: " + e.getMessage(), e);
        }
    }
}
