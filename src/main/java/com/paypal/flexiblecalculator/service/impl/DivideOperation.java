package com.paypal.flexiblecalculator.service.impl;

import com.paypal.flexiblecalculator.service.OperationStrategy;
import org.springframework.stereotype.Component;

@Component("divideOperation")
public class DivideOperation implements OperationStrategy {
    @Override
    public Number execute(Number num1, Number num2) {
        if (num2.doubleValue() == 0) {
            throw new IllegalArgumentException("Cannot divide by zero");
        }
        return num1.doubleValue() / num2.doubleValue();
    }
}
