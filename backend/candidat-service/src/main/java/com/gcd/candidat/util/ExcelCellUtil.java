package com.gcd.candidat.util;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public final class ExcelCellUtil {

    private static final DateTimeFormatter[] SUPPORTED_DATE_PATTERNS = new DateTimeFormatter[]{
            DateTimeFormatter.ofPattern("dd/MM/yyyy"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd")
    };

    private ExcelCellUtil() {
    }

    public static String readAsString(Row row, int index) {
        Cell cell = row.getCell(index, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
        if (cell == null) {
            return null;
        }

        return switch (cell.getCellType()) {
            case STRING -> normalize(cell.getStringCellValue());
            case NUMERIC -> {
                if (DateUtil.isCellDateFormatted(cell)) {
                    LocalDate date = Instant.ofEpochMilli(cell.getDateCellValue().getTime())
                            .atZone(ZoneId.systemDefault())
                            .toLocalDate();
                    yield date.toString();
                }
                double value = cell.getNumericCellValue();
                long longValue = (long) value;
                yield value == longValue ? String.valueOf(longValue) : String.valueOf(value);
            }
            case BOOLEAN -> String.valueOf(cell.getBooleanCellValue());
            case FORMULA -> {
                CellType cached = cell.getCachedFormulaResultType();
                if (cached == CellType.NUMERIC && DateUtil.isCellDateFormatted(cell)) {
                    LocalDate date = Instant.ofEpochMilli(cell.getDateCellValue().getTime())
                            .atZone(ZoneId.systemDefault())
                            .toLocalDate();
                    yield date.toString();
                }
                yield normalize(cell.toString());
            }
            default -> null;
        };
    }

    public static LocalDate readAsDate(Row row, int index) {
        String value = readAsString(row, index);
        if (value == null || value.isBlank()) {
            return null;
        }

        for (DateTimeFormatter formatter : SUPPORTED_DATE_PATTERNS) {
            try {
                return LocalDate.parse(value, formatter);
            } catch (Exception ignored) {
            }
        }

        try {
            return LocalDate.parse(value);
        } catch (Exception ignored) {
            return null;
        }
    }

    public static Long parseLongSafe(String value) {
        if (value == null || value.isBlank()) {
            return null;
        }
        try {
            return Long.parseLong(value.trim());
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private static String normalize(String value) {
        if (value == null) {
            return null;
        }
        String cleaned = value.trim();
        return cleaned.isEmpty() ? null : cleaned;
    }
}
