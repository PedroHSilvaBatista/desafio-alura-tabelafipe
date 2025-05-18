package br.com.alura.carros.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConverteDados implements IConverteDados {
    private ObjectMapper mapper = new ObjectMapper();

    public <T> T converterDados(String string, TypeReference<T> typeRef) {
        try {
            return mapper.readValue(string, typeRef);
        } catch (JsonProcessingException e ) {
            throw new RuntimeException();
        }
    }
}
