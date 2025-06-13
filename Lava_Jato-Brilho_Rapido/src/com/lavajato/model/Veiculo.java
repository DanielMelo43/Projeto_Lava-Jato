package com.lavajato.model;

public class Veiculo {
    private String marca;
    private String modelo;
    private String placa;
    private int ano;
    private String cor;
    private Cliente dono;

    public Veiculo(String marca, String modelo, String placa, int ano, String cor, Cliente dono) {
        this.marca = marca;
        this.modelo = modelo;
        this.placa = placa;
        this.ano = ano;
        this.cor = cor;
        this.dono = dono;
    }

    // Getters
    public String getMarca() { return marca; }
    public String getModelo() { return modelo; }
    public String getPlaca() { return placa; }
    public int getAno() { return ano; }
    public String getCor() { return cor; }
    public Cliente getDono() { return dono; }
}