package com.lavajato.repository;

import com.lavajato.model.Servico;
import com.lavajato.api.IServicoRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ServicoRepositoryImpl implements IServicoRepository {
    private List<Servico> catalogoServicos = new ArrayList<>();

    @Override
    public void adicionar(Servico servico) {
        catalogoServicos.add(servico);
    }

    @Override
    public List<Servico> listarTodos() {
        return catalogoServicos;
    }

    @Override
    public Optional<Servico> buscarPorId(int id) {
        return catalogoServicos.stream().filter(s -> s.getId() == id).findFirst();
    }

    @Override
    public void atualizar(Servico servicoAtualizado) {
        buscarPorId(servicoAtualizado.getId()).ifPresent(servicoAntigo -> {
            int index = catalogoServicos.indexOf(servicoAntigo);
            catalogoServicos.set(index, servicoAtualizado);
        });
    }

    @Override
    public boolean removerPorId(int id) {
        return catalogoServicos.removeIf(servico -> servico.getId() == id);
    }
}