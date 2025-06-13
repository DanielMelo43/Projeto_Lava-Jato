package com.lavajato.repository;

import com.lavajato.model.Produto;
import com.lavajato.api.IProdutoRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProdutoRepositoryImpl implements IProdutoRepository {
    private List<Produto> produtos = new ArrayList<>();

    @Override
    public void adicionar(Produto produto) {
        produtos.add(produto);
    }

    @Override
    public List<Produto> listarTodos() {
        return produtos;
    }

    @Override
    public Optional<Produto> buscarPorId(int id) {
        return produtos.stream().filter(p -> p.getId() == id).findFirst();
    }

    @Override
    public void atualizar(Produto produtoAtualizado) {
        buscarPorId(produtoAtualizado.getId()).ifPresent(produtoAntigo -> {
            int index = produtos.indexOf(produtoAntigo);
            produtos.set(index, produtoAtualizado);
        });
    }
}