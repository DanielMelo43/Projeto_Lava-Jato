package com.lavajato.api;

import com.lavajato.model.Servico;
import java.util.List;
import java.util.Optional;

public interface IServicoRepository {
    void adicionar(Servico servico);
    List<Servico> listarTodos();
    Optional<Servico> buscarPorId(int id);
    void atualizar(Servico servico);
    boolean removerPorId(int id);
}