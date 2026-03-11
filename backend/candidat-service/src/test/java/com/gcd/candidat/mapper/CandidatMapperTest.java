package com.gcd.candidat.mapper;

import com.gcd.candidat.entity.Candidat;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CandidatMapperTest {

    private final CandidatMapper mapper = new CandidatMapper();

    @Test
    void shouldMapEntityToResponse() {
        Candidat entity = Candidat.builder()
                .id(1L)
                .cin("AB123")
                .nom("EL AMRANI")
                .prenom("Yassine")
                .sexe("M")
                .province("RABAT")
                .centre("CENTRE-1")
                .categorie("CAT2")
                .dateAttribution(LocalDate.of(2026, 3, 11))
                .dateNaissance(LocalDate.of(2000, 1, 1))
                .niveauEtude("Bac")
                .build();

        var dto = mapper.toResponse(entity);

        assertEquals(entity.getId(), dto.getId());
        assertEquals(entity.getCin(), dto.getCin());
        assertEquals(entity.getCategorie(), dto.getCategorie());
    }
}
