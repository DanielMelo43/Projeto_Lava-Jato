package com.lavajato.model;

import java.time.LocalDateTime;
import java.util.List;

public class OrdemServico {
    private Cliente cliente;
    private Veiculo veiculo;
    private List<Servico> servicos;
    private List<Produto> produtosVendidos;
    private String status;
    private double valorTotal;
    private LocalDateTime dataHora;

    public OrdemServico(Cliente cliente, Veiculo veiculo, List<Servico> servicos, List<Produto> produtos, String status) {
        this.cliente = cliente;
        this.veiculo = veiculo;
        this.servicos = servicos;
        this.produtosVendidos = produtos;
        this.status = status;
        this.dataHora = LocalDateTime.now();
        this.valorTotal = calcularValorTotal();
    }

    private double calcularValorTotal() {
        double totalServicos = this.servicos.stream().mapToDouble(Servico::getPreco).sum();
        double totalProdutos = this.produtosVendidos.stream().mapToDouble(Produto::getPrecoVenda).sum();
        return totalServicos + totalProdutos;
    }

    // Getters
    public Cliente getCliente() { return cliente; }
    public Veiculo getVeiculo() { return veiculo; }
    public List<Servico> getServicos() { return servicos; }
    public List<Produto> getProdutosVendidos() { return produtosVendidos; }
    public String getStatus() { return status; }
    public double getValorTotal() { return valorTotal; }
    public LocalDateTime getDataHora() { return dataHora; }
}