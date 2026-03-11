package com.gcd.attribution.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CandidatExternalDto {
    private Long id;
    private String cin;
    private String nom;
    private String prenom;
    private String sexe;
    private String province;
    private String centre;
    private String categorie;
    private LocalDate dateAttribution;
    private LocalDate dateNaissance;
    private String niveauEtude;
}
