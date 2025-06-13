package com.lavajato.repository;

import com.lavajato.model.OrdemServico;
import com.lavajato.api.IOrdemServicoRepository;
import java.util.ArrayList;
import java.util.List;

public class OrdemServicoRepositoryImpl implements IOrdemServicoRepository {
    private List<OrdemServico> ordens = new ArrayList<>();

    @Override
    public void criarOrdem(OrdemServico ordem) {
        ordens.add(ordem);
    }

    @Override
    public List<OrdemServico> listarOrdens() {
        return ordens;
    }
}