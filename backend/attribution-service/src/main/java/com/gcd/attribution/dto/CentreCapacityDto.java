package com.gcd.attribution.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CentreCapacityDto {
    String province;
    String centre;
    Integer capaciteJournaliere;
}
