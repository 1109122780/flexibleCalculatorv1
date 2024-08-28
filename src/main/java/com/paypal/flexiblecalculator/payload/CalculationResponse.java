package com.paypal.flexiblecalculator.payload;

import com.paypal.flexiblecalculator.model.Operation;

public class CalculationResponse {
    private Number result;

    public CalculationResponse(Number result) {
        this.result = result;
    }

    public Number getResult() {
        return result;
    }
}
