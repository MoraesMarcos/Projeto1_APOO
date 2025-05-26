package relatorio;

import model.ItemPedido;

public class RelatorioJSON {
    public String gerar(Pedido pedido) {
        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        sb.append("  \"cliente\": \"").append(pedido.getCliente().getNome()).append("\",\n");
        sb.append("  \"produtos\": [\n");
        for (int i = 0; i < pedido.getItens().size(); i++) {
            ItemPedido item = pedido.getItens().get(i);
            sb.append("    {\"nome\": \"").append(item.getProduto().getNome()).append("\", \"quantidade\": ")
                    .append(item.getQuantidade()).append(", \"preco\": ").append(item.getProduto().getPreco()).append("}");
            if (i < pedido.getItens().size() - 1) {
                sb.append(",");
            }
            sb.append("\n");
        }
        sb.append("  ],\n");
        sb.append("  \"total\": ").append(pedido.calcularTotalProdutos()).append(",\n");
        sb.append("  \"frete\": ").append(pedido.getFrete()).append(",\n");
        sb.append("  \"total_com_frete\": ").append(pedido.getTotalComFrete()).append("\n");
        sb.append("}");
        return sb.toString();
    }
}