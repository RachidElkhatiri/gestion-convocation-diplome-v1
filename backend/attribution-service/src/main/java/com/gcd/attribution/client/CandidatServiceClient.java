package com.gcd.attribution.client;

import com.gcd.attribution.config.FeignConfig;
import com.gcd.attribution.dto.CandidatExternalDto;
import com.gcd.attribution.dto.PageResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "candidat-service-client", url = "${clients.candidat.url}", configuration = FeignConfig.class)
public interface CandidatServiceClient {

    @GetMapping("/api/candidats")
    PageResponse<CandidatExternalDto> getCandidats(@RequestParam("page") int page,
                                                   @RequestParam("size") int size,
                                                   @RequestParam("sort") String sort);

    @GetMapping("/api/candidats/filter")
    PageResponse<CandidatExternalDto> getCandidatsFiltered(@RequestParam(value = "province", required = false) String province,
                                                           @RequestParam(value = "centre", required = false) String centre,
                                                           @RequestParam(value = "sexe", required = false) String sexe,
                                                           @RequestParam(value = "categorie", required = false) String categorie,
                                                           @RequestParam(value = "cin", required = false) String cin,
                                                           @RequestParam(value = "nom", required = false) String nom,
                                                           @RequestParam(value = "prenom", required = false) String prenom,
                                                           @RequestParam("page") int page,
                                                           @RequestParam("size") int size,
                                                           @RequestParam("sort") String sort);
}
