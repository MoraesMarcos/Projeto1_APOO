package app;

import model.*;
// Importações para relatórios (mantidas como estavam)
import relatorio.RelatorioJSON;
import relatorio.RelatorioTexto;
import util.Entrada;

// Importações para Abstract Factory (Frete)
import servico.FabricaFrete;
import servico.FabricaFretePeso;
import servico.FabricaFreteDistancia;
import servico.FreteCalculadora;

// Importações para Abstract Factory (Notificação)
import servico.FabricaNotificador;
import servico.FabricaNotificadorEmail;
import servico.FabricaNotificadorSMS;
import servico.FabricaNotificadorWhatsApp;
import servico.Notificador;

// Importações para Builder (Pedido)
import servico.PedidoBuilder;
import servico.PedidoConcreteBuilder;

import java.util.ArrayList;
import java.util.List;

public class Main {
    static List<Cliente> clientes = new ArrayList<>();
    static List<Produto> produtos = new ArrayList<>();
    static List<Pedido> pedidos = new ArrayList<>();

    public static void main(String[] args) {
        boolean executando = true;

        while (executando) {
            System.out.println("\n========= SISTEMA DE PEDIDOS =========");
            System.out.println("1 - Gerenciar Clientes");
            System.out.println("2 - Gerenciar Produtos");
            System.out.println("3 - Criar Pedido");
            System.out.println("4 - Confirmar Pedido e Notificar");
            System.out.println("5 - Gerar Relatório de Pedido");
            System.out.println("0 - Sair");
            System.out.println("======================================");

            int opcao = Entrada.lerInt("Escolha uma opção: ");

            switch (opcao) {
                case 1 -> menuClientes();
                case 2 -> menuProdutos();
                case 3 -> criarPedido();
                case 4 -> confirmarPedido();
                case 5 -> gerarRelatorio();
                case 0 -> {
                    System.out.println("Sistema finalizado.");
                    executando = false;
                }
                default -> System.out.println("Opção inválida.");
            }
        }
        Entrada.fecharScanner(); // Garante que o scanner seja fechado ao sair.
    }

    // Menu de Clientes
    static void menuClientes() {
        System.out.println("\n--- Cadastro de Cliente ---");

        String nome = Entrada.lerString("Nome: ");

        String cpf;
        while (true) {
            cpf = Entrada.lerString("CPF (somente números, 11 dígitos): ");
            if (cpf.matches("\\d{11}")) {
                break;
            } else {
                System.out.println("CPF inválido. Digite exatamente 11 números.");
            }
        }

        String email;
        while (true) {
            email = Entrada.lerString("Email: ");
            if (email.contains("@") && email.contains(".") && !email.startsWith("@") && !email.endsWith(".")) {
                break;
            } else {
                System.out.println("Email inválido. Digite um endereço válido (ex: nome@dominio.com).");
            }
        }

        String telefone;
        while (true) {
            telefone = Entrada.lerString("Telefone (DDD + número, 11 dígitos): ");
            if (telefone.matches("\\d{11}")) {
                break;
            } else {
                System.out.println("Telefone inválido. Deve conter exatamente 11 dígitos (ex: 81912345678).");
            }
        }

        clientes.add(new Cliente(nome, cpf, email, telefone));
        System.out.println("Cliente cadastrado com sucesso!");
    }

    // Menu de Produtos
    static void menuProdutos() {
        System.out.println("\n--- Cadastro de Produto ---");
        String nome = Entrada.lerString("Nome: ");
        double preco = Entrada.lerDouble("Preço: ");
        double peso = Entrada.lerDouble("Peso (kg): ");
        produtos.add(new Produto(nome, preco, peso));
        System.out.println("Produto cadastrado com sucesso!");
    }

    // Criar Pedido
    static void criarPedido() {
        if (clientes.isEmpty() || produtos.isEmpty()) {
            System.out.println("É necessário ter pelo menos um cliente e um produto cadastrados.");
            return;
        }

        System.out.println("\n--- Criação de Pedido ---");
        exibirClientes();
        int clienteIndex = Entrada.lerInt("Escolha o cliente pelo número: ") - 1;
        if (!indiceValido(clienteIndex, clientes.size())) {
            System.out.println("Cliente inválido.");
            return;
        }

        // 1. Inicia o Builder com o cliente
        PedidoBuilder builder = new PedidoConcreteBuilder()
                .comCliente(clientes.get(clienteIndex));

        boolean adicionando = true;
        while (adicionando) {
            exibirProdutos();
            int produtoIndex = Entrada.lerInt("Escolha o produto (0 para sair): ") - 1;
            if (produtoIndex == -1) break;
            if (!indiceValido(produtoIndex, produtos.size())) {
                System.out.println("Produto inválido.");
                continue;
            }
            int qtd = Entrada.lerInt("Quantidade: ");
            if (qtd > 0) {
                builder.adicionarItem(produtos.get(produtoIndex), qtd);
            } else {
                System.out.println("Quantidade inválida.");
            }
        }

        // 2. Constrói um pedido temporário para calcular o frete (especialmente por peso)
        // Isso é necessário porque o cálculo de frete por peso depende dos itens já adicionados.
        Pedido pedidoTemporarioParaFrete = builder.build();

        double frete = 0;
        System.out.println("\nEscolha o tipo de frete:");
        System.out.println("1 - Por peso total (R$ 5,00/kg)");
        System.out.println("2 - Por distância (R$ 0,50/km)");
        int tipoFrete = Entrada.lerInt("Opção: ");

        FabricaFrete fabricaFrete;
        FreteCalculadora calculadoraFrete;

        // 3. Usa a Abstract Factory para criar a calculadora de frete
        if (tipoFrete == 1) {
            fabricaFrete = new FabricaFretePeso();
            calculadoraFrete = fabricaFrete.criarCalculadoraFrete();
            frete = calculadoraFrete.calcular(pedidoTemporarioParaFrete); // Calcula com base no pedido (peso total)
        } else if (tipoFrete == 2) {
            fabricaFrete = new FabricaFreteDistancia();
            calculadoraFrete = fabricaFrete.criarCalculadoraFrete();
            double distancia = Entrada.lerDouble("Distância (km): ");
            frete = calculadoraFrete.calcular(distancia); // Calcula com base na distância
        } else {
            System.out.println("Opção de frete inválida. Valor zerado.");
        }

        // 4. Finaliza a construção do Pedido com o frete calculado
        Pedido pedidoFinal = builder.comFrete(frete).build();

        if (pedidoFinal.getItens().isEmpty()) {
            System.out.println("Pedido vazio. Cancelado.");
            return;
        }

        pedidos.add(pedidoFinal);

        System.out.println("\n✅ Pedido criado com sucesso!");
        System.out.printf("Total produtos: R$ %.2f\n", pedidoFinal.calcularTotalProdutos());
        System.out.printf("Frete: R$ %.2f\n", pedidoFinal.getFrete());
        System.out.printf("Total com frete: R$ %.2f\n", pedidoFinal.getTotalComFrete());
    }

    // Confirmar Pedido e Notificar
    static void confirmarPedido() {
        if (pedidos.isEmpty()) {
            System.out.println("Nenhum pedido encontrado.");
            return;
        }

        exibirPedidos();
        int index = Entrada.lerInt("Escolha o pedido: ") - 1;
        if (!indiceValido(index, pedidos.size())) {
            System.out.println("Pedido inválido.");
            return;
        }

        Pedido pedido = pedidos.get(index);

        System.out.println("Tipo de notificação:");
        System.out.println("1 - Email");
        System.out.println("2 - SMS");
        System.out.println("3 - WhatsApp");
        int tipo = Entrada.lerInt("Escolha: ");

        FabricaNotificador fabricaNotificador;
        Notificador notificador;

        // Usa a Abstract Factory para criar o notificador
        switch (tipo) {
            case 1 -> fabricaNotificador = new FabricaNotificadorEmail();
            case 2 -> fabricaNotificador = new FabricaNotificadorSMS();
            case 3 -> fabricaNotificador = new FabricaNotificadorWhatsApp();
            default -> {
                System.out.println("Tipo inválido. Nenhuma notificação enviada.");
                return;
            }
        }
        notificador = fabricaNotificador.criarNotificador();
        notificador.notificar(pedido.getCliente());
    }

    // Gerar Relatório
    static void gerarRelatorio() {
        if (pedidos.isEmpty()) {
            System.out.println("Nenhum pedido encontrado.");
            return;
        }

        exibirPedidos();
        int index = Entrada.lerInt("Escolha o pedido: ") - 1;
        if (!indiceValido(index, pedidos.size())) {
            System.out.println("Pedido inválido.");
            return;
        }

        Pedido pedido = pedidos.get(index);

        System.out.println("Formato do relatório:");
        System.out.println("1 - Texto Simples");
        System.out.println("2 - JSON");
        int tipo = Entrada.lerInt("Escolha: ");

        if (tipo == 1) {
            System.out.println("\n--- Relatório Texto ---");
            System.out.println(new RelatorioTexto().gerar(pedido));
        } else if (tipo == 2) {
            System.out.println("\n--- Relatório JSON ---");
            System.out.println(new RelatorioJSON().gerar(pedido));
        } else {
            System.out.println("Formato inválido.");
        }
    }

    // Utilitários de exibição
    static void exibirClientes() {
        System.out.println("\nClientes:");
        for (int i = 0; i < clientes.size(); i++) {
            System.out.printf("%d - %s\n", i + 1, clientes.get(i).getNome());
        }
    }

    static void exibirProdutos() {
        System.out.println("\nProdutos:");
        for (int i = 0; i < produtos.size(); i++) {
            Produto p = produtos.get(i);
            System.out.printf("%d - %s | R$ %.2f | %.2f kg\n", i + 1, p.getNome(), p.getPreco(), p.getPeso());
        }
    }

    static void exibirPedidos() {
        System.out.println("\nPedidos:");
        for (int i = 0; i < pedidos.size(); i++) {
            Pedido p = pedidos.get(i);
            System.out.printf("%d - Cliente: %s | Total: R$ %.2f\n", i + 1, p.getCliente().getNome(), p.getTotalComFrete());
        }
    }

    static boolean indiceValido(int index, int tamanho) {
        return index >= 0 && index < tamanho;
    }
}