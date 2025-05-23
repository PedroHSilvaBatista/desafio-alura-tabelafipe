package br.com.alura.carros.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosListaModelo(@JsonAlias("modelos")List<DadosModelo> listaDeModelos) {
}
