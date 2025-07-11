// app/Main.java
package app;

import model.Cliente;
import model.Pedido;
import model.Produto;
import relatorio.RelatorioJson;
import relatorio.RelatorioTxt;
import servico.*;
import util.Entrada;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Main {

    static List<Cliente> clientes = new ArrayList<>();
    static List<Produto> produtos = new ArrayList<>();
    static List<Pedido> pedidos = new ArrayList<>();

    public static void main(String[] args) {
        // O Locale foi setado para garantir que a saída de números seja com ponto
        // e evitar problemas de formatação com vírgulas.
        Locale.setDefault(Locale.US);
        boolean executando = true;

        while (executando) {
            System.out.println("\n ========= SISTEMA DE PEDIDOS ========= ");
            System.out.println("1 - Gerenciar Clientes");
            System.out.println("2 - Gerenciar Produtos");
            System.out.println("3 - Criar Pedido");
            System.out.println("4 - Confirmar Pedido e Notificar");
            System.out.println("5 - Gerar Relatório de Pedido");
            System.out.println("0 - Sair");
            System.out.println("=======================================");

            int opcao = Entrada.lerInt("Escolha uma opção: ");

            switch (opcao) {
                case 1 -> menuClientes();
                case 2 -> menuProdutos();
                case 3 -> criarPedido();
                case 4 -> confirmarPedido();
                case 5 -> gerarRelatorio();
                case 0 -> {
                    System.out.println(" Sistema finalizado.");
                    executando = false;
                }
                default -> System.out.println("Opção inválida.");
            }
        }
    }

    // ... outros métodos ...
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
            if (email.contains("@") && email.contains(".")) {
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

    static void menuProdutos() {
        System.out.println("\n--- Cadastro de Produto ---");
        String nome = Entrada.lerString("Nome: ");
        double preco = Entrada.lerDouble("Preço: ");
        double peso = Entrada.lerDouble("Peso (kg): ");
        produtos.add(new Produto(nome, preco, peso));
        System.out.println("Produto cadastrado com sucesso!");
    }

    static void criarPedido() {
        if (clientes.isEmpty()) {
            System.out.println("ERRO: É necessário ter pelo menos um cliente cadastrado.");
            return;
        }
        if (produtos.isEmpty()) {
            System.out.println("ERRO: É necessário ter pelo menos um produto cadastrado.");
            return;
        }


        System.out.println("\n--- Criação de Pedido ---");
        exibirClientes();
        int clienteIndex = Entrada.lerInt("Escolha o cliente pelo número: ") - 1;
        if (!indiceValido(clienteIndex, clientes.size())) {
            System.out.println("Cliente inválido.");
            return;
        }

        Pedido pedido = new Pedido(clientes.get(clienteIndex));

        while (true) {
            exibirProdutos();
            int produtoIndex = Entrada.lerInt("Escolha o produto (0 para finalizar): ") - 1;
            if (produtoIndex == -1) break; // Finaliza a adição de itens
            if (!indiceValido(produtoIndex, produtos.size())) {
                System.out.println("Produto inválido.");
                continue;
            }

            int qtd = Entrada.lerInt("Quantidade: ");
            if (qtd > 0) {
                // LÓGICA ORIGINAL: Simplesmente adiciona um novo item
                pedido.adicionarItem(produtos.get(produtoIndex), qtd);
            } else {
                System.out.println("Quantidade deve ser maior que zero.");
            }
        }

        if (pedido.getItens().isEmpty()) {
            System.out.println("Pedido vazio. Operação cancelada.");
            return;
        }

        System.out.println("\nEscolha o tipo de frete:");
        System.out.println("1 - Por peso total (R$ 5,00/kg)");
        System.out.println("2 - Por distância (R$ 0,50/km)");
        int tipoFrete = Entrada.lerInt("Opção: ");
        double frete = 0;

        if (tipoFrete == 1) {
            frete = new FretePeso().calcular(pedido);
        } else if (tipoFrete == 2) {
            double distancia = Entrada.lerDouble("Distância (km): ");
            frete = new FreteDistancia().calcular(pedido, distancia);
        } else {
            System.out.println("Opção de frete inválida. Valor do frete será R$ 0,00.");
        }

        pedido.setFrete(frete);
        pedidos.add(pedido);
        System.out.println("Pedido criado com sucesso!");
        System.out.printf("Total produtos: R$ %.2f\n", pedido.calcularTotalProdutos());
        System.out.printf("Frete: R$ %.2f\n", pedido.getFrete());
        System.out.printf("Total com frete: R$ %.2f\n", pedido.getTotalComFrete());
    }

    static void confirmarPedido() {
        if (pedidos.isEmpty()) {
            System.out.println("Nenhum pedido encontrado para confirmar.");
            return;
        }

        exibirPedidos();
        int index = Entrada.lerInt("Escolha o pedido a ser notificado: ") - 1;
        if (!indiceValido(index, pedidos.size())) {
            System.out.println("Pedido inválido.");
            return;
        }

        Pedido pedido = pedidos.get(index);
        System.out.println("\nTipo de notificação:");
        System.out.println("1 - Email");
        System.out.println("2 - SMS");
        System.out.println("3 - WhatsApp");
        int tipo = Entrada.lerInt("Escolha: ");

        switch (tipo) {
            case 1 -> new NotificarEmail().notificar(pedido.getCliente());
            case 2 -> new NotificarSMS().notificar(pedido.getCliente());
            case 3 -> new NotificarWhatsApp().notificar(pedido.getCliente());
            default -> System.out.println("Tipo de notificação inválido.");
        }
    }

    static void gerarRelatorio() {
        if (pedidos.isEmpty()) {
            System.out.println("Nenhum pedido encontrado para gerar relatório.");
            return;
        }

        exibirPedidos();
        int index = Entrada.lerInt("Escolha o pedido para gerar o relatório: ") - 1;
        if (!indiceValido(index, pedidos.size())) {
            System.out.println("Pedido inválido.");
            return;
        }

        Pedido pedido = pedidos.get(index);
        System.out.println("\nFormato do relatório:");
        System.out.println("1 - Texto Simples");
        System.out.println("2 - JSON");
        int tipo = Entrada.lerInt("Escolha: ");

        if (tipo == 1) {
            System.out.println("\n--- Relatório Texto ---");
            System.out.println(new RelatorioTxt().gerar(pedido));
        } else if (tipo == 2) {
            System.out.println("\n--- Relatório JSON ---");
            // Usando a classe RelatorioJson corrigida
            System.out.println(new RelatorioJson().gerar(pedido));
        } else {
            System.out.println("Formato inválido.");
        }
    }

    // ... métodos de exibição ...
    static void exibirClientes() {
        System.out.println("\nClientes Cadastrados:");
        for (int i = 0; i < clientes.size(); i++) {
            System.out.printf("%d - %s\n", i + 1, clientes.get(i).getNome());
        }
    }

    static void exibirProdutos() {
        System.out.println("\nProdutos Cadastrados:");
        for (int i = 0; i < produtos.size(); i++) {
            Produto p = produtos.get(i);
            System.out.printf("%d - %s | R$ %.2f | %.2f kg\n", i + 1, p.getNome(), p.getPreco(), p.getPeso());
        }
    }

    static void exibirPedidos() {
        System.out.println("\nPedidos Criados:");
        for (int i = 0; i < pedidos.size(); i++) {
            Pedido p = pedidos.get(i);
            System.out.printf("%d - Cliente: %s | Total: R$ %.2f\n", i + 1, p.getCliente().getNome(), p.getTotalComFrete());
        }
    }

    static boolean indiceValido(int index, int tamanhoLista) {
        return index >= 0 && index < tamanhoLista;
    }
}