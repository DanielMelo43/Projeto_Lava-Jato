package com.lavajato.api;

import com.lavajato.model.Cliente;
import java.util.List;
import java.util.Optional;

public interface IClienteRepository {
    void cadastrarCliente(Cliente cliente);
    List<Cliente> listarClientes();
    Optional<Cliente> buscarPorCpf(String cpf);
    boolean removerPorCpf(String cpf);
    boolean atualizarCliente(String cpf, Cliente clienteAtualizado);
}