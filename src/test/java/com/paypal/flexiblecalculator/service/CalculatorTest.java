package com.paypal.flexiblecalculator.service;

import com.paypal.flexiblecalculator.model.Operation;
import com.paypal.flexiblecalculator.service.impl.AddOperation;
import com.paypal.flexiblecalculator.service.impl.SubtractOperation;
import com.paypal.flexiblecalculator.service.impl.MultiplyOperation;
import com.paypal.flexiblecalculator.service.impl.DivideOperation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    private Calculator calculator;

    @BeforeEach
    void setUp() {
        Map<Operation, OperationStrategy> strategies = new HashMap<>();
        strategies.put(Operation.ADD, new AddOperation());
        strategies.put(Operation.SUBTRACT, new SubtractOperation());
        strategies.put(Operation.MULTIPLY, new MultiplyOperation());
        strategies.put(Operation.DIVIDE, new DivideOperation());
        calculator = new Calculator(strategies);
    }

    @Test
    void testCalculateAddition() {
        Number result = calculator.calculate(Operation.ADD, 5, 3);
        assertEquals(8.0, result);
    }

    @Test
    void testCalculateSubtraction() {
        Number result = calculator.calculate(Operation.SUBTRACT, 5, 3);
        assertEquals(2.0, result);
    }

    @Test
    void testCalculateMultiplication() {
        Number result = calculator.calculate(Operation.MULTIPLY, 5, 3);
        assertEquals(15.0, result);
    }

    @Test
    void testCalculateDivision() {
        Number result = calculator.calculate(Operation.DIVIDE, 6, 3);
        assertEquals(2.0, result);
    }

    @Test
    void testChainOperations() {
        calculator.apply(Operation.ADD, 5)
                .apply(Operation.SUBTRACT, 3);
        assertEquals(2.0, calculator.getResult());
    }

    @Test
    void testReset() {
        calculator.apply(Operation.ADD, 5);
        calculator.reset();
        assertEquals(0, calculator.getResult());
    }

    @Test
    void testDivisionByZero() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            calculator.apply(Operation.DIVIDE, 5).apply(Operation.DIVIDE, 0);
        });

        String expectedMessage = "Cannot divide by zero";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testUnsupportedOperation() {
        Exception exception = assertThrows(UnsupportedOperationException.class, () -> {
            calculator.calculate(null, 5, 3);
        });

        String expectedMessage = "Operation not supported";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}
