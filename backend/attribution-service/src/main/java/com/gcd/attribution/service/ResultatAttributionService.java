package com.gcd.attribution.service;

import com.gcd.attribution.dto.ResultatAttributionResponse;
import com.gcd.attribution.entity.ResultatAttribution;
import com.gcd.attribution.exception.ResourceNotFoundException;
import com.gcd.attribution.repository.ResultatAttributionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ResultatAttributionService {

    private final ResultatAttributionRepository repository;

    public Page<ResultatAttributionResponse> findAll(String runId, Pageable pageable) {
        Page<ResultatAttribution> page;
        if (runId == null || runId.isBlank()) {
            page = repository.findAll(pageable);
        } else {
            page = repository.findByRunIdOrderByDateConvocationAscCentreNomAscProvinceNomAsc(runId, pageable);
        }
        return page.map(this::toResponse);
    }

    public ResultatAttributionResponse findById(Long id) {
        ResultatAttribution entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resultat introuvable: " + id));
        return toResponse(entity);
    }

    private ResultatAttributionResponse toResponse(ResultatAttribution entity) {
        return ResultatAttributionResponse.builder()
                .id(entity.getId())
                .runId(entity.getRunId())
                .centreNom(entity.getCentreNom())
                .provinceNom(entity.getProvinceNom())
                .dateConvocation(entity.getDateConvocation())
                .nombreCandidats(entity.getNombreCandidats())
                .categorie(entity.getCategorie())
                .statut(entity.getStatut())
                .metadata(entity.getMetadata())
                .createdAt(entity.getCreatedAt())
                .build();
    }
}
