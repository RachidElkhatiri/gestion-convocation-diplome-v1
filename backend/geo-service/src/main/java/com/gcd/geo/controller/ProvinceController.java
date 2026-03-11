package com.gcd.geo.controller;

import com.gcd.geo.dto.ProvinceRequest;
import com.gcd.geo.dto.ProvinceResponse;
import com.gcd.geo.service.ProvinceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/provinces")
@RequiredArgsConstructor
public class ProvinceController {

    private final ProvinceService provinceService;

    @PostMapping
    public ResponseEntity<ProvinceResponse> create(@Valid @RequestBody ProvinceRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(provinceService.create(request));
    }

    @GetMapping
    public ResponseEntity<List<ProvinceResponse>> getAll() {
        return ResponseEntity.ok(provinceService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProvinceResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(provinceService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProvinceResponse> update(@PathVariable Long id, @Valid @RequestBody ProvinceRequest request) {
        return ResponseEntity.ok(provinceService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        provinceService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-centre/{centreId}")
    public ResponseEntity<List<ProvinceResponse>> getByCentre(@PathVariable Long centreId) {
        return ResponseEntity.ok(provinceService.findByCentre(centreId));
    }
}
