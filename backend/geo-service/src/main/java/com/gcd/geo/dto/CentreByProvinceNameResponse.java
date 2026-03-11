package com.gcd.geo.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CentreByProvinceNameResponse {
    String province;
    String centre;
    Integer capaciteJournaliere;
}
