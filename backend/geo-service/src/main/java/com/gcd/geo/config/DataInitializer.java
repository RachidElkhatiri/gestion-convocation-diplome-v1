package com.gcd.geo.config;

import com.gcd.geo.entity.Centre;
import com.gcd.geo.entity.Province;
import com.gcd.geo.repository.CentreRepository;
import com.gcd.geo.repository.ProvinceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class DataInitializer {

    private final CentreRepository centreRepository;
    private final ProvinceRepository provinceRepository;

    @Bean
    CommandLineRunner seedGeoData() {
        return args -> {
            if (centreRepository.count() > 0 || provinceRepository.count() > 0) {
                return;
            }

            Centre centreRabat = centreRepository.save(Centre.builder()
                    .nom("Centre Rabat")
                    .capaciteJournaliere(120)
                    .adresse("Avenue Mohammed V")
                    .ville("Rabat")
                    .actif(true)
                    .build());

            Centre centreCasa = centreRepository.save(Centre.builder()
                    .nom("Centre Casablanca")
                    .capaciteJournaliere(180)
                    .adresse("Boulevard Zerktouni")
                    .ville("Casablanca")
                    .actif(true)
                    .build());

            provinceRepository.save(Province.builder().nom("Rabat").code("RAB").actif(true).centre(centreRabat).build());
            provinceRepository.save(Province.builder().nom("Sale").code("SAL").actif(true).centre(centreRabat).build());
            provinceRepository.save(Province.builder().nom("Casablanca").code("CAS").actif(true).centre(centreCasa).build());

            log.info("Jeu de données initial Geo chargé");
        };
    }
}
