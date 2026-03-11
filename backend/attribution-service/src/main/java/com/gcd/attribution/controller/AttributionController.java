package com.gcd.attribution.controller;

import com.gcd.attribution.dto.AttributionRunRequest;
import com.gcd.attribution.dto.AttributionRunResponse;
import com.gcd.attribution.dto.ResultatAttributionResponse;
import com.gcd.attribution.service.AttributionAlgorithmService;
import com.gcd.attribution.service.ResultatAttributionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/attribution")
@RequiredArgsConstructor
public class AttributionController {

    private final AttributionAlgorithmService attributionAlgorithmService;
    private final ResultatAttributionService resultatService;

    @PostMapping("/run")
    public ResponseEntity<AttributionRunResponse> run(@Valid @RequestBody(required = false) AttributionRunRequest request) {
        AttributionRunRequest safeRequest = request == null ? new AttributionRunRequest() : request;
        return ResponseEntity.ok(attributionAlgorithmService.run(safeRequest));
    }

    @GetMapping("/results")
    public ResponseEntity<Page<ResultatAttributionResponse>> getResults(@RequestParam(required = false) String runId,
                                                                        Pageable pageable) {
        return ResponseEntity.ok(resultatService.findAll(runId, pageable));
    }

    @GetMapping("/results/{id}")
    public ResponseEntity<ResultatAttributionResponse> getResultById(@PathVariable Long id) {
        return ResponseEntity.ok(resultatService.findById(id));
    }
}
