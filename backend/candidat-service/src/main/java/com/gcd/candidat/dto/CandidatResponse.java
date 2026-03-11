package com.gcd.candidat.dto;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class CandidatResponse {
    Long id;
    String cin;
    String nom;
    String prenom;
    String sexe;
    String province;
    String centre;
    String categorie;
    LocalDate dateAttribution;
    LocalDate dateNaissance;
    String niveauEtude;
}
