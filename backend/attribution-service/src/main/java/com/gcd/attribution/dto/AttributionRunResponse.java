package com.gcd.attribution.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class AttributionRunResponse {
    String runId;
    int totalCandidats;
    int totalLignesResultat;
    String mode;
}
