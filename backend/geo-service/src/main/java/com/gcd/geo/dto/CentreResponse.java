package com.gcd.geo.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CentreResponse {
    Long id;
    String nom;
    Integer capaciteJournaliere;
    String adresse;
    String ville;
    Boolean actif;
}
