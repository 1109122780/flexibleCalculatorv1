package com.ebay.flexiblecalculator.service.impl;

import com.ebay.flexiblecalculator.service.OperationStrategy;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component("multiplyOperation")
public class MultiplyOperation implements OperationStrategy {
    @Override
    public Number execute(Number num1, Number num2) {
        BigDecimal bigDecimalNum1 = new BigDecimal(num1.toString());
        BigDecimal bigDecimalNum2 = new BigDecimal(num2.toString());

        return bigDecimalNum1.multiply(bigDecimalNum2);
    }
}