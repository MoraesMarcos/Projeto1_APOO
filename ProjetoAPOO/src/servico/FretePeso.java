package servico;

import model.Pedido;

public class FretePeso implements FreteEscolhido {

    private static final double preco_por_kilo = 5.0;

    @Override
    public double calcularFrete(Pedido pedido) {
        return pedido.getPesoTotal() * preco_por_kilo;
    }
}