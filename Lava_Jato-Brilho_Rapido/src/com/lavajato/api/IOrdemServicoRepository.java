package com.lavajato.api;

import com.lavajato.model.OrdemServico;
import java.util.List;

public interface IOrdemServicoRepository {
    void criarOrdem(OrdemServico ordem);
    List<OrdemServico> listarOrdens();
}