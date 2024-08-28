package com.paypal.flexiblecalculator.controller;

import com.paypal.flexiblecalculator.model.Operation;
import com.paypal.flexiblecalculator.payload.CalculationRequest;
import com.paypal.flexiblecalculator.payload.CalculationResponse;
import com.paypal.flexiblecalculator.service.Calculator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CalculatorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Calculator calculator;

    @Test
    public void testValidCalculation() throws Exception {
        String requestBody = "{\"operation\": \"ADD\", \"num1\": 5, \"num2\": 3}";

        mockMvc.perform(post("/api/v1/calculator/calculate")
                        .contentType("application/json")
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(8.0));
    }

    @Test
    public void testUnsupportedOperationException() throws Exception {
        String requestBody = "{\"operation\": \"XXX\", \"num1\": 5, \"num2\": 3}";

        mockMvc.perform(post("/api/v1/calculator/calculate")
                        .contentType("application/json")
                        .content(requestBody))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testDivisionByZero() throws Exception {
        String requestBody = "{\"operation\": \"DIVIDE\", \"num1\": 5, \"num2\": 0}";

        mockMvc.perform(post("/api/v1/calculator/calculate")
                        .contentType("application/json")
                        .content(requestBody))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testChainedOperations() throws Exception {
        String requestBody = "["
                + "{\"operation\": \"ADD\", \"num1\": 5, \"num2\": 3},"
                + "{\"operation\": \"MULTIPLY\", \"num1\": null, \"num2\": 2},"
                + "{\"operation\": \"DIVIDE\", \"num1\": null, \"num2\": 4}"
                + "]";

        mockMvc.perform(post("/api/v1/calculator/chain")
                        .contentType("application/json")
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(4.0));
    }

    @Test
    public void testInvalidOperationInChainedOperations() throws Exception {
        String requestBody = "["
                + "{\"operation\": \"ADD\", \"num1\": 5, \"num2\": 3},"
                + "{\"operation\": \"XXX\", \"num1\": null, \"num2\": 2},"
                + "{\"operation\": \"DIVIDE\", \"num1\": null, \"num2\": 4}"
                + "]";

        mockMvc.perform(post("/api/v1/calculator/chain")
                        .contentType("application/json")
                        .content(requestBody))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testNullOperand() throws Exception {
        String requestBody = "{\"operation\": \"ADD\", \"num1\": null, \"num2\": 3}";

        mockMvc.perform(post("/api/v1/calculator/calculate")
                        .contentType("application/json")
                        .content(requestBody))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testCalculatorStateReset() throws Exception {
        // Perform an operation
        String requestBody1 = "{\"operation\": \"ADD\", \"num1\": 5, \"num2\": 3}";
        mockMvc.perform(post("/api/v1/calculator/calculate")
                        .contentType("application/json")
                        .content(requestBody1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(8.0));

        // Perform another operation to ensure state reset
        String requestBody2 = "{\"operation\": \"SUBTRACT\", \"num1\": 10, \"num2\": 2}";
        mockMvc.perform(post("/api/v1/calculator/calculate")
                        .contentType("application/json")
                        .content(requestBody2))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(8.0)); // result should not carry over
    }
}

