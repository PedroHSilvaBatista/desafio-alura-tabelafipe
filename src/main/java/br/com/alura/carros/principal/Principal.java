package br.com.alura.carros.principal;

import br.com.alura.carros.model.DadosListaModelo;
import br.com.alura.carros.model.Modelo;
import br.com.alura.carros.service.ConsumoAPI;
import br.com.alura.carros.service.ConverteDados;
import br.com.alura.carros.model.Marca;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.Scanner;
import java.util.List;

public class Principal {
    private final Scanner leitura = new Scanner(System.in);
    private String endereco = "https://parallelum.com.br/fipe/api/v1/";
    private final ConsumoAPI consumo = new ConsumoAPI();
    private final ConverteDados conversor = new ConverteDados();

    public void exibirMenu() {
        System.out.println("""
                DIGITE A OPÇÃO QUE DESEJA ESCOLHER
                CARROS
                MOTOS
                CAMINHOES
                """);
        System.out.print("Digite sua opção aqui: ");
        String opcaoUsuario = leitura.nextLine().toLowerCase();

        endereco += opcaoUsuario + "/marcas/";

        String json = consumo.obterDados(endereco);
        System.out.println(json);

        List<Marca> listaDeMarcas = conversor.converterDados(json, new TypeReference<List<Marca>>() {
        });
        listaDeMarcas.forEach(System.out::println);

        System.out.print("Informe o código da marca para consulta: ");
        String codigoMarcaUsuario = leitura.nextLine();

        endereco += codigoMarcaUsuario + "/modelos/";

        json = consumo.obterDados(endereco);
        System.out.println(json);

        DadosListaModelo dadosListaModelo = conversor.converterDados(json, new TypeReference<DadosListaModelo>() {
        });
        System.out.println(dadosListaModelo);

        List<Modelo> modelos = dadosListaModelo.listaDeModelos().stream()
                .map(Modelo::new)
                .toList();

        modelos.forEach(System.out::println);

        System.out.print("Digite um trecho do nome do modelo para consulta: ");
        String trechoModeloUsuario = leitura.nextLine().toLowerCase();

        System.out.println();

        modelos.stream()
                .filter(m -> m.getNome().toLowerCase().contains(trechoModeloUsuario))
                .forEach(System.out::println);

        System.out.println();
        System.out.print("Digite o código do modelo para consultar valores: ");
        String codigoModeloUsuario = leitura.nextLine();
        System.out.println();

        endereco += codigoModeloUsuario + "/anos";
        json = consumo.obterDados(endereco);

        System.out.println(json);
    }
}
