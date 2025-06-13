package com.lavajato.view;

import com.lavajato.model.*;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class AppView {
    private final Scanner sc = new Scanner(System.in);

    public int exibirMenuPrincipal() {
        System.out.println("\n===== Lava Jato Brilho Rápido =====");
        System.out.println("1. Gestão de Clientes");
        System.out.println("2. Gestão de Serviços");
        System.out.println("3. Gestão de Produtos");
        System.out.println("4. Criar Ordem de Serviço");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
        int opcao = sc.nextInt();
        sc.nextLine(); // Limpa o buffer
        return opcao;
    }

    public int exibirMenuClientes() {
        System.out.println("\n--- Gestão de Clientes ---");
        System.out.println("1. Cadastrar Novo Cliente");
        System.out.println("2. Listar Clientes");
        System.out.println("3. Modificar Cliente");
        System.out.println("4. Remover Cliente");
        System.out.println("9. Voltar ao Menu Principal");
        System.out.print("Escolha uma opção: ");
        int opcao = sc.nextInt();
        sc.nextLine();
        return opcao;
    }

    public int exibirMenuServicos() {
        System.out.println("\n--- Gestão de Serviços do Catálogo ---");
        System.out.println("1. Adicionar Novo Serviço ao Catálogo");
        System.out.println("2. Listar Todos os Serviços");
        System.out.println("3. Modificar um Serviço");
        System.out.println("4. Remover um Serviço");
        System.out.println("9. Voltar ao Menu Principal");
        System.out.print("Escolha uma opção: ");
        int opcao = sc.nextInt();
        sc.nextLine();
        return opcao;
    }

    public int exibirMenuProdutos() {
        System.out.println("\n--- Gestão de Produtos ---");
        System.out.println("1. Adicionar Novo Produto");
        System.out.println("2. Listar Produtos em Estoque");
        System.out.println("3. Atualizar Estoque de um Produto");
        System.out.println("9. Voltar ao Menu Principal");
        System.out.print("Escolha uma opção: ");
        int opcao = sc.nextInt();
        sc.nextLine();
        return opcao;
    }

    public void exibirMensagem(String mensagem) {
        System.out.println(mensagem);
    }

    public Cliente obterDadosCliente() {
        System.out.println("\n--- Cadastro de Cliente ---");
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("CPF: ");
        String cpf = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Telefone: ");
        String telefone = sc.nextLine();
        return new Cliente(nome, cpf, email, telefone);
    }

    public Veiculo obterDadosVeiculo(Cliente dono) {
        System.out.println("\n--- Cadastro do Veículo ---");
        System.out.print("Marca: ");
        String marca = sc.nextLine();
        System.out.print("Modelo: ");
        String modelo = sc.nextLine();
        System.out.print("Placa: ");
        String placa = sc.nextLine();
        System.out.print("Ano: ");
        int ano = sc.nextInt();
        sc.nextLine();
        System.out.print("Cor: ");
        String cor = sc.nextLine();
        return new Veiculo(marca, modelo, placa, ano, cor, dono);
    }

    public void exibirListaClientes(List<Cliente> clientes) {
        System.out.println("\n--- Lista de Clientes Cadastrados ---");
        if (clientes.isEmpty()) {
            System.out.println(">>> Nenhum cliente encontrado.");
            return;
        }
        for (Cliente cliente : clientes) {
            System.out.println("---------------------------------------");
            System.out.printf("Nome: %s\n", cliente.getNome());
            System.out.printf("CPF: %s | Telefone: %s | Email: %s\n",
                    cliente.getCpf(), cliente.getTelefone(), cliente.getEmail());

            Veiculo veiculo = cliente.getVeiculo();
            if (veiculo != null) {
                System.out.printf("  Veículo: %s %s (%d) - Placa: %s - Cor: %s\n",
                        veiculo.getMarca(), veiculo.getModelo(), veiculo.getAno(),
                        veiculo.getPlaca(), veiculo.getCor());
            } else {
                System.out.println("  Veículo: (Nenhum veículo cadastrado para este cliente)");
            }
        }
        System.out.println("---------------------------------------");
    }

    public String obterCpfParaBusca(String acao) {
        System.out.printf("\nDigite o CPF do cliente que deseja %s: ", acao);
        return sc.nextLine();
    }

    public Cliente obterNovosDadosCliente(Cliente clienteAntigo) {
        System.out.println("Digite os novos dados (deixe em branco para manter o atual):");

        System.out.printf("Nome atual: %s | Novo nome: ", clienteAntigo.getNome());
        String nome = sc.nextLine();

        System.out.printf("Email atual: %s | Novo email: ", clienteAntigo.getEmail());
        String email = sc.nextLine();

        System.out.printf("Telefone atual: %s | Novo telefone: ", clienteAntigo.getTelefone());
        String telefone = sc.nextLine();

        Cliente clienteAtualizado = new Cliente(
                nome.isEmpty() ? clienteAntigo.getNome() : nome,
                clienteAntigo.getCpf(),
                email.isEmpty() ? clienteAntigo.getEmail() : email,
                telefone.isEmpty() ? clienteAntigo.getTelefone() : telefone
        );
        clienteAtualizado.setVeiculo(clienteAntigo.getVeiculo());
        return clienteAtualizado;
    }

    public Servico obterDadosServico() {
        System.out.println("\n--- Cadastro de Novo Serviço ---");
        System.out.print("Descrição do serviço: ");
        String desc = sc.nextLine();
        System.out.print("Preço Padrão (R$): ");
        double preco = sc.nextDouble();
        sc.nextLine();
        return new Servico(desc, preco);
    }

    public void exibirListaServicos(List<Servico> servicos) {
        System.out.println("\n--- Catálogo de Serviços Disponíveis ---");
        if(servicos.isEmpty()) {
            System.out.println(">>> Nenhum serviço cadastrado.");
            return;
        }
        servicos.forEach(System.out::println);
    }

    public int obterIdServicoParaBusca(String acao) {
        System.out.printf("\nDigite o ID do serviço para %s: ", acao);
        int id = sc.nextInt();
        sc.nextLine();
        return id;
    }

    public Servico obterNovosDadosServico(Servico servicoAntigo) {
        System.out.println("Digite os novos dados (deixe em branco para manter o atual):");

        System.out.printf("Descrição atual: %s | Nova descrição: ", servicoAntigo.getDescricao());
        String desc = sc.nextLine();

        System.out.printf("Preço atual: R$ %.2f | Novo preço: ", servicoAntigo.getPreco());
        String precoStr = sc.nextLine();

        double novoPreco = precoStr.isEmpty() ? servicoAntigo.getPreco() : Double.parseDouble(precoStr);

        return new Servico(
                servicoAntigo.getId(),
                desc.isEmpty() ? servicoAntigo.getDescricao() : desc,
                novoPreco
        );
    }

    public Produto obterDadosProduto() {
        System.out.println("\n--- Cadastro de Novo Produto ---");
        System.out.print("Nome do produto: ");
        String nome = sc.nextLine();
        System.out.print("Descrição: ");
        String desc = sc.nextLine();
        System.out.print("Preço de Venda (R$): ");
        double preco = sc.nextDouble();
        System.out.print("Quantidade em Estoque inicial: ");
        int qtd = sc.nextInt();
        sc.nextLine();
        return new Produto(nome, desc, preco, qtd);
    }

    public void exibirListaProdutos(List<Produto> produtos) {
        System.out.println("\n--- Lista de Produtos em Estoque ---");
        if (produtos.isEmpty()) {
            System.out.println(">>> Nenhum produto cadastrado.");
            return;
        }
        produtos.forEach(System.out::println);
    }

    public int obterIdProdutoParaBusca(String acao) {
        System.out.printf("\nDigite o ID do produto para %s: ", acao);
        int id = sc.nextInt();
        sc.nextLine();
        return id;
    }

    public int obterNovaQuantidadeEstoque() {
        System.out.print("Digite a nova quantidade em estoque: ");
        int qtd = sc.nextInt();
        sc.nextLine();
        return qtd;
    }

    public void exibirNotaFiscal(OrdemServico ordem) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        System.out.println("\n\n========================================");
        System.out.println("          NOTA FISCAL DE SERVIÇO        ");
        System.out.println("      LAVA JATO BRILHO RÁPIDO         ");
        System.out.println("========================================");
        System.out.println("Data/Hora da Emissão: " + ordem.getDataHora().format(formatter));
        System.out.println("----------------------------------------");
        System.out.println("CLIENTE:");
        System.out.println("  Nome: " + ordem.getCliente().getNome());
        System.out.println("  CPF: " + ordem.getCliente().getCpf());
        System.out.println("----------------------------------------");
        System.out.println("VEÍCULO:");
        System.out.println("  Modelo: " + ordem.getVeiculo().getMarca() + " " + ordem.getVeiculo().getModelo());
        System.out.println("  Placa: " + ordem.getVeiculo().getPlaca());

        if (!ordem.getServicos().isEmpty()) {
            System.out.println("----------------------------------------");
            System.out.println("SERVIÇOS PRESTADOS:");
            for (Servico servico : ordem.getServicos()) {
                System.out.printf("  - %-25s R$ %7.2f\n", servico.getDescricao(), servico.getPreco());
            }
        }

        if (!ordem.getProdutosVendidos().isEmpty()) {
            System.out.println("----------------------------------------");
            System.out.println("PRODUTOS VENDIDOS:");
            for (Produto produto : ordem.getProdutosVendidos()) {
                System.out.printf("  - %-25s R$ %7.2f\n", produto.getNome(), produto.getPrecoVenda());
            }
        }

        System.out.println("========================================");
        System.out.printf("VALOR TOTAL: R$ %.2f\n", ordem.getValorTotal());
        System.out.println("========================================");
        System.out.println("Status da Ordem: " + ordem.getStatus());
        System.out.println("\n\n");
    }
}