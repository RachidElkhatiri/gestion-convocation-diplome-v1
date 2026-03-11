package com.gcd.geo.controller;

import com.gcd.geo.dto.*;
import com.gcd.geo.service.CentreService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/centres")
@RequiredArgsConstructor
public class CentreController {

    private final CentreService centreService;

    @PostMapping
    public ResponseEntity<CentreResponse> create(@Valid @RequestBody CentreRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(centreService.create(request));
    }

    @GetMapping
    public ResponseEntity<List<CentreResponse>> getAll() {
        return ResponseEntity.ok(centreService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CentreResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(centreService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CentreResponse> update(@PathVariable Long id, @Valid @RequestBody CentreRequest request) {
        return ResponseEntity.ok(centreService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        centreService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-province/{provinceId}")
    public ResponseEntity<ProvinceCentreResponse> getByProvince(@PathVariable Long provinceId) {
        return ResponseEntity.ok(centreService.getCentreByProvinceId(provinceId));
    }

    @GetMapping("/by-province-name/{provinceName}")
    public ResponseEntity<CentreByProvinceNameResponse> getByProvinceName(@PathVariable String provinceName) {
        return ResponseEntity.ok(centreService.getCentreByProvinceName(provinceName));
    }
}
