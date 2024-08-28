package com.paypal.flexiblecalculator.service.impl;

import com.paypal.flexiblecalculator.service.OperationStrategy;
import org.springframework.boot.json.JsonParseException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component("divideOperation")
public class DivideOperation implements OperationStrategy {

    private static final int DEFAULT_SCALE = 10;

    @Override
    public Number execute(Number num1, Number num2) {
        if (num2.doubleValue() == 0) {
            throw new IllegalArgumentException("Cannot divide by zero");
        }

        BigDecimal bigDecimalNum1 = new BigDecimal(num1.toString());
        BigDecimal bigDecimalNum2 = new BigDecimal(num2.toString());

        return bigDecimalNum1.divide(bigDecimalNum2, DEFAULT_SCALE, RoundingMode.HALF_UP).stripTrailingZeros();
    }
}
