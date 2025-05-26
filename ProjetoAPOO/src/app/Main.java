package app;

import model.Cliente;
import model.Produto;
import model.Pedido;
import servico.FreteCalculadoraPeso;
import servico.FreteCalculadoraDistancia;
import servico.NotificadorEmail;
import servico.NotificadorSMS;
import servico.NotificadorWhatsApp;
import relatorio.RelatorioTexto;
import relatorio.RelatorioJSON;
import util.Entrada;

import java.util.ArrayList;
import java.util.List;

public class Main {
    static List<Cliente> clientes = new ArrayList<>();
    static List<Produto> produtos = new ArrayList<>();
    static List<Pedido> pedidos = new ArrayList<>();

    public static void main(String[] args) {
        boolean rodando = true;

        while (rodando) {
            System.out.println("\n--- Sistema de Pedidos ---");
            System.out.println("1 - Cadastrar Cliente");
            System.out.println("2 - Cadastrar Produto");
            System.out.println("3 - Criar Pedido");
            System.out.println("4 - Confirmar Pedido (Notificar)");
            System.out.println("5 - Gerar Relatório");
            System.out.println("0 - Sair");

            int opcao = Entrada.lerInt("Escolha uma opção: ");

            switch (opcao) {
                case 1 -> cadastrarCliente();
                case 2 -> cadastrarProduto();
                case 3 -> criarPedido();
                case 4 -> confirmarPedido();
                case 5 -> gerarRelatorio();
                case 0 -> {
                    System.out.println("Encerrando sistema.");
                    rodando = false;
                }
                default -> System.out.println("Opção inválida.");
            }
        }
    }

    static void cadastrarCliente() {
        System.out.println("\nCadastro de Cliente");
        String nome = Entrada.lerString("Nome: ");
        String cpf = Entrada.lerString("CPF: ");
        String email = Entrada.lerString("Email: ");
        clientes.add(new Cliente(nome, cpf, email));
        System.out.println("Cliente cadastrado com sucesso!");
    }

    static void cadastrarProduto() {
        System.out.println("\nCadastro de Produto");
        String nome = Entrada.lerString("Nome: ");
        double preco = Entrada.lerDouble("Preço: ");
        double peso = Entrada.lerDouble("Peso (kg): ");
        produtos.add(new Produto(nome, preco, peso));
        System.out.println("Produto cadastrado com sucesso!");
    }

    static void criarPedido() {
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado. Cadastre um cliente primeiro.");
            return;
        }
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado. Cadastre um produto primeiro.");
            return;
        }

        System.out.println("\nCriar Pedido");
        System.out.println("Clientes:");
        for (int i = 0; i < clientes.size(); i++) {
            System.out.println((i + 1) + " - " + clientes.get(i).getNome());
        }
        int indCliente = Entrada.lerInt("Escolha o cliente pelo número: ") - 1;
        if (indCliente < 0 || indCliente >= clientes.size()) {
            System.out.println("Cliente inválido.");
            return;
        }
        Cliente cliente = clientes.get(indCliente);
        Pedido pedido = new Pedido(cliente);

        boolean adicionando = true;
        while (adicionando) {
            System.out.println("\nProdutos:");
            for (int i = 0; i < produtos.size(); i++) {
                Produto p = produtos.get(i);
                System.out.println((i + 1) + " - " + p.getNome() + " - R$ " + String.format("%.2f", p.getPreco()));
            }
            int indProduto = Entrada.lerInt("Escolha o produto pelo número (0 para terminar): ") - 1;
            if (indProduto == -1) {
                adicionando = false;
                break;
            }
            if (indProduto < 0 || indProduto >= produtos.size()) {
                System.out.println("Produto inválido.");
                continue;
            }
            Produto produto = produtos.get(indProduto);
            int qtd = Entrada.lerInt("Quantidade: ");
            if (qtd <= 0) {
                System.out.println("Quantidade inválida.");
                continue;
            }
            pedido.adicionarItem(produto, qtd);
        }

        if (pedido.getItens().isEmpty()) {
            System.out.println("Nenhum produto adicionado ao pedido. Cancelando.");
            return;
        }

        // Escolha cálculo de frete
        System.out.println("\nEscolha o cálculo do frete:");
        System.out.println("1 - Por peso total (R$ 5,00 por kg)");
        System.out.println("2 - Fixo por distância (R$ 0,50 por km)");
        int freteOp = Entrada.lerInt("Opção: ");

        double frete = 0;
        if (freteOp == 1) {
            FreteCalculadoraPeso calcPeso = new FreteCalculadoraPeso();
            frete = calcPeso.calcular(pedido);
        } else if (freteOp == 2) {
            double distancia = Entrada.lerDouble("Informe a distância em km: ");
            FreteCalculadoraDistancia calcDist = new FreteCalculadoraDistancia();
            frete = calcDist.calcular(pedido, distancia);
        } else {
            System.out.println("Opção inválida para frete. Frete definido como zero.");
        }

        pedido.setFrete(frete);
        pedidos.add(pedido);

        System.out.println("Pedido criado com sucesso!");
        System.out.println("Total produtos: R$ " + String.format("%.2f", pedido.calcularTotalProdutos()));
        System.out.println("Frete: R$ " + String.format("%.2f", pedido.getFrete()));
        System.out.println("Total com frete: R$ " + String.format("%.2f", pedido.getTotalComFrete()));
    }

    static void confirmarPedido() {
        if (pedidos.isEmpty()) {
            System.out.println("Nenhum pedido criado ainda.");
            return;
        }
        System.out.println("\nPedidos:");
        for (int i = 0; i < pedidos.size(); i++) {
            Pedido p = pedidos.get(i);
            System.out.println((i + 1) + " - Cliente: " + p.getCliente().getNome() + ", Total: R$ " + String.format("%.2f", p.getTotalComFrete()));
        }
        int ind = Entrada.lerInt("Escolha o pedido para confirmar: ") - 1;
        if (ind < 0 || ind >= pedidos.size()) {
            System.out.println("Pedido inválido.");
            return;
        }
        Pedido pedido = pedidos.get(ind);

        System.out.println("Escolha tipo de notificação:");
        System.out.println("1 - Email");
        System.out.println("2 - SMS");
        System.out.println("3 - WhatsApp");
        int tipo = Entrada.lerInt("Opção: ");

        switch (tipo) {
            case 1 -> new NotificadorEmail().notificar(pedido.getCliente());
            case 2 -> new NotificadorSMS().notificar(pedido.getCliente());
            case 3 -> new NotificadorWhatsApp().notificar(pedido.getCliente());
            default -> System.out.println("Tipo inválido. Nenhuma notificação enviada.");
        }
    }

    static void gerarRelatorio() {
        if (pedidos.isEmpty()) {
            System.out.println("Nenhum pedido criado ainda.");
            return;
        }
        System.out.println("\nPedidos:");
        for (int i = 0; i < pedidos.size(); i++) {
            Pedido p = pedidos.get(i);
            System.out.println((i + 1) + " - Cliente: " + p.getCliente().getNome() + ", Total: R$ " + String.format("%.2f", p.getTotalComFrete()));
        }
        int ind = Entrada.lerInt("Escolha o pedido para gerar relatório: ") - 1;
        if (ind < 0 || ind >= pedidos.size()) {
            System.out.println("Pedido inválido.");
            return;
        }
        Pedido pedido = pedidos.get(ind);

        System.out.println("Formato do relatório:");
        System.out.println("1 - Texto simples");
        System.out.println("2 - JSON");
        int formato = Entrada.lerInt("Opção: ");

        if (formato == 1) {
            RelatorioTexto relTexto = new RelatorioTexto();
            System.out.println("\n--- Relatório Texto ---");
            System.out.println(relTexto.gerar(pedido));
        } else if (formato == 2) {
            RelatorioJSON relJson = new RelatorioJSON();
            System.out.println("\n--- Relatório JSON ---");
            System.out.println(relJson.gerar(pedido));
        } else {
            System.out.println("Formato inválido.");
        }
    }
}