package com.paypal.flexiblecalculator.payload;

import com.paypal.flexiblecalculator.model.Operation;

public class CalculationRequest {
    private Operation operation;
    private Number num1;
    private Number num2;

    public CalculationRequest(Operation operation, Number num1, Number num2) {
        this.operation = operation;
        this.num1 = num1;
        this.num2 = num2;
    }

    public Number getNum1() {
        return num1;
    }


    public Number getNum2() {
        return num2;
    }


    public Operation getOperation() {
        return operation;
    }

}
