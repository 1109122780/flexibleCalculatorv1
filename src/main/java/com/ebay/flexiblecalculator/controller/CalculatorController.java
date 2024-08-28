package com.ebay.flexiblecalculator.controller;

import com.ebay.flexiblecalculator.payload.CalculationRequest;
import com.ebay.flexiblecalculator.payload.CalculationResponse;
import com.ebay.flexiblecalculator.service.Calculator;
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
        Number lastResult = null;
        try {
            for (CalculationRequest request : requests) {
                if (request.getNum1() == null && lastResult != null) {
                    lastResult = calculator.calculate(request.getOperation(), lastResult, request.getNum2());
                } else {
                    lastResult = calculator.calculate(request.getOperation(), request.getNum1(), request.getNum2());
                }
            }
        } finally {
            calculator.reset(); // Reset after all operations are completed
        }
        return new CalculationResponse(lastResult);
    }

}
