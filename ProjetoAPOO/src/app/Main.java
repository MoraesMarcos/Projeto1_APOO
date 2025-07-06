package app;

import model.*;
import relatorio.*;
import servico.*;
import util.Entrada;
import util.Persistencia;

import java.util.*;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Cliente> clientes = new ArrayList<>();
    private static final List<Produto> produtos = new ArrayList<>();
    private static final List<Pedido> pedidos = new ArrayList<>();

    public static void main(String[] args) {
        int opcao;
        do {
            System.out.println("1. Cadastrar Cliente");
            System.out.println("2. Cadastrar Produto");
            System.out.println("3. Criar Pedido");
            System.out.println("0. Sair");
            opcao = Entrada.getInstance().lerInt("Escolha uma opção: ");

            switch (opcao) {
                case 1 -> cadastrarCliente();
                case 2 -> cadastrarProduto();
                case 3 -> criarPedido();
            }
        } while (opcao != 0);
    }

    private static void cadastrarCliente() {
        String nome = Entrada.getInstance().lerTexto("Nome: ");
        String cpf;
        do {
            cpf = Entrada.getInstance().lerTexto("CPF (11 dígitos): ");
        } while (!cpf.matches("\\d{11}"));

        String email;
        do {
            email = Entrada.getInstance().lerTexto("Email: ");
        } while (!email.matches("^.+@.+\\..+$"));

        String telefone;
        do {
            telefone = Entrada.getInstance().lerTexto("Telefone (somente números): ");
        } while (!telefone.matches("\\d{8,15}"));

        clientes.add(new Cliente(nome, cpf, email, telefone));
    }

    private static void cadastrarProduto() {
        String nome = Entrada.getInstance().lerTexto("Nome: ");

        double preco;
        do {
            preco = Entrada.getInstance().lerDouble("Preço: ");
        } while (preco < 0);

        double peso;
        do {
            peso = Entrada.getInstance().lerDouble("Peso (kg): ");
        } while (peso <= 0);

        produtos.add(new Produto(nome, preco, peso));
    }

    private static void criarPedido() {
        Cliente cliente;
        try {
            cliente = selecionarCliente();
        } catch (Exception e) {
            System.out.println("Erro ao selecionar cliente.");
            return;
        }

        Produto produto;
        try {
            produto = selecionarProduto();
        } catch (Exception e) {
            System.out.println("Erro ao selecionar produto.");
            return;
        }

        int quantidade = Entrada.getInstance().lerInt("Quantidade: ");

        FreteEscolhido freteEscolhido = escolherFrete();
        Notificador notificador = escolherNotificador();

        Pedido pedido = new Pedido.PedidoBuilder(cliente)
                .adicionarItem(produto, quantidade)
                .setFrete(freteEscolhido)
                .setNotificador(new NotificadorComLog(notificador))
                .build();

        pedidos.add(pedido);

        pedido.notificar();

        System.out.println("Deseja gerar relatório? 1. Texto  2. JSON  Outro. Não");
        int escolha = Entrada.getInstance().lerInt("Opção: ");
        if (escolha == 1) new RelatorioTexto().gerar(pedido);
        else if (escolha == 2) new RelatorioJSON().gerar(pedido);
    }

    private static FreteEscolhido escolherFrete() {
        System.out.println("Opções de Frete:");
        System.out.println("1. Peso");
        System.out.println("2. Distância");
        int tipoFrete = Entrada.getInstance().lerInt("Escolha: ");

        return switch (tipoFrete) {
            case 1 -> new FretePeso();
            case 2 -> new FreteDistancia(Entrada.getInstance().lerDouble("Distância (km): "));
            default -> {
                System.out.println("Opção inválida. Usando frete por peso.");
                yield new FretePeso();
            }
        };
    }

    private static Notificador escolherNotificador() {
        System.out.println("Tipo de Notificação:");
        System.out.println("1. Email\n2. SMS\n3. WhatsApp");
        int tipoNotif = Entrada.getInstance().lerInt("Escolha: ");
        return FactoryNotificador.criarNotificador(tipoNotif);
    }

    private static Cliente selecionarCliente() {
        for (int i = 0; i < clientes.size(); i++) {
            System.out.println(i + ". " + clientes.get(i).getNome());
        }
        int idx = Entrada.getInstance().lerInt("Escolha um cliente: ");
        return clientes.get(idx);
    }

    private static Produto selecionarProduto() {
        for (int i = 0; i < produtos.size(); i++) {
            System.out.println(i + ". " + produtos.get(i).getNome());
        }
        int idx = Entrada.getInstance().lerInt("Escolha um produto: ");
        return produtos.get(idx);
    }
}
