package com.gcd.geo.service;

import com.gcd.geo.dto.CentreRequest;
import com.gcd.geo.entity.Centre;
import com.gcd.geo.exception.BusinessException;
import com.gcd.geo.mapper.GeoMapper;
import com.gcd.geo.repository.CentreRepository;
import com.gcd.geo.repository.ProvinceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CentreServiceTest {

    @Mock
    private CentreRepository centreRepository;

    @Mock
    private ProvinceRepository provinceRepository;

    @Mock
    private GeoMapper geoMapper;

    @InjectMocks
    private CentreService centreService;

    @Test
    void shouldRejectCreateWhenNameAlreadyExists() {
        CentreRequest request = new CentreRequest();
        request.setNom("Centre Rabat");
        request.setCapaciteJournaliere(100);

        when(centreRepository.existsByNomIgnoreCase("Centre Rabat")).thenReturn(true);

        assertThrows(BusinessException.class, () -> centreService.create(request));
    }

    @Test
    void shouldRejectUpdateWhenNameBelongsToAnotherCentre() {
        CentreRequest request = new CentreRequest();
        request.setNom("Centre Casa");
        request.setCapaciteJournaliere(100);

        Centre existing = Centre.builder().id(2L).nom("Centre Casa").capaciteJournaliere(150).actif(true).build();
        Centre current = Centre.builder().id(1L).nom("Centre Rabat").capaciteJournaliere(100).actif(true).build();

        when(centreRepository.findById(1L)).thenReturn(Optional.of(current));
        when(centreRepository.findByNomIgnoreCase("Centre Casa")).thenReturn(Optional.of(existing));

        assertThrows(BusinessException.class, () -> centreService.update(1L, request));
    }
}
