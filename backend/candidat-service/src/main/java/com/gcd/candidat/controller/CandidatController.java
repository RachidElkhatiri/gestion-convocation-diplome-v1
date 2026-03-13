package com.gcd.candidat.controller;

import com.gcd.candidat.dto.CandidatResponse;
import com.gcd.candidat.service.CandidatQueryService;
import com.gcd.candidat.service.CandidatTransformationService;
import com.gcd.candidat.service.ExcelImportService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/api/candidats")
@RequiredArgsConstructor
@Validated
public class CandidatController {

    private final ExcelImportService excelImportService;
    private final CandidatTransformationService transformationService;
    private final CandidatQueryService candidatQueryService;

    @PostMapping("/import")
    public ResponseEntity<Map<String, Object>> importExcel(@RequestParam("file") MultipartFile file) {
        int imported = excelImportService.importExcel(file);
        return ResponseEntity.ok(Map.of("imported", imported));
    }

    @PostMapping("/transform")
    public ResponseEntity<Map<String, Object>> transform() {
        int transformed = transformationService.transform();
        return ResponseEntity.ok(Map.of("transformed", transformed));
    }

    @GetMapping
    public ResponseEntity<Page<CandidatResponse>> getAll(Pageable pageable) {
        return ResponseEntity.ok(candidatQueryService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CandidatResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(candidatQueryService.findById(id));
    }

    @GetMapping("/filter")
    public ResponseEntity<Page<CandidatResponse>> filter(
            @RequestParam(required = false) String province,
            @RequestParam(required = false) String centre,
            @RequestParam(required = false) String sexe,
            @RequestParam(required = false) String categorie,
            @RequestParam(required = false) String cin,
            @RequestParam(required = false) String nom,
            @RequestParam(required = false) String prenom,
            Pageable pageable) {
        return ResponseEntity.ok(candidatQueryService.filter(province, centre, sexe, categorie, cin, nom, prenom, pageable));
    }
}
