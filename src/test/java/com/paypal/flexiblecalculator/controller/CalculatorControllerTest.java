package com.paypal.flexiblecalculator.controller;

import com.paypal.flexiblecalculator.model.Operation;
import com.paypal.flexiblecalculator.payload.CalculationRequest;
import com.paypal.flexiblecalculator.payload.CalculationResponse;
import com.paypal.flexiblecalculator.service.Calculator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CalculatorController.class)
class CalculatorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private Calculator calculator;

    @Test
    void testSingleOperation() throws Exception {
        CalculationRequest request = new CalculationRequest(Operation.ADD, 5, 3);
        CalculationResponse response = new CalculationResponse(8.0);

        when(calculator.calculate(any(Operation.class), any(Number.class), any(Number.class)))
                .thenReturn(8.0);

        mockMvc.perform(post("/api/v1/calculator/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"operation\":\"ADD\",\"num1\":5,\"num2\":3}"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"result\":8.0}"));
    }

    @Test
    void testChainOperations() throws Exception {
        when(calculator.getResult()).thenReturn(10.0);

        mockMvc.perform(post("/api/v1/calculator/chain")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("[{\"operation\":\"ADD\",\"num1\":5,\"num2\":0}," +
                                "{\"operation\":\"MULTIPLY\",\"num1\":null,\"num2\":2}]"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"result\":10.0}"));
    }
}

