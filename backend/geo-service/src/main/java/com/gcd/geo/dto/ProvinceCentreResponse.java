package com.gcd.geo.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ProvinceCentreResponse {
    Long provinceId;
    String provinceNom;
    Long centreId;
    String centreNom;
    Integer capaciteJournaliere;
}
