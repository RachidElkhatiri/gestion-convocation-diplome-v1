package com.gcd.candidat.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CategoryUtilTest {

    @Test
    void shouldReturnCat3WhenUniversityDiplomaExists() {
        String category = CategoryUtil.resolveCategory(null, null, "Licence", null);
        assertEquals("CAT3", category);
    }

    @Test
    void shouldReturnCat2WhenBacExists() {
        String category = CategoryUtil.resolveCategory(null, "Bac Sciences", null, null);
        assertEquals("CAT2", category);
    }

    @Test
    void shouldReturnCat1WhenOnlyInferieurBacExists() {
        String category = CategoryUtil.resolveCategory("9eme", null, null, null);
        assertEquals("CAT1", category);
    }
}
