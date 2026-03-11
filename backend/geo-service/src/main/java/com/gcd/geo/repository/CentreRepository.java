package com.gcd.geo.repository;

import com.gcd.geo.entity.Centre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CentreRepository extends JpaRepository<Centre, Long> {
    Optional<Centre> findByNomIgnoreCase(String nom);
    boolean existsByNomIgnoreCase(String nom);
}
