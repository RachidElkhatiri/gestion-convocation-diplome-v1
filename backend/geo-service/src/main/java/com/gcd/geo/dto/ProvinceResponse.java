package com.gcd.geo.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ProvinceResponse {
    Long id;
    String nom;
    String code;
    Boolean actif;
    Long centreId;
    String centreNom;
    Integer centreCapaciteJournaliere;
}
