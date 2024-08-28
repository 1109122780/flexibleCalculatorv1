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
        if (num1 == null || num2 == null) {
            throw new IllegalArgumentException("Operands must not be null");
        }
        OperationStrategy strategy = operationStrategies.get(operation);
        if (strategy == null) {
            throw new UnsupportedOperationException("Operation not supported");
        }
        return strategy.execute(num1, num2);
    }

    public Calculator apply(Operation operation, Number value) {
        if (currentValue == null) {
            currentValue = value;
        } else {
            currentValue = calculate(operation, currentValue, value);
        }
        return this;
    }

    public Number getResult() {
        return this.currentValue;
    }

    public void reset() {
        this.currentValue = 0;
    }
}