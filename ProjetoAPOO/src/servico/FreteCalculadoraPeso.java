package servico;

import model.Pedido;

public class FreteCalculadoraPeso implements FreteCalculadora {
    private static final double CUSTO_POR_KG = 5.00; // R$ 5,00/kg

    @Override
    public double calcular(Pedido pedido) {
        return pedido.calcularPesoTotal() * CUSTO_POR_KG;
    }

    @Override
    public double calcular(double distancia) {

        return 0;
    }
}