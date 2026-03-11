package com.gcd.geo.controller;

import com.gcd.geo.dto.CentreByProvinceNameResponse;
import com.gcd.geo.service.CentreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/geo")
@RequiredArgsConstructor
public class GeoBridgeController {

    private final CentreService centreService;

    @GetMapping("/centres/by-province/{provinceName}")
    public ResponseEntity<CentreByProvinceNameResponse> findCentreByProvinceName(@PathVariable String provinceName) {
        return ResponseEntity.ok(centreService.getCentreByProvinceName(provinceName));
    }
}
