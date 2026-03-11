package com.gcd.geo.repository;

import com.gcd.geo.entity.Province;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProvinceRepository extends JpaRepository<Province, Long> {
    Optional<Province> findByNomIgnoreCase(String nom);
    boolean existsByNomIgnoreCase(String nom);
    List<Province> findByCentreId(Long centreId);
    boolean existsByCentreId(Long centreId);
}
