package servico;

import model.ItemPedido;
import model.Pedido;

public class FretePeso {
    private static final double VALOR_POR_KG = 5.00;

    public double calcular(Pedido pedido) {
        double pesoTotal = 0.0;
        for (ItemPedido item : pedido.getItens()) {
            pesoTotal += item.getPesoTotal();
        }
        return pesoTotal * VALOR_POR_KG;
    }
}