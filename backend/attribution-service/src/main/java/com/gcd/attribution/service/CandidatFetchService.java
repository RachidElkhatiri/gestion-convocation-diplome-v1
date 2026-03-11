package com.gcd.attribution.service;

import com.gcd.attribution.client.CandidatServiceClient;
import com.gcd.attribution.dto.CandidatExternalDto;
import com.gcd.attribution.dto.PageResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CandidatFetchService {

    private static final int PAGE_SIZE = 300;

    private final CandidatServiceClient candidatServiceClient;

    public List<CandidatExternalDto> fetchAll(String categorie) {
        int page = 0;
        List<CandidatExternalDto> aggregated = new ArrayList<>();

        while (true) {
            PageResponse<CandidatExternalDto> response;
            if (categorie == null || categorie.isBlank()) {
                response = candidatServiceClient.getCandidats(page, PAGE_SIZE, "id,asc");
            } else {
                response = candidatServiceClient.getCandidatsFiltered(
                        null, null, null, categorie, null, null, null,
                        page, PAGE_SIZE, "id,asc");
            }

            if (response.getContent() == null || response.getContent().isEmpty()) {
                break;
            }

            aggregated.addAll(response.getContent());
            if (response.isLast() || page + 1 >= response.getTotalPages()) {
                break;
            }
            page++;
        }

        log.info("Candidats recuperes: {} (categorie={})", aggregated.size(), categorie);
        return aggregated;
    }
}
