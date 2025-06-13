package com.lavajato.repository;

import com.lavajato.model.Cliente;
import com.lavajato.api.IClienteRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClienteRepositoryImpl implements IClienteRepository {
    private List<Cliente> clientes = new ArrayList<>();

    @Override
    public void cadastrarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    @Override
    public List<Cliente> listarClientes() {
        return clientes;
    }

    @Override
    public Optional<Cliente> buscarPorCpf(String cpf) {
        return clientes.stream()
                .filter(cliente -> cliente.getCpf().equals(cpf))
                .findFirst();
    }

    @Override
    public boolean removerPorCpf(String cpf) {
        return clientes.removeIf(cliente -> cliente.getCpf().equals(cpf));
    }


    @Override
    public boolean atualizarCliente(String cpf, Cliente clienteAtualizado) {
        Optional<Cliente> clienteOptional = buscarPorCpf(cpf);
        if (clienteOptional.isPresent()) {
            Cliente clienteAntigo = clienteOptional.get();
            int index = clientes.indexOf(clienteAntigo);
            clientes.set(index, clienteAtualizado);
            return true;
        }
        return false;
    }
}