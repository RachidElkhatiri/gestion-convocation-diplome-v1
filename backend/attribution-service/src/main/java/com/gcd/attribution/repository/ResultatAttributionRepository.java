package com.gcd.attribution.repository;

import com.gcd.attribution.entity.ResultatAttribution;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResultatAttributionRepository extends JpaRepository<ResultatAttribution, Long> {
    Page<ResultatAttribution> findByRunIdOrderByDateConvocationAscCentreNomAscProvinceNomAsc(String runId, Pageable pageable);
}
