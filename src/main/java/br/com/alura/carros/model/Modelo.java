package br.com.alura.carros.model;

public class Modelo {
    private int codigo;
    private String nome;

    public Modelo(DadosModelo dadosModelo) {
        this.codigo = dadosModelo.codigo();
        this.nome = dadosModelo.nome();
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Cód: " + getCodigo() + " " + "Descrição:  " + getNome();
    }
}
