package com.ebay.flexiblecalculator.payload;

public class CalculationResponse {
    private Number result;

    public CalculationResponse(Number result) {
        this.result = result;
    }

    public Number getResult() {
        return result;
    }
}
