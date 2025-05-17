package br.com.alura.carros.model;

public class Marca {
    private String codigo;
    private String nome;

    public Marca () {

    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
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
        return "Marca[" +
                "codigo='" + codigo + '\'' +
                ", nome='" + nome + '\'' +
                ']';
    }
}
