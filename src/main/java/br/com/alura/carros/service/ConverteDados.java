package br.com.alura.carros.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConverteDados implements IConverteDados {
    private ObjectMapper mapper = new ObjectMapper();

    public <T> T converterDados(String string, Class<T> classe) {
        try {
            return mapper.readValue(string, classe);
        } catch (JsonProcessingException e ) {
            throw new RuntimeException();
        }
    }
}
