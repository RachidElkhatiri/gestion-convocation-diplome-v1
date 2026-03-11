package com.gcd.candidat.service;

import com.gcd.candidat.entity.CandidatGlobal;
import com.gcd.candidat.exception.BusinessException;
import com.gcd.candidat.repository.CandidatGlobalRepository;
import com.gcd.candidat.util.CandidatGlobalColumns;
import com.gcd.candidat.util.ExcelCellUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExcelImportService {

    private static final int BATCH_SIZE = 500;
    private static final List<String> DATE_FIELDS = List.of("dateValidite", "dateNaiss", "dateValide");

    private final CandidatGlobalRepository candidatGlobalRepository;

    @Transactional
    public int importExcel(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new BusinessException("Le fichier Excel est vide");
        }

        try (InputStream inputStream = file.getInputStream(); XSSFWorkbook workbook = new XSSFWorkbook(inputStream)) {
            Sheet sheet = workbook.getSheetAt(0);
            List<CandidatGlobal> batch = new ArrayList<>();
            int imported = 0;

            for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                Row row = sheet.getRow(rowIndex);
                if (row == null) {
                    continue;
                }

                try {
                    CandidatGlobal candidat = mapRow(row);
                    if (candidat.getIdDemande() == null) {
                        log.warn("Ligne {} ignorée: Id_Demande invalide", rowIndex + 1);
                        continue;
                    }
                    batch.add(candidat);
                    if (batch.size() >= BATCH_SIZE) {
                        candidatGlobalRepository.saveAll(batch);
                        imported += batch.size();
                        batch.clear();
                    }
                } catch (Exception ex) {
                    log.error("Erreur sur ligne {}: {}", rowIndex + 1, ex.getMessage());
                }
            }

            if (!batch.isEmpty()) {
                candidatGlobalRepository.saveAll(batch);
                imported += batch.size();
            }

            log.info("Import terminé: {} lignes insérées", imported);
            return imported;
        } catch (IOException e) {
            throw new BusinessException("Impossible de lire le fichier Excel: " + e.getMessage());
        }
    }

    private CandidatGlobal mapRow(Row row) throws ReflectiveOperationException {
        CandidatGlobal candidat = new CandidatGlobal();
        List<String> fields = CandidatGlobalColumns.ENTITY_FIELDS;

        for (int i = 0; i < fields.size(); i++) {
            String fieldName = fields.get(i);
            Field field = CandidatGlobal.class.getDeclaredField(fieldName);
            field.setAccessible(true);

            if ("idDemande".equals(fieldName)) {
                field.set(candidat, ExcelCellUtil.parseLongSafe(ExcelCellUtil.readAsString(row, i)));
                continue;
            }

            if (DATE_FIELDS.contains(fieldName)) {
                LocalDate dateValue = ExcelCellUtil.readAsDate(row, i);
                field.set(candidat, dateValue);
                continue;
            }

            if ("age".equals(fieldName) || "classement".equals(fieldName)) {
                String raw = ExcelCellUtil.readAsString(row, i);
                Long parsed = ExcelCellUtil.parseLongSafe(raw);
                field.set(candidat, parsed == null ? null : parsed.intValue());
                continue;
            }

            field.set(candidat, ExcelCellUtil.readAsString(row, i));
        }

        return candidat;
    }
}
