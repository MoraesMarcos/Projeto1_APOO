package relatorio;

import model.Pedido;

public class RelatorioTxt {
    public String gerar(Pedido pedido) {
        StringBuilder relatorio = new StringBuilder();
        relatorio.append("Cliente: ").append(pedido.getCliente().getNome()).append("\n");
        relatorio.append("Total: R$ ").append(String.format("%.2f", pedido.getTotalComFrete())).append("\n");
        return relatorio.toString();
    }
}