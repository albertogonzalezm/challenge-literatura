package com.example.literaturaalura.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataConverter implements IDataConverter {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public <T> T getData(String json, Class<T> dataClass) {

        if (json == null || json.trim().isEmpty()) {
            throw new IllegalArgumentException("El JSON proporcionado es nulo o está vacío");
        }

        try {
            return objectMapper.readValue(json, dataClass);
        } catch (JsonProcessingException exp) {
            throw new RuntimeException(exp);
        }
    }
}
