package model;

public class Produto {
    private final String nome;
    private final double preco;
    private final double peso;

    public Produto(String nome, double preco, double peso) {
        this.nome = nome;
        this.preco = preco;
        this.peso = peso;
    }

    public String getNome() { return nome; }
    public double getPreco() { return preco; }
    public double getPeso() { return peso; }
}
