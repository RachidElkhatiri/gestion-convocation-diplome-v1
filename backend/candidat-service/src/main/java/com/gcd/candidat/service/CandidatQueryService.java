package com.gcd.candidat.service;

import com.gcd.candidat.dto.CandidatResponse;
import com.gcd.candidat.entity.Candidat;
import com.gcd.candidat.exception.ResourceNotFoundException;
import com.gcd.candidat.mapper.CandidatMapper;
import com.gcd.candidat.repository.CandidatRepository;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CandidatQueryService {

    private final CandidatRepository candidatRepository;
    private final CandidatMapper candidatMapper;

    public Page<CandidatResponse> findAll(Pageable pageable) {
        return candidatRepository.findAll(pageable).map(candidatMapper::toResponse);
    }

    public CandidatResponse findById(Long id) {
        Candidat candidat = candidatRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Candidat introuvable: " + id));
        return candidatMapper.toResponse(candidat);
    }

    public Page<CandidatResponse> filter(
            String province,
            String centre,
            String sexe,
            String categorie,
            String cin,
            String nom,
            String prenom,
            Pageable pageable) {

        Specification<Candidat> specification = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            addLike(predicates, cb, root.get("province"), province);
            addLike(predicates, cb, root.get("centre"), centre);
            addLike(predicates, cb, root.get("sexe"), sexe);
            addLike(predicates, cb, root.get("categorie"), categorie);
            addLike(predicates, cb, root.get("cin"), cin);
            addLike(predicates, cb, root.get("nom"), nom);
            addLike(predicates, cb, root.get("prenom"), prenom);
            return cb.and(predicates.toArray(new Predicate[0]));
        };

        return candidatRepository.findAll(specification, pageable).map(candidatMapper::toResponse);
    }

    private void addLike(List<Predicate> predicates, jakarta.persistence.criteria.CriteriaBuilder cb,
                         jakarta.persistence.criteria.Path<String> path, String value) {
        if (value != null && !value.isBlank()) {
            predicates.add(cb.like(cb.lower(path), "%" + value.toLowerCase().trim() + "%"));
        }
    }
}
