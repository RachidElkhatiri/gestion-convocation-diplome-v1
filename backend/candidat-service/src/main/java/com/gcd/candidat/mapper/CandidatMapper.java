package com.gcd.candidat.mapper;

import com.gcd.candidat.dto.CandidatResponse;
import com.gcd.candidat.entity.Candidat;
import org.springframework.stereotype.Component;

@Component
public class CandidatMapper {

    public CandidatResponse toResponse(Candidat candidat) {
        return CandidatResponse.builder()
                .id(candidat.getId())
                .cin(candidat.getCin())
                .nom(candidat.getNom())
                .prenom(candidat.getPrenom())
                .sexe(candidat.getSexe())
                .province(candidat.getProvince())
                .centre(candidat.getCentre())
                .categorie(candidat.getCategorie())
                .dateAttribution(candidat.getDateAttribution())
                .dateNaissance(candidat.getDateNaissance())
                .niveauEtude(candidat.getNiveauEtude())
                .build();
    }
}
