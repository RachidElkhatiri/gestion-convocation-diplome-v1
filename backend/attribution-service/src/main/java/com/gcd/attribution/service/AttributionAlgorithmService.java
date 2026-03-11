package com.gcd.attribution.service;

import com.gcd.attribution.config.AttributionProperties;
import com.gcd.attribution.dto.AttributionRunRequest;
import com.gcd.attribution.dto.AttributionRunResponse;
import com.gcd.attribution.dto.CandidatExternalDto;
import com.gcd.attribution.dto.CentreCapacityDto;
import com.gcd.attribution.entity.ResultatAttribution;
import com.gcd.attribution.exception.BusinessException;
import com.gcd.attribution.repository.ResultatAttributionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class AttributionAlgorithmService {

    private final CandidatFetchService candidatFetchService;
    private final GeoLookupService geoLookupService;
    private final ResultatAttributionRepository resultatRepository;
    private final AttributionProperties properties;

    @Transactional
    public AttributionRunResponse run(AttributionRunRequest request) {
        if (Boolean.TRUE.equals(request.getResetResults())) {
            resultatRepository.deleteAllInBatch();
        }

        geoLookupService.clearCache();

        String runId = UUID.randomUUID().toString();
        String categorie = normalize(request.getCategorie());
        List<CandidatExternalDto> candidats = new ArrayList<>(candidatFetchService.fetchAll(categorie));

        if (candidats.isEmpty()) {
            return AttributionRunResponse.builder()
                    .runId(runId)
                    .totalCandidats(0)
                    .totalLignesResultat(0)
                    .mode(resolveMode(request, categorie))
                    .build();
        }

        LocalDate startDate = request.getStartDate() != null
                ? request.getStartDate()
                : LocalDate.now().plusDays(properties.getDefaultStartDateOffsetDays());

        Set<LocalDate> holidays = new HashSet<>(properties.getHolidays());
        if (request.getHolidaysOverride() != null) {
            holidays.addAll(request.getHolidaysOverride());
        }

        Map<String, Map<LocalDate, Integer>> centreDailyLoad = new HashMap<>();
        Map<String, Integer> aggregation = new HashMap<>();

        boolean processByCategory = Boolean.TRUE.equals(request.getProcessByCategory());
        candidats.sort(Comparator
                .comparing((CandidatExternalDto c) -> safe(processByCategory ? c.getCategorie() : "GLOBAL"))
                .thenComparing(c -> safe(c.getProvince()))
                .thenComparing(c -> safe(c.getNom()))
                .thenComparing(c -> safe(c.getPrenom())));

        for (CandidatExternalDto candidat : candidats) {
            CentreCapacityDto centreInfo = geoLookupService.findCentreForProvince(candidat.getProvince());
            String cat = processByCategory ? safe(candidat.getCategorie()) : "GLOBAL";
            LocalDate date = findNextAvailableDate(centreInfo.getCentre(), centreInfo.getCapaciteJournaliere(), startDate, holidays, centreDailyLoad);
            incrementLoad(centreInfo.getCentre(), date, centreDailyLoad);

            String key = buildAggregationKey(centreInfo.getCentre(), centreInfo.getProvince(), date, cat);
            aggregation.put(key, aggregation.getOrDefault(key, 0) + 1);
        }

        List<ResultatAttribution> rows = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : aggregation.entrySet()) {
            String[] parts = entry.getKey().split("\\|", -1);
            rows.add(ResultatAttribution.builder()
                    .runId(runId)
                    .centreNom(parts[0])
                    .provinceNom(parts[1])
                    .dateConvocation(LocalDate.parse(parts[2]))
                    .categorie(parts[3])
                    .nombreCandidats(entry.getValue())
                    .statut("PLANIFIE")
                    .metadata(buildMetadata(processByCategory, categorie, holidays.size()))
                    .createdAt(LocalDateTime.now())
                    .build());
        }

        rows.sort(Comparator.comparing(ResultatAttribution::getDateConvocation)
                .thenComparing(ResultatAttribution::getCentreNom)
                .thenComparing(ResultatAttribution::getProvinceNom)
                .thenComparing(ResultatAttribution::getCategorie));

        resultatRepository.saveAll(rows);

        log.info("Attribution terminee runId={}, candidats={}, lignes={}", runId, candidats.size(), rows.size());

        return AttributionRunResponse.builder()
                .runId(runId)
                .totalCandidats(candidats.size())
                .totalLignesResultat(rows.size())
                .mode(resolveMode(request, categorie))
                .build();
    }

    private LocalDate findNextAvailableDate(String centre, Integer capacity, LocalDate start, Set<LocalDate> holidays,
                                            Map<String, Map<LocalDate, Integer>> centreDailyLoad) {
        if (capacity == null || capacity <= 0) {
            throw new BusinessException("Capacite invalide pour centre: " + centre);
        }

        LocalDate current = start;
        while (true) {
            if (isWorkingDay(current, holidays)) {
                int currentLoad = centreDailyLoad
                        .getOrDefault(centre, Collections.emptyMap())
                        .getOrDefault(current, 0);
                if (currentLoad < capacity) {
                    return current;
                }
            }
            current = current.plusDays(1);
        }
    }

    private void incrementLoad(String centre, LocalDate date, Map<String, Map<LocalDate, Integer>> centreDailyLoad) {
        centreDailyLoad.computeIfAbsent(centre, key -> new HashMap<>());
        Map<LocalDate, Integer> daily = centreDailyLoad.get(centre);
        daily.put(date, daily.getOrDefault(date, 0) + 1);
    }

    private boolean isWorkingDay(LocalDate date, Set<LocalDate> holidays) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) {
            return false;
        }
        return !holidays.contains(date);
    }

    private String buildAggregationKey(String centre, String province, LocalDate date, String categorie) {
        return safe(centre) + "|" + safe(province) + "|" + date + "|" + safe(categorie);
    }

    private String buildMetadata(boolean byCategory, String filteredCategory, int holidaysCount) {
        return "{\"mode\":\"" + (byCategory ? "BY_CATEGORY" : "GLOBAL") +
                "\",\"filteredCategory\":\"" + safe(filteredCategory) +
                "\",\"holidaysCount\":" + holidaysCount + "}";
    }

    private String resolveMode(AttributionRunRequest request, String categorie) {
        if (categorie != null) {
            return "FILTERED_CATEGORY";
        }
        return Boolean.TRUE.equals(request.getProcessByCategory()) ? "BY_CATEGORY" : "GLOBAL";
    }

    private String safe(String value) {
        return value == null ? "N/A" : value.trim();
    }

    private String normalize(String value) {
        if (value == null) {
            return null;
        }
        String cleaned = value.trim();
        return cleaned.isEmpty() ? null : cleaned;
    }
}
