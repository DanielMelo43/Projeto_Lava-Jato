package com.lavajato.controller;

import com.lavajato.model.*;
import com.lavajato.repository.*;
import com.lavajato.view.AppView;

import com.lavajato.api.IClienteRepository;
import com.lavajato.api.IOrdemServicoRepository;
import com.lavajato.api.IProdutoRepository;
import com.lavajato.api.IServicoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class AppController {
    private final AppView view;
    private final IClienteRepository clienteRepo;
    private final IServicoRepository servicoRepo;
    private final IOrdemServicoRepository ordemRepo;
    private final IProdutoRepository produtoRepo;

    public AppController() {
        this.view = new AppView();
        this.clienteRepo = new ClienteRepositoryImpl();
        this.servicoRepo = new ServicoRepositoryImpl();
        this.ordemRepo = new OrdemServicoRepositoryImpl();
        this.produtoRepo = new ProdutoRepositoryImpl();

        // Dados de exemplo para iniciar o sistema
        servicoRepo.adicionar(new Servico("Lavagem Simples", 30.00));
        servicoRepo.adicionar(new Servico("Lavagem Completa com Cera", 50.00));
        servicoRepo.adicionar(new Servico("Higienização Interna", 120.00));
        produtoRepo.adicionar(new Produto("Pretinho para Pneu (Frasco)", "Brilho intenso para pneus", 15.00, 20));
        produtoRepo.adicionar(new Produto("Aromatizante de Carro", "Cheiro de carro novo", 10.00, 50));
    }

    public void iniciar() {
        boolean executando = true;
        while (executando) {
            int opcao = view.exibirMenuPrincipal();
            switch (opcao) {
                case 1: gestaoClientes(); break;
                case 2: gestaoServicos(); break;
                case 3: gestaoProdutos(); break;
                case 4: criarNovaOrdemDeServico(); break;
                case 0: executando = false; view.exibirMensagem("Saindo do sistema..."); break;
                default: view.exibirMensagem("Opção inválida!"); break;
            }
        }
    }

    private void gestaoClientes() {
        boolean clienteMenu = true;
        while (clienteMenu) {
            int opcao = view.exibirMenuClientes();
            switch (opcao) {
                case 1: cadastrarNovoCliente(); break;
                case 2: listarClientes(); break;
                case 3: modificarCliente(); break;
                case 4: removerCliente(); break;
                case 9: clienteMenu = false; break;
                default: view.exibirMensagem("Opção inválida!"); break;
            }
        }
    }

    private void gestaoServicos() {
        boolean servicoMenu = true;
        while(servicoMenu) {
            int opcao = view.exibirMenuServicos();
            switch (opcao) {
                case 1: adicionarNovoServico(); break;
                case 2: listarTodosServicos(); break;
                case 3: modificarServico(); break;
                case 4: removerServico(); break;
                case 9: servicoMenu = false; break;
                default: view.exibirMensagem("Opção inválida!"); break;
            }
        }
    }

    private void gestaoProdutos() {
        boolean produtoMenu = true;
        while(produtoMenu) {
            int opcao = view.exibirMenuProdutos();
            switch (opcao) {
                case 1: adicionarNovoProduto(); break;
                case 2: listarTodosProdutos(); break;
                case 3: atualizarEstoqueProduto(); break;
                case 9: produtoMenu = false; break;
                default: view.exibirMensagem("Opção inválida!"); break;
            }
        }
    }

    private void cadastrarNovoCliente() {
        Cliente novoCliente = view.obterDadosCliente();
        if (clienteRepo.buscarPorCpf(novoCliente.getCpf()).isPresent()) {
            view.exibirMensagem("ERRO: CPF já cadastrado no sistema.");
            return;
        }
        Veiculo novoVeiculo = view.obterDadosVeiculo(novoCliente);
        novoCliente.setVeiculo(novoVeiculo);
        clienteRepo.cadastrarCliente(novoCliente);
        view.exibirMensagem("Cliente e veículo cadastrados com sucesso!");
    }

    private void listarClientes() {
        List<Cliente> clientes = clienteRepo.listarClientes();
        view.exibirListaClientes(clientes);
    }

    private void modificarCliente() {
        view.exibirMensagem("--- Modificação de Cliente ---");
        String cpf = view.obterCpfParaBusca("modificar");

        Optional<Cliente> clienteOptional = clienteRepo.buscarPorCpf(cpf);

        if (clienteOptional.isPresent()) {
            Cliente clienteAntigo = clienteOptional.get();
            Cliente clienteAtualizado = view.obterNovosDadosCliente(clienteAntigo);
            clienteRepo.atualizarCliente(cpf, clienteAtualizado);
            view.exibirMensagem("Dados do cliente atualizados com sucesso!");
        } else {
            view.exibirMensagem("ERRO: Cliente com o CPF informado não encontrado.");
        }
    }

    private void removerCliente() {
        view.exibirMensagem("--- Remoção de Cliente ---");
        String cpf = view.obterCpfParaBusca("remover");

        boolean removido = clienteRepo.removerPorCpf(cpf);

        if (removido) {
            view.exibirMensagem("Cliente removido com sucesso!");
        } else {
            view.exibirMensagem("ERRO: Cliente com o CPF informado não encontrado.");
        }
    }

    private void adicionarNovoServico() {
        Servico servico = view.obterDadosServico();
        servicoRepo.adicionar(servico);
        view.exibirMensagem("Serviço adicionado ao catálogo com sucesso!");
    }

    private void listarTodosServicos() {
        List<Servico> servicos = servicoRepo.listarTodos();
        view.exibirListaServicos(servicos);
    }

    private void modificarServico() {
        listarTodosServicos();
        int id = view.obterIdServicoParaBusca("modificar");
        Optional<Servico> servicoOptional = servicoRepo.buscarPorId(id);

        if(servicoOptional.isPresent()){
            Servico servicoAntigo = servicoOptional.get();
            Servico servicoAtualizado = view.obterNovosDadosServico(servicoAntigo);
            servicoRepo.atualizar(servicoAtualizado);
            view.exibirMensagem("Serviço atualizado com sucesso!");
        } else {
            view.exibirMensagem("ERRO: Serviço com o ID informado não encontrado.");
        }
    }

    private void removerServico() {
        listarTodosServicos();
        int id = view.obterIdServicoParaBusca("remover");
        if(servicoRepo.removerPorId(id)) {
            view.exibirMensagem("Serviço removido com sucesso!");
        } else {
            view.exibirMensagem("ERRO: Serviço não encontrado.");
        }
    }

    private void adicionarNovoProduto() {
        Produto produto = view.obterDadosProduto();
        produtoRepo.adicionar(produto);
        view.exibirMensagem("Produto adicionado com sucesso!");
    }

    private void listarTodosProdutos() {
        List<Produto> produtos = produtoRepo.listarTodos();
        view.exibirListaProdutos(produtos);
    }

    private void atualizarEstoqueProduto() {
        listarTodosProdutos();
        int id = view.obterIdProdutoParaBusca("atualizar o estoque");
        Optional<Produto> produtoOptional = produtoRepo.buscarPorId(id);

        if (produtoOptional.isPresent()) {
            Produto produto = produtoOptional.get();
            int novaQtd = view.obterNovaQuantidadeEstoque();
            produto.setQuantidadeEstoque(novaQtd);
            produtoRepo.atualizar(produto);
            view.exibirMensagem("Estoque atualizado com sucesso!");
        } else {
            view.exibirMensagem("ERRO: Produto com o ID informado não encontrado.");
        }
    }

    private void criarNovaOrdemDeServico() {
        view.exibirMensagem("--- Criar Nova Ordem de Serviço ---");

        String cpf = view.obterCpfParaBusca("iniciar a Ordem de Serviço");
        Optional<Cliente> clienteOptional = clienteRepo.buscarPorCpf(cpf);
        if (!clienteOptional.isPresent()) {
            view.exibirMensagem("ERRO: Nenhum cliente encontrado com este CPF.");
            return;
        }
        Cliente cliente = clienteOptional.get();

        List<Servico> servicosDaOrdem = new ArrayList<>();
        List<Produto> produtosDaOrdem = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        boolean adicionandoItens = true;
        while (adicionandoItens) {
            System.out.println("\n>> Montagem da Ordem de Serviço <<");
            System.out.println("1. Adicionar Serviço");
            System.out.println("2. Adicionar Produto");
            System.out.println("0. Finalizar e Gerar Nota");
            System.out.print("Opção: ");
            String escolha = sc.nextLine();

            switch (escolha) {
                case "1":
                    listarTodosServicos();
                    int idServico = view.obterIdServicoParaBusca("adicionar à ordem");
                    servicoRepo.buscarPorId(idServico).ifPresentOrElse(
                            servico -> {
                                servicosDaOrdem.add(servico);
                                view.exibirMensagem("'" + servico.getDescricao() + "' adicionado com sucesso!");
                            },
                            () -> view.exibirMensagem("ERRO: Serviço não encontrado com este ID.")
                    );
                    break;

                case "2":
                    listarTodosProdutos();
                    int idProduto = view.obterIdProdutoParaBusca("adicionar à ordem");
                    produtoRepo.buscarPorId(idProduto).ifPresentOrElse(
                            produto -> {
                                if (produto.getQuantidadeEstoque() > 0) {
                                    produtosDaOrdem.add(produto);
                                    produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() - 1);
                                    produtoRepo.atualizar(produto);
                                    view.exibirMensagem("'" + produto.getNome() + "' adicionado com sucesso!");
                                } else {
                                    view.exibirMensagem("ERRO: Produto '" + produto.getNome() + "' sem estoque!");
                                }
                            },
                            () -> view.exibirMensagem("ERRO: Produto não encontrado com este ID.")
                    );
                    break;

                case "0":
                    adicionandoItens = false;
                    break;

                default:
                    view.exibirMensagem("Opção inválida. Tente novamente.");
                    break;
            }
        }

        if (servicosDaOrdem.isEmpty() && produtosDaOrdem.isEmpty()) {
            view.exibirMensagem("Nenhum item adicionado. A Ordem de Serviço foi cancelada.");
            return;
        }

        OrdemServico novaOrdem = new OrdemServico(cliente, cliente.getVeiculo(), servicosDaOrdem, produtosDaOrdem, "Pendente");
        ordemRepo.criarOrdem(novaOrdem);

        view.exibirMensagem("\nOrdem de Serviço criada com sucesso!");
        view.exibirNotaFiscal(novaOrdem);
    }
}