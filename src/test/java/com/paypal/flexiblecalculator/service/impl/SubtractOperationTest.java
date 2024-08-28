package com.paypal.flexiblecalculator.service.impl;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class SubtractOperationTest {

    @Test
    void testSubtraction() {
        SubtractOperation subtractOperation = new SubtractOperation();
        Number result = subtractOperation.execute(5, 3);
        assertEquals(new BigDecimal("2"), result);
    }

    @Test
    void testSubtractionWithNegativeNumbers() {
        SubtractOperation subtractOperation = new SubtractOperation();
        Number result = subtractOperation.execute(-5, -3);
        assertEquals(new BigDecimal("-2"), result);
    }

}

