package com.lavajato.model;

public class Servico {
    private static int proximoId = 1;
    private int id;
    private String descricao;
    private double preco;

    public Servico(String descricao, double preco) {
        this.id = proximoId++;
        this.descricao = descricao;
        this.preco = preco;
    }

    // Usado para criar uma cópia para a Ordem de Serviço ou para atualizar
    public Servico(int id, String descricao, double preco) {
        this.id = id;
        this.descricao = descricao;
        this.preco = preco;
    }

    // Getters
    public int getId() { return id; }
    public String getDescricao() { return descricao; }
    public double getPreco() { return preco; }

    // Setters
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public void setPreco(double preco) { this.preco = preco; }

    @Override
    public String toString() {
        return String.format("ID: %d | Serviço: %s | Preço Padrão: R$ %.2f", id, descricao, preco);
    }
}