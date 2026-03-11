package com.gcd.attribution.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class AttributionRunRequest {
    private LocalDate startDate;
    private String categorie;
    private Boolean processByCategory;
    private Boolean resetResults;
    private List<LocalDate> holidaysOverride;
}
