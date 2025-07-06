package relatorio;

import model.Pedido;

public class RelatorioTexto {
    public void gerar(Pedido pedido) {
        System.out.println("Cliente: " + pedido.getCliente().getNome());
        System.out.println("Produtos:");
        pedido.getItens().forEach(item -> {
            System.out.println("- " + item.getProduto().getNome() + " (" + item.getQuantidade() + "x) - R$ " + item.getProduto().getPreco());
        });
        System.out.println("Total: R$ " + pedido.getTotal());
        System.out.println("Frete: R$ " + pedido.calcularFrete());
        System.out.println("Total com frete: R$ " + (pedido.getTotal() + pedido.calcularFrete()));
    }
}
