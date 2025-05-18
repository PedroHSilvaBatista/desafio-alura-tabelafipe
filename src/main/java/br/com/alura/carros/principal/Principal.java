package br.com.alura.carros.principal;

import br.com.alura.carros.model.DadosListaModelo;
import br.com.alura.carros.model.DadosModelo;
import br.com.alura.carros.model.Modelo;
import br.com.alura.carros.service.ConsumoAPI;
import br.com.alura.carros.service.ConverteDados;
import br.com.alura.carros.model.Marca;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.Map;
import java.util.Scanner;
import java.util.List;
import java.util.stream.Collectors;

public class Principal {
    private Scanner leitura = new Scanner(System.in);
    private String endereco = "https://parallelum.com.br/fipe/api/v1/";
    private ConsumoAPI consumo = new ConsumoAPI();
    private ConverteDados conversor = new ConverteDados();

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

        endereco += codigoMarcaUsuario + "/modelos";

        json = consumo.obterDados(endereco);
        System.out.println(json);

        DadosListaModelo dadosListaModelo = conversor.converterDados(json, new TypeReference<DadosListaModelo>() {
        });
        System.out.println(dadosListaModelo);

        List<Modelo> modelos = dadosListaModelo.listaDeModelos().stream()
                .map(Modelo::new)
                .toList();

        modelos.forEach(System.out::println);
    }
}
