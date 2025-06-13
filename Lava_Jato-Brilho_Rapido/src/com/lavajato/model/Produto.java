package com.lavajato.model;

public class Produto {
    private static int proximoId = 1;
    private int id;
    private String nome;
    private String descricao;
    private double precoVenda;
    private int quantidadeEstoque;

    public Produto(String nome, String descricao, double precoVenda, int quantidadeEstoque) {
        this.id = proximoId++;
        this.nome = nome;
        this.descricao = descricao;
        this.precoVenda = precoVenda;
        this.quantidadeEstoque = quantidadeEstoque;
    }

    // Getters
    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getDescricao() { return descricao; }
    public double getPrecoVenda() { return precoVenda; }
    public int getQuantidadeEstoque() { return quantidadeEstoque; }

    // Setters
    public void setNome(String nome) { this.nome = nome; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public void setPrecoVenda(double precoVenda) { this.precoVenda = precoVenda; }
    public void setQuantidadeEstoque(int quantidadeEstoque) { this.quantidadeEstoque = quantidadeEstoque; }

    @Override
    public String toString() {
        return String.format("ID: %d | Produto: %s | Preço: R$ %.2f | Estoque: %d",
                id, nome, precoVenda, quantidadeEstoque);
    }
}