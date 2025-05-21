package br.com.alura.carros.principal;

import br.com.alura.carros.model.*;
import br.com.alura.carros.service.ConsumoAPI;
import br.com.alura.carros.service.ConverteDados;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class Principal {
    private final Scanner LEITURA = new Scanner(System.in);
    private String endereco = "https://parallelum.com.br/fipe/api/v1/";
    private final ConsumoAPI CONSUMO = new ConsumoAPI();
    private final ConverteDados CONVERSOR = new ConverteDados();

    public void exibirMenu() {
        System.out.println("""
                DIGITE A OPÇÃO QUE DESEJA ESCOLHER
                CARROS
                MOTOS
                CAMINHOES
                """);
        System.out.print("Digite sua opção aqui: ");
        String opcaoUsuario = LEITURA.nextLine().toLowerCase();

        endereco += opcaoUsuario + "/marcas/";

        String json = CONSUMO.obterDados(endereco);

        List<Marca> listaDeMarcas = CONVERSOR.converterDados(json, new TypeReference<>() {
        });
        listaDeMarcas.forEach(System.out::println);

        System.out.print("Informe o código da marca para consulta: ");
        String codigoMarcaUsuario = LEITURA.nextLine();

        endereco += codigoMarcaUsuario + "/modelos/";

        json = CONSUMO.obterDados(endereco);

        DadosListaModelo dadosListaModelo = CONVERSOR.converterDados(json, new TypeReference<>() {
        });
        System.out.println(dadosListaModelo);

        List<Modelo> modelos = dadosListaModelo.listaDeModelos().stream()
                .map(Modelo::new)
                .toList();

        modelos.forEach(System.out::println);

        System.out.print("Digite um trecho do nome do modelo para consulta: ");
        String trechoModeloUsuario = LEITURA.nextLine().toLowerCase();

        System.out.println();

        modelos.stream()
                .filter(m -> m.getNome().toLowerCase().contains(trechoModeloUsuario))
                .forEach(System.out::println);

        System.out.println();
        System.out.print("Digite o código do modelo para consultar valores: ");
        String codigoModeloUsuario = LEITURA.nextLine();
        System.out.println();

        endereco += codigoModeloUsuario + "/anos/";
        json = CONSUMO.obterDados(endereco);

        List<DadosAnos> dadosAnos = CONVERSOR.converterDados(json, new TypeReference<>() {});
        List<DadosVeiculo> listaDadosVeiculo = new ArrayList<>();

        for (DadosAnos anos: dadosAnos) {
            String tempEndereco = endereco + anos.codigo();
            json = CONSUMO.obterDados(tempEndereco);
            listaDadosVeiculo.add(CONVERSOR.converterDados(json, new TypeReference<>(){}));
        }

        List<Veiculo> listaVeiculos = listaDadosVeiculo
                .stream()
                .map(Veiculo::new)
                .toList();

        listaVeiculos.forEach(System.out::println);
    }
}
