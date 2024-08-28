package com.paypal.flexiblecalculator.model;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.IOException;

@JsonDeserialize(using = Operation.OperationDeserializer.class)
public enum Operation {
    ADD, SUBTRACT, MULTIPLY, DIVIDE;

    public static class OperationDeserializer extends JsonDeserializer<Operation> {
        @Override
        public Operation deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
                throws IOException {
            String operation = jsonParser.getText().toUpperCase();
            try {
                return Operation.valueOf(operation);
            } catch (IllegalArgumentException ex) {
                // Instead of throwing JsonMappingException, we throw UnsupportedOperationException
                throw new UnsupportedOperationException("Unsupported operation: " + operation);
            }
        }
    }
}
