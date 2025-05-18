package br.com.alura.carros.service;

import com.fasterxml.jackson.core.type.TypeReference;

public interface IConverteDados {

    <T> T converterDados(String string, TypeReference<T> typeRef);
}
