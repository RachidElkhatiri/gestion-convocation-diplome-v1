package com.gcd.attribution.dto;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Value
@Builder
public class ResultatAttributionResponse {
    Long id;
    String runId;
    String centreNom;
    String provinceNom;
    LocalDate dateConvocation;
    Integer nombreCandidats;
    String categorie;
    String statut;
    String metadata;
    LocalDateTime createdAt;
}
