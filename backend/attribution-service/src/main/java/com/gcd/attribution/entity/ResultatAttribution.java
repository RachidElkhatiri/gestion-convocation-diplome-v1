package com.gcd.attribution.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "RESULTATS_ATTRIBUTION")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResultatAttribution {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "resultats_attr_seq_gen")
    @SequenceGenerator(name = "resultats_attr_seq_gen", sequenceName = "RESULTATS_ATTR_SEQ", allocationSize = 1)
    @Column(name = "ID")
    private Long id;

    @Column(name = "RUN_ID", nullable = false, length = 80)
    private String runId;

    @Column(name = "CENTRE_NOM", nullable = false, length = 250)
    private String centreNom;

    @Column(name = "PROVINCE_NOM", nullable = false, length = 250)
    private String provinceNom;

    @Column(name = "DATE_CONVOCATION", nullable = false)
    private LocalDate dateConvocation;

    @Column(name = "NOMBRE_CANDIDATS", nullable = false)
    private Integer nombreCandidats;

    @Column(name = "CATEGORIE", length = 30)
    private String categorie;

    @Column(name = "STATUT", nullable = false, length = 40)
    private String statut;

    @Lob
    @Column(name = "METADATA")
    private String metadata;

    @Column(name = "CREATED_AT", nullable = false)
    private LocalDateTime createdAt;
}
