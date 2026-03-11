package com.gcd.attribution.service;

import com.gcd.attribution.config.AttributionProperties;
import com.gcd.attribution.dto.AttributionRunRequest;
import com.gcd.attribution.dto.CandidatExternalDto;
import com.gcd.attribution.dto.CentreCapacityDto;
import com.gcd.attribution.repository.ResultatAttributionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AttributionAlgorithmServiceTest {

    @Mock
    private CandidatFetchService candidatFetchService;

    @Mock
    private GeoLookupService geoLookupService;

    @Mock
    private ResultatAttributionRepository resultatRepository;

    @InjectMocks
    private AttributionAlgorithmService service;

    @BeforeEach
    void setupProps() {
        AttributionProperties props = new AttributionProperties();
        props.setDefaultStartDateOffsetDays(1);
        props.setHolidays(List.of(LocalDate.of(2026, 1, 1)));
        service = new AttributionAlgorithmService(candidatFetchService, geoLookupService, resultatRepository, props);
    }

    @Test
    void shouldSkipWeekendAndRespectCapacity() {
        CandidatExternalDto c1 = candidat("Rabat", "CAT1", "A");
        CandidatExternalDto c2 = candidat("Rabat", "CAT1", "B");
        CandidatExternalDto c3 = candidat("Rabat", "CAT1", "C");

        when(candidatFetchService.fetchAll(null)).thenReturn(List.of(c1, c2, c3));
        when(geoLookupService.findCentreForProvince("Rabat")).thenReturn(CentreCapacityDto.builder()
                .province("Rabat")
                .centre("Centre Rabat")
                .capaciteJournaliere(2)
                .build());

        AttributionRunRequest request = new AttributionRunRequest();
        request.setStartDate(LocalDate.of(2026, 3, 13));

        service.run(request);

        ArgumentCaptor<List> captor = ArgumentCaptor.forClass(List.class);
        verify(resultatRepository, times(1)).saveAll(captor.capture());
        List saved = captor.getValue();

        assertEquals(2, saved.size());
    }

    private CandidatExternalDto candidat(String province, String categorie, String nom) {
        CandidatExternalDto dto = new CandidatExternalDto();
        dto.setProvince(province);
        dto.setCategorie(categorie);
        dto.setNom(nom);
        dto.setPrenom("Test");
        return dto;
    }
}
