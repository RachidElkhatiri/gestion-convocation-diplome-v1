package com.gcd.attribution.service;

import com.gcd.attribution.client.GeoServiceClient;
import com.gcd.attribution.dto.CentreCapacityDto;
import com.gcd.attribution.dto.GeoCentreResponse;
import com.gcd.attribution.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class GeoLookupService {

    private final GeoServiceClient geoServiceClient;
    private final Map<String, CentreCapacityDto> provinceCache = new HashMap<>();

    public CentreCapacityDto findCentreForProvince(String province) {
        String key = normalize(province);
        if (key == null) {
            throw new BusinessException("Province candidat manquante");
        }

        if (provinceCache.containsKey(key)) {
            return provinceCache.get(key);
        }

        try {
            GeoCentreResponse response = geoServiceClient.getCentreByProvinceName(key);
            if (response == null || response.getCentre() == null || response.getCapaciteJournaliere() == null || response.getCapaciteJournaliere() <= 0) {
                throw new BusinessException("Reponse geo invalide pour la province: " + key);
            }

            CentreCapacityDto dto = CentreCapacityDto.builder()
                    .province(response.getProvince())
                    .centre(response.getCentre())
                    .capaciteJournaliere(response.getCapaciteJournaliere())
                    .build();
            provinceCache.put(key, dto);
            return dto;
        } catch (Exception ex) {
            log.error("Erreur GeoService pour province={}: {}", key, ex.getMessage());
            throw new BusinessException("Impossible de resoudre le centre pour la province: " + key);
        }
    }

    public void clearCache() {
        provinceCache.clear();
    }

    private String normalize(String value) {
        if (value == null) {
            return null;
        }
        String cleaned = value.trim();
        return cleaned.isEmpty() ? null : cleaned;
    }
}
