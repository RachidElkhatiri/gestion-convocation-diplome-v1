package com.gcd.geo.service;

import com.gcd.geo.dto.CentreRequest;
import com.gcd.geo.dto.CentreResponse;
import com.gcd.geo.dto.CentreByProvinceNameResponse;
import com.gcd.geo.dto.ProvinceCentreResponse;
import com.gcd.geo.entity.Centre;
import com.gcd.geo.entity.Province;
import com.gcd.geo.exception.BusinessException;
import com.gcd.geo.exception.ResourceNotFoundException;
import com.gcd.geo.mapper.GeoMapper;
import com.gcd.geo.repository.CentreRepository;
import com.gcd.geo.repository.ProvinceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CentreService {

    private final CentreRepository centreRepository;
    private final ProvinceRepository provinceRepository;
    private final GeoMapper geoMapper;

    @Transactional
    public CentreResponse create(CentreRequest request) {
        String nom = normalize(request.getNom());
        if (centreRepository.existsByNomIgnoreCase(nom)) {
            throw new BusinessException("Un centre avec ce nom existe déjà: " + nom);
        }

        Centre entity = Centre.builder()
                .nom(nom)
                .capaciteJournaliere(request.getCapaciteJournaliere())
                .adresse(normalize(request.getAdresse()))
                .ville(normalize(request.getVille()))
                .actif(request.getActif() == null || request.getActif())
                .build();

        Centre saved = centreRepository.save(entity);
        log.info("Centre créé: id={}, nom={}", saved.getId(), saved.getNom());
        return geoMapper.toCentreResponse(saved);
    }

    @Transactional(readOnly = true)
    public List<CentreResponse> findAll() {
        return centreRepository.findAll().stream().map(geoMapper::toCentreResponse).toList();
    }

    @Transactional(readOnly = true)
    public CentreResponse findById(Long id) {
        Centre centre = findEntityById(id);
        return geoMapper.toCentreResponse(centre);
    }

    @Transactional
    public CentreResponse update(Long id, CentreRequest request) {
        Centre centre = findEntityById(id);
        String nom = normalize(request.getNom());

        centreRepository.findByNomIgnoreCase(nom)
                .filter(existing -> !existing.getId().equals(id))
                .ifPresent(existing -> {
                    throw new BusinessException("Un autre centre avec ce nom existe déjà: " + nom);
                });

        centre.setNom(nom);
        centre.setCapaciteJournaliere(request.getCapaciteJournaliere());
        centre.setAdresse(normalize(request.getAdresse()));
        centre.setVille(normalize(request.getVille()));
        centre.setActif(request.getActif() == null || request.getActif());

        Centre saved = centreRepository.save(centre);
        log.info("Centre mis à jour: id={}, nom={}", saved.getId(), saved.getNom());
        return geoMapper.toCentreResponse(saved);
    }

    @Transactional
    public void delete(Long id) {
        Centre centre = findEntityById(id);
        if (provinceRepository.existsByCentreId(id)) {
            throw new BusinessException("Suppression refusée: ce centre contient des provinces");
        }
        centreRepository.delete(centre);
        log.info("Centre supprimé: id={}", id);
    }

    @Transactional(readOnly = true)
    public ProvinceCentreResponse getCentreByProvinceId(Long provinceId) {
        Province province = provinceRepository.findById(provinceId)
                .orElseThrow(() -> new ResourceNotFoundException("Province introuvable: " + provinceId));

        return ProvinceCentreResponse.builder()
                .provinceId(province.getId())
                .provinceNom(province.getNom())
                .centreId(province.getCentre().getId())
                .centreNom(province.getCentre().getNom())
                .capaciteJournaliere(province.getCentre().getCapaciteJournaliere())
                .build();
    }

    @Transactional(readOnly = true)
    public CentreByProvinceNameResponse getCentreByProvinceName(String provinceName) {
        Province province = provinceRepository.findByNomIgnoreCase(normalize(provinceName))
                .orElseThrow(() -> new ResourceNotFoundException("Province introuvable: " + provinceName));

        return CentreByProvinceNameResponse.builder()
                .province(province.getNom())
                .centre(province.getCentre().getNom())
                .capaciteJournaliere(province.getCentre().getCapaciteJournaliere())
                .build();
    }

    @Transactional(readOnly = true)
    public Centre findEntityById(Long id) {
        return centreRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Centre introuvable: " + id));
    }

    private String normalize(String value) {
        if (value == null) {
            return null;
        }
        String cleaned = value.trim();
        return cleaned.isEmpty() ? null : cleaned;
    }
}
