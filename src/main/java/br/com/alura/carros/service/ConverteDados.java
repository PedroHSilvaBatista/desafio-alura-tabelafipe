package br.com.alura.carros.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConverteDados implements IConverteDados {
    private final ObjectMapper MAPPER = new ObjectMapper();

    public <T> T converterDados(String string, TypeReference<T> typeRef) {
        try {
            return MAPPER.readValue(string, typeRef);
        } catch (JsonProcessingException e ) {
            throw new RuntimeException();
        }
    }
}
