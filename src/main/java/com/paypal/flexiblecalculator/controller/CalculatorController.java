package com.paypal.flexiblecalculator.controller;

import com.paypal.flexiblecalculator.payload.CalculationRequest;
import com.paypal.flexiblecalculator.payload.CalculationResponse;
import com.paypal.flexiblecalculator.service.Calculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/calculator")
public class CalculatorController {

    @Autowired
    private Calculator calculator;

    @PostMapping("/calculate")
    public CalculationResponse calculate(@RequestBody CalculationRequest request) {
        Number result = calculator.calculate(
                request.getOperation(),
                request.getNum1(),
                request.getNum2()
        );
        return new CalculationResponse(result);
    }

    @PostMapping("/chain")
    public CalculationResponse chainOperations(@RequestBody CalculationRequest[] requests) {
        for (CalculationRequest request : requests) {
            calculator.apply(request.getOperation(), request.getNum2());
        }
        return new CalculationResponse(calculator.getResult());
    }
}
