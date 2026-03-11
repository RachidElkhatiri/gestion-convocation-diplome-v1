package com.gcd.geo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "CENTRES", uniqueConstraints = {
        @UniqueConstraint(name = "UK_CENTRES_NOM", columnNames = "NOM")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Centre {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "centres_seq_gen")
    @SequenceGenerator(name = "centres_seq_gen", sequenceName = "CENTRES_SEQ", allocationSize = 1)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NOM", nullable = false, length = 200)
    private String nom;

    @Column(name = "CAPACITE_JOURNALIERE", nullable = false)
    private Integer capaciteJournaliere;

    @Column(name = "ADRESSE", length = 500)
    private String adresse;

    @Column(name = "VILLE", length = 120)
    private String ville;

    @Column(name = "ACTIF", nullable = false)
    private Boolean actif;

    @OneToMany(mappedBy = "centre", cascade = CascadeType.ALL, orphanRemoval = false)
    @Builder.Default
    private List<Province> provinces = new ArrayList<>();
}
