package com.gcd.candidat.util;

public final class CategoryUtil {

    private CategoryUtil() {
    }

    public static String resolveCategory(String niveauInferieurBac, String typeBacObtenu, String diplomeUniversitaire, String diplomeEcoleSuperieure) {
        if (hasText(diplomeUniversitaire) || hasText(diplomeEcoleSuperieure)) {
            return "CAT3";
        }
        if (hasText(typeBacObtenu)) {
            return "CAT2";
        }
        if (hasText(niveauInferieurBac)) {
            return "CAT1";
        }
        return "CAT1";
    }

    public static String clean(String value) {
        if (value == null) {
            return null;
        }
        String normalized = value.trim();
        return normalized.isEmpty() ? null : normalized;
    }

    private static boolean hasText(String value) {
        return value != null && !value.trim().isEmpty();
    }
}
