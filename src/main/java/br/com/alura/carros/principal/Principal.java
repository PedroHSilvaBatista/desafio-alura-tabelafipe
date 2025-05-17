package br.com.alura.carros.principal;

import br.com.alura.carros.service.ConsumoAPI;

import java.util.Scanner;

public class Principal {
    private Scanner leitura = new Scanner(System.in);
    private String endereco = "https://parallelum.com.br/fipe/api/v1/";
    private ConsumoAPI consumo = new ConsumoAPI();

    public void exibirMenu() {
        System.out.println("""
                DIGITE A OPÇÃO QUE DESEJA ESCOLHER
                CARROS
                MOTOS
                CAMINHOES
                """);
        System.out.print("Digite sua opção aqui: ");
        String opcaoUsuario = leitura.nextLine().toLowerCase();

        endereco += opcaoUsuario + "/marcas";

        String json = consumo.obterDados(endereco);
        System.out.println(json);
    }
}
