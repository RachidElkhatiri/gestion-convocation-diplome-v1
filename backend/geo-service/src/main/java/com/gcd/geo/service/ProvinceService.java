package com.gcd.geo.service;

import com.gcd.geo.dto.ProvinceRequest;
import com.gcd.geo.dto.ProvinceResponse;
import com.gcd.geo.entity.Centre;
import com.gcd.geo.entity.Province;
import com.gcd.geo.exception.BusinessException;
import com.gcd.geo.exception.ResourceNotFoundException;
import com.gcd.geo.mapper.GeoMapper;
import com.gcd.geo.repository.ProvinceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProvinceService {

    private final ProvinceRepository provinceRepository;
    private final CentreService centreService;
    private final GeoMapper geoMapper;

    @Transactional
    public ProvinceResponse create(ProvinceRequest request) {
        String nom = normalize(request.getNom());
        if (provinceRepository.existsByNomIgnoreCase(nom)) {
            throw new BusinessException("Une province avec ce nom existe déjà: " + nom);
        }

        Centre centre = centreService.findEntityById(request.getCentreId());

        Province entity = Province.builder()
                .nom(nom)
                .code(normalize(request.getCode()))
                .actif(request.getActif() == null || request.getActif())
                .centre(centre)
                .build();

        Province saved = provinceRepository.save(entity);
        log.info("Province créée: id={}, nom={}, centre={}", saved.getId(), saved.getNom(), centre.getNom());
        return geoMapper.toProvinceResponse(saved);
    }

    @Transactional(readOnly = true)
    public List<ProvinceResponse> findAll() {
        return provinceRepository.findAll().stream().map(geoMapper::toProvinceResponse).toList();
    }

    @Transactional(readOnly = true)
    public ProvinceResponse findById(Long id) {
        Province province = findEntityById(id);
        return geoMapper.toProvinceResponse(province);
    }

    @Transactional
    public ProvinceResponse update(Long id, ProvinceRequest request) {
        Province province = findEntityById(id);
        String nom = normalize(request.getNom());

        provinceRepository.findByNomIgnoreCase(nom)
                .filter(existing -> !existing.getId().equals(id))
                .ifPresent(existing -> {
                    throw new BusinessException("Une autre province avec ce nom existe déjà: " + nom);
                });

        Centre centre = centreService.findEntityById(request.getCentreId());

        province.setNom(nom);
        province.setCode(normalize(request.getCode()));
        province.setActif(request.getActif() == null || request.getActif());
        province.setCentre(centre);

        Province saved = provinceRepository.save(province);
        log.info("Province mise à jour: id={}, nom={}, centre={}", saved.getId(), saved.getNom(), centre.getNom());
        return geoMapper.toProvinceResponse(saved);
    }

    @Transactional
    public void delete(Long id) {
        Province province = findEntityById(id);
        provinceRepository.delete(province);
        log.info("Province supprimée: id={}", id);
    }

    @Transactional(readOnly = true)
    public List<ProvinceResponse> findByCentre(Long centreId) {
        if (centreId == null) {
            throw new BusinessException("centreId est obligatoire");
        }
        centreService.findEntityById(centreId);
        return provinceRepository.findByCentreId(centreId).stream()
                .map(geoMapper::toProvinceResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public Province findEntityById(Long id) {
        return provinceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Province introuvable: " + id));
    }

    private String normalize(String value) {
        if (value == null) {
            return null;
        }
        String cleaned = value.trim();
        return cleaned.isEmpty() ? null : cleaned;
    }
}
