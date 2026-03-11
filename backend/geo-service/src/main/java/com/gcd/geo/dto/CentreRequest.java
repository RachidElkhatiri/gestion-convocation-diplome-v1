package com.gcd.geo.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CentreRequest {

    @NotBlank
    private String nom;

    @NotNull
    @Min(1)
    private Integer capaciteJournaliere;

    private String adresse;

    private String ville;

    private Boolean actif;
}
