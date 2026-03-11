package com.gcd.candidat.service;

import com.gcd.candidat.client.GeoServiceClient;
import com.gcd.candidat.entity.Candidat;
import com.gcd.candidat.entity.CandidatGlobal;
import com.gcd.candidat.repository.CandidatGlobalRepository;
import com.gcd.candidat.repository.CandidatRepository;
import com.gcd.candidat.util.CategoryUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CandidatTransformationService {

    private static final int BATCH_SIZE = 500;

    private final CandidatGlobalRepository candidatGlobalRepository;
    private final CandidatRepository candidatRepository;
    private final GeoServiceClient geoServiceClient;

    @Transactional
    public int transform() {
        List<CandidatGlobal> globals = candidatGlobalRepository.findAll();
        List<Candidat> batch = new ArrayList<>();
        int transformed = 0;

        for (CandidatGlobal g : globals) {
            Candidat candidat = Candidat.builder()
                    .cin(CategoryUtil.clean(g.getNcin()))
                    .nom(resolveNom(g))
                    .prenom(resolvePrenom(g))
                    .sexe(CategoryUtil.clean(g.getLibfSexe()))
                    .province(resolveProvince(g))
                    .centre(geoServiceClient.resolveCentreByProvince(resolveProvince(g)))
                    .categorie(CategoryUtil.resolveCategory(
                            g.getNiveauInferieurBac(),
                            g.getTypeBacObtenu(),
                            g.getDiplomeUniversitaire(),
                            g.getDiplomeEcoleSuperieure()))
                    .dateAttribution(LocalDate.now())
                    .dateNaissance(g.getDateNaiss())
                    .niveauEtude(resolveNiveauEtude(g))
                    .build();

            batch.add(candidat);
            if (batch.size() >= BATCH_SIZE) {
                candidatRepository.saveAll(batch);
                transformed += batch.size();
                batch.clear();
            }
        }

        if (!batch.isEmpty()) {
            candidatRepository.saveAll(batch);
            transformed += batch.size();
        }

        log.info("Transformation terminée: {} lignes", transformed);
        return transformed;
    }

    private String resolveNom(CandidatGlobal g) {
        return CategoryUtil.clean(g.getNomF()) != null ? CategoryUtil.clean(g.getNomF()) : CategoryUtil.clean(g.getNomA());
    }

    private String resolvePrenom(CandidatGlobal g) {
        return CategoryUtil.clean(g.getPrenomF()) != null ? CategoryUtil.clean(g.getPrenomF()) : CategoryUtil.clean(g.getPrenomA());
    }

    private String resolveProvince(CandidatGlobal g) {
        String province = CategoryUtil.clean(g.getLibfProvinceResid());
        if (province == null) {
            province = CategoryUtil.clean(g.getLibfProvince());
        }
        return province;
    }

    private String resolveNiveauEtude(CandidatGlobal g) {
        if (CategoryUtil.clean(g.getDiplomeUniversitaire()) != null) {
            return CategoryUtil.clean(g.getDiplomeUniversitaire());
        }
        if (CategoryUtil.clean(g.getDiplomeEcoleSuperieure()) != null) {
            return CategoryUtil.clean(g.getDiplomeEcoleSuperieure());
        }
        if (CategoryUtil.clean(g.getTypeBacObtenu()) != null) {
            return CategoryUtil.clean(g.getTypeBacObtenu());
        }
        return CategoryUtil.clean(g.getNiveauInferieurBac());
    }
}
