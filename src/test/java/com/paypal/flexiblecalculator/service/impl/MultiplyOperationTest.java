package com.paypal.flexiblecalculator.service.impl;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class MultiplyOperationTest {

    @Test
    void testMultiplication() {
        MultiplyOperation multiplyOperation = new MultiplyOperation();
        Number result = multiplyOperation.execute(5, 3);
        assertEquals(new BigDecimal("15"), result);
    }

    @Test
    void testMultiplicationWithNegativeNumbers() {
        MultiplyOperation multiplyOperation = new MultiplyOperation();
        Number result = multiplyOperation.execute(-5, -3);
        assertEquals(new BigDecimal("15"), result);
    }

}

