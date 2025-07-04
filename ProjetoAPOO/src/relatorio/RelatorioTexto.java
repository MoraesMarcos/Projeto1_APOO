package relatorio;

import model.Pedido;
import model.ItemPedido;

public class RelatorioTexto {
    public String gerar(Pedido pedido) {
        StringBuilder relatorio = new StringBuilder();
        relatorio.append("---------- Detalhes do Pedido ----------\n");
        relatorio.append("Cliente: ").append(pedido.getCliente().getNome()).append("\n");
        relatorio.append("CPF: ").append(pedido.getCliente().getCpf()).append("\n");
        relatorio.append("Email: ").append(pedido.getCliente().getEmail()).append("\n");
        relatorio.append("Telefone: ").append(pedido.getCliente().getTelefone()).append("\n");
        relatorio.append("\nItens do Pedido:\n");
        for (ItemPedido item : pedido.getItens()) {
            relatorio.append(String.format("- %s (x%d) | Preço Unitário: R$ %.2f | Subtotal: R$ %.2f\n",
                    item.getProduto().getNome(),
                    item.getQuantidade(),
                    item.getProduto().getPreco(),
                    item.calcularSubtotal()));
        }
        relatorio.append(String.format("\nTotal dos Produtos: R$ %.2f\n", pedido.calcularTotalProdutos()));
        relatorio.append(String.format("Valor do Frete: R$ %.2f\n", pedido.getFrete()));
        relatorio.append(String.format("TOTAL GERAL: R$ %.2f\n", pedido.getTotalComFrete()));
        relatorio.append("--------------------------------------\n");
        return relatorio.toString();
    }
}