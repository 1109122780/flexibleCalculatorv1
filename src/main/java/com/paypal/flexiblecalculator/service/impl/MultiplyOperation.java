package com.paypal.flexiblecalculator.service.impl;

import com.paypal.flexiblecalculator.service.OperationStrategy;
import org.springframework.stereotype.Component;

@Component("multiplyOperation")
public class MultiplyOperation implements OperationStrategy {
    @Override
    public Number execute(Number num1, Number num2) {
        return num1.doubleValue() * num2.doubleValue();
    }
}