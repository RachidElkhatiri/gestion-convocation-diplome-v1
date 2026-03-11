package com.gcd.candidat.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class GeoCentreResponse {
    @NotBlank
    private String centre;
}
