package relatorio;

import model.Pedido;

public class RelatorioJSON {
    public void gerar(Pedido pedido) {
        System.out.println("{");
        System.out.println("  \"cliente\": \"" + pedido.getCliente().getNome() + "\", ");
        System.out.println("  \"produtos\": [");
        pedido.getItens().forEach(item -> {
            System.out.println("    { \"nome\": \"" + item.getProduto().getNome() + "\", \"quantidade\": " + item.getQuantidade() + ", \"preco\": " + item.getProduto().getPreco() + " },");
        });
        System.out.println("  ],");
        System.out.println("  \"total\": " + pedido.getTotal() + ",");
        System.out.println("  \"frete\": " + pedido.calcularFrete() + ",");
        System.out.println("  \"total_com_frete\": " + (pedido.getTotal() + pedido.calcularFrete()));
        System.out.println("}");
    }
}
