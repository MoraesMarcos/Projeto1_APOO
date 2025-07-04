package relatorio;

import model.Pedido;
import model.ItemPedido;

public class RelatorioJSON {
    public String gerar(Pedido pedido) {
        StringBuilder json = new StringBuilder();
        json.append("{\n");
        json.append("  \"pedido\": {\n");
        json.append("    \"cliente\": {\n");
        json.append("      \"nome\": \"").append(pedido.getCliente().getNome()).append("\",\n");
        json.append("      \"cpf\": \"").append(pedido.getCliente().getCpf()).append("\"\n");
        json.append("    },\n");
        json.append("    \"itens\": [\n");
        for (int i = 0; i < pedido.getItens().size(); i++) {
            ItemPedido item = pedido.getItens().get(i);
            json.append("      {\n");
            json.append("        \"produto\": \"").append(item.getProduto().getNome()).append("\",\n");
            json.append("        \"quantidade\": ").append(item.getQuantidade()).append(",\n");
            json.append("        \"precoUnitario\": ").append(String.format("%.2f", item.getProduto().getPreco()).replace(",", ".")).append("\n");
            json.append("      }").append(i < pedido.getItens().size() - 1 ? "," : "").append("\n");
        }
        json.append("    ],\n");
        json.append("    \"totalProdutos\": ").append(String.format("%.2f", pedido.calcularTotalProdutos()).replace(",", ".")).append(",\n");
        json.append("    \"frete\": ").append(String.format("%.2f", pedido.getFrete()).replace(",", ".")).append(",\n");
        json.append("    \"totalComFrete\": ").append(String.format("%.2f", pedido.getTotalComFrete()).replace(",", ".")).append("\n");
        json.append("  }\n");
        json.append("}");
        return json.toString();
    }
}