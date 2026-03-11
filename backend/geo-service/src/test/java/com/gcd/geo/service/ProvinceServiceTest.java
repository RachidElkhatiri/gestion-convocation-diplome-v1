package com.gcd.geo.service;

import com.gcd.geo.dto.ProvinceRequest;
import com.gcd.geo.entity.Centre;
import com.gcd.geo.exception.ResourceNotFoundException;
import com.gcd.geo.mapper.GeoMapper;
import com.gcd.geo.repository.ProvinceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProvinceServiceTest {

    @Mock
    private ProvinceRepository provinceRepository;

    @Mock
    private CentreService centreService;

    @Mock
    private GeoMapper geoMapper;

    @InjectMocks
    private ProvinceService provinceService;

    @Test
    void shouldRejectCreateWhenCentreDoesNotExist() {
        ProvinceRequest request = new ProvinceRequest();
        request.setNom("Kenitra");
        request.setCentreId(99L);

        when(provinceRepository.existsByNomIgnoreCase("Kenitra")).thenReturn(false);
        when(centreService.findEntityById(99L)).thenThrow(new ResourceNotFoundException("Centre introuvable"));

        assertThrows(ResourceNotFoundException.class, () -> provinceService.create(request));
    }

    @Test
    void shouldRejectFindByCentreWhenCentreMissing() {
        when(centreService.findEntityById(77L)).thenThrow(new ResourceNotFoundException("Centre introuvable"));
        assertThrows(ResourceNotFoundException.class, () -> provinceService.findByCentre(77L));
    }
}
