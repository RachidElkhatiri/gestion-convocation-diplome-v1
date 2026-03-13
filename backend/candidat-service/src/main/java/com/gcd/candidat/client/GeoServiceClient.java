package com.gcd.candidat.client;

import com.gcd.candidat.dto.GeoCentreResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
@RequiredArgsConstructor
public class GeoServiceClient {

    private final RestTemplate restTemplate;

    @Value("${app.geo.base-url}")
    private String geoBaseUrl;

    public String resolveCentreByProvince(String province) {
        if (province == null || province.isBlank()) {
            return "NON_AFFECTE";
        }

        String url = String.format("%s/api/geo/centres/by-province/%s", geoBaseUrl, province.trim());
        try {
            GeoCentreResponse response = restTemplate.getForObject(url, GeoCentreResponse.class);
            if (response == null || response.getCentre() == null || response.getCentre().isBlank()) {
                return "NON_AFFECTE";
            }
            return response.getCentre();
        } catch (Exception ex) {
            log.warn("GeoService indisponible pour province={} : {}", province, ex.getMessage());
            return "NON_AFFECTE";
        }
    }
}
