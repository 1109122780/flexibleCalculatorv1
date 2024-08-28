package com.paypal.flexiblecalculator.service.impl;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class DivideOperationTest {

    @Test
    void testDivision() {
        DivideOperation divideOperation = new DivideOperation();
        Number result = divideOperation.execute(6, 3);
        assertEquals(new BigDecimal("2"), result);
    }

    @Test
    void testDivisionByZero() {
        DivideOperation divideOperation = new DivideOperation();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            divideOperation.execute(6, 0);
        });
        String expectedMessage = "Cannot divide by zero";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}
