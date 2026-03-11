package com.gcd.geo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProvinceRequest {

    @NotBlank
    private String nom;

    private String code;

    @NotNull
    private Long centreId;

    private Boolean actif;
}
