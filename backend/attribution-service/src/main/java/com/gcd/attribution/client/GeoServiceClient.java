package com.gcd.attribution.client;

import com.gcd.attribution.config.FeignConfig;
import com.gcd.attribution.dto.GeoCentreResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "geo-service-client", url = "${clients.geo.url}", configuration = FeignConfig.class)
public interface GeoServiceClient {

    @GetMapping("/api/geo/centres/by-province/{provinceName}")
    GeoCentreResponse getCentreByProvinceName(@PathVariable("provinceName") String provinceName);
}
