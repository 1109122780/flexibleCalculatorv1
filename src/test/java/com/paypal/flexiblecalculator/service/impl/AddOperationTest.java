package com.paypal.flexiblecalculator.service.impl;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class AddOperationTest {

    @Test
    void testAddition() {
        AddOperation addOperation = new AddOperation();
        Number result = addOperation.execute(5, 3);
        assertEquals(new BigDecimal("8"), result);
    }

    @Test
    void testAdditionWithNegativeNumbers() {
        AddOperation addOperation = new AddOperation();
        Number result = addOperation.execute(-5, -3);
        assertEquals(new BigDecimal("-8"), result);
    }

}

