package relatorio;

import model.Pedido;
import model.ItemPedido;

public class RelatorioTexto {
    public String gerar(Pedido pedido) {
        StringBuilder sb = new StringBuilder();
        sb.append("Cliente: ").append(pedido.getCliente().getNome()).append("\nProdutos:\n");
        for (ItemPedido item : pedido.getItens()) {
            sb.append("- ")
                    .append(item.getProduto().getNome())
                    .append(" (")
                    .append(item.getQuantidade())
                    .append("x) - R$ ")
                    .append(String.format("%.2f", item.getSubtotal()))
                    .append("\n");
        }
        sb.append("Total: R$ ").append(String.format("%.2f", pedido.calcularTotalProdutos())).append("\n");
        sb.append("Frete: R$ ").append(String.format("%.2f", pedido.getFrete())).append("\n");
        sb.append("Total com frete: R$ ").append(String.format("%.2f", pedido.getTotalComFrete())).append("\n");
        return sb.toString();
    }
}