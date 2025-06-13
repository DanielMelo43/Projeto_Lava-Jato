package com.lavajato.api;

import com.lavajato.model.Produto;
import java.util.List;
import java.util.Optional;

public interface IProdutoRepository {
    void adicionar(Produto produto);
    List<Produto> listarTodos();
    Optional<Produto> buscarPorId(int id);
    void atualizar(Produto produto);
}