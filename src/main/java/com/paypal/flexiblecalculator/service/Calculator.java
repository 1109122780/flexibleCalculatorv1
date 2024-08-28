package com.paypal.flexiblecalculator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.paypal.flexiblecalculator.model.Operation;

import java.util.Map;

@Service
public class Calculator {

    private final Map<Operation, OperationStrategy> operationStrategies;
    private Number currentValue;

    @Autowired
    public Calculator(Map<Operation, OperationStrategy> operationStrategies) {
        this.operationStrategies = operationStrategies;
    }

    public Number calculate(Operation operation, Number num1, Number num2) {
        // Check if either operand is null, which is not allowed in this context.
        if (num1 == null || num2 == null) {
            throw new IllegalArgumentException("Operands must not be null");
        }

        // Retrieve the strategy associated with the given operation. Configuration: config.CalculatorConfig.java
        OperationStrategy strategy = operationStrategies.get(operation);

        // If no strategy is found for the operation, it means the operation is not supported.
        if (strategy == null) {
            throw new UnsupportedOperationException("Operation not supported");
        }

        // Execute the strategy with the provided operands and return the result.
        return strategy.execute(num1, num2);
    }

    public Calculator apply(Operation operation, Number value) {
        // If currentValue is null, it means this is the first operation, so set the currentValue.
        if (currentValue == null) {
            currentValue = value;
        } else {
            // If currentValue is not null, perform the operation using the currentValue and the new value.
            currentValue = calculate(operation, currentValue, value);
        }

        // Return the Calculator instance to allow for method chaining.
        return this;
    }

    public Number getResult() {
        return this.currentValue;
    }

    public void reset() {
        this.currentValue = 0;
    }
}