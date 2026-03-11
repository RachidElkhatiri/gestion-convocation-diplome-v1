package com.gcd.candidat.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "CANDIDATS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Candidat {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "candidats_seq_gen")
    @SequenceGenerator(name = "candidats_seq_gen", sequenceName = "CANDIDATS_SEQ", allocationSize = 1)
    @Column(name = "ID")
    private Long id;

    @Column(name = "CIN", length = 50)
    private String cin;

    @Column(name = "NOM", length = 200)
    private String nom;

    @Column(name = "PRENOM", length = 200)
    private String prenom;

    @Column(name = "SEXE", length = 20)
    private String sexe;

    @Column(name = "PROVINCE", length = 200)
    private String province;

    @Column(name = "CENTRE", length = 200)
    private String centre;

    @Column(name = "CATEGORIE", length = 20)
    private String categorie;

    @Column(name = "DATE_ATTRIBUTION")
    private LocalDate dateAttribution;

    @Column(name = "DATE_NAISSANCE")
    private LocalDate dateNaissance;

    @Column(name = "NIVEAU_ETUDE", length = 200)
    private String niveauEtude;
}
