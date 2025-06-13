package com.lavajato.model;

public class Cliente {
    private String nome;
    private String cpf;
    private String email;
    private String telefone;
    private Veiculo veiculo;

    public Cliente(String nome, String cpf, String email, String telefone) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
    }

    // Getters
    public String getNome() { return nome; }
    public String getCpf() { return cpf; }
    public String getEmail() { return email; }
    public String getTelefone() { return telefone; }
    public Veiculo getVeiculo() { return veiculo; }

    // Setters
    public void setNome(String nome) { this.nome = nome; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    public void setEmail(String email) { this.email = email; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    public void setVeiculo(Veiculo veiculo) { this.veiculo = veiculo; }
}