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
        try{
            Number result = calculator.calculate(request.getOperation(), request.getNum1(), request.getNum2());
            return new CalculationResponse(result);
        }
        finally {
            calculator.reset();
        }
    }

    @PostMapping("/chain")
    public CalculationResponse chainOperations(@RequestBody CalculationRequest[] requests) {
        try{
            for (CalculationRequest request : requests) {
                if (request.getNum1() != null) {
                    calculator.apply(request.getOperation(), request.getNum1());
                }
                calculator.apply(request.getOperation(), request.getNum2());
            }
            return new CalculationResponse(calculator.getResult());
        }
        finally {
            calculator.reset();
        }
    }
}
