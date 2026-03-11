package com.gcd.geo.mapper;

import com.gcd.geo.dto.CentreResponse;
import com.gcd.geo.dto.ProvinceResponse;
import com.gcd.geo.entity.Centre;
import com.gcd.geo.entity.Province;
import org.springframework.stereotype.Component;

@Component
public class GeoMapper {

    public CentreResponse toCentreResponse(Centre centre) {
        return CentreResponse.builder()
                .id(centre.getId())
                .nom(centre.getNom())
                .capaciteJournaliere(centre.getCapaciteJournaliere())
                .adresse(centre.getAdresse())
                .ville(centre.getVille())
                .actif(centre.getActif())
                .build();
    }

    public ProvinceResponse toProvinceResponse(Province province) {
        return ProvinceResponse.builder()
                .id(province.getId())
                .nom(province.getNom())
                .code(province.getCode())
                .actif(province.getActif())
                .centreId(province.getCentre().getId())
                .centreNom(province.getCentre().getNom())
                .centreCapaciteJournaliere(province.getCentre().getCapaciteJournaliere())
                .build();
    }
}
