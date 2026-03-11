package com.gcd.geo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "PROVINCES", uniqueConstraints = {
        @UniqueConstraint(name = "UK_PROVINCES_NOM", columnNames = "NOM")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Province {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "provinces_seq_gen")
    @SequenceGenerator(name = "provinces_seq_gen", sequenceName = "PROVINCES_SEQ", allocationSize = 1)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NOM", nullable = false, length = 200)
    private String nom;

    @Column(name = "CODE", length = 50)
    private String code;

    @Column(name = "ACTIF", nullable = false)
    private Boolean actif;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CENTRE_ID", nullable = false,
            foreignKey = @ForeignKey(name = "FK_PROVINCE_CENTRE"))
    private Centre centre;
}
