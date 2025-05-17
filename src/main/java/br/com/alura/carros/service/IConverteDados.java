package br.com.alura.carros.service;

public interface IConverteDados {

    <T> T converterDados(String string, Class<T> classe);
}
