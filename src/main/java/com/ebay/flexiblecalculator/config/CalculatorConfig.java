package com.ebay.flexiblecalculator.config;

import com.ebay.flexiblecalculator.model.Operation;
import com.ebay.flexiblecalculator.service.OperationStrategy;
import com.ebay.flexiblecalculator.service.impl.AddOperation;
import com.ebay.flexiblecalculator.service.impl.DivideOperation;
import com.ebay.flexiblecalculator.service.impl.MultiplyOperation;
import com.ebay.flexiblecalculator.service.impl.SubtractOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CalculatorConfig {

    @Bean
    public Map<Operation, OperationStrategy> operationStrategies(
            AddOperation addOperation,
            SubtractOperation subtractOperation,
            MultiplyOperation multiplyOperation,
            DivideOperation divideOperation) {

        Map<Operation, OperationStrategy> strategies = new HashMap<>();
        strategies.put(Operation.ADD, addOperation);
        strategies.put(Operation.SUBTRACT, subtractOperation);
        strategies.put(Operation.MULTIPLY, multiplyOperation);
        strategies.put(Operation.DIVIDE, divideOperation);
        return strategies;
    }
}
