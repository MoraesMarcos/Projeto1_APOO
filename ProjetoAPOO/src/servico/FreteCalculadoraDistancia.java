package servico;

import model.Pedido;

public class FreteCalculadoraDistancia implements FreteCalculadora {
    private static final double CUSTO_POR_KM = 0.50;

    @Override
    public double calcular(Pedido pedido) {

        return 0;
    }

    @Override
    public double calcular(double distancia) {
        return distancia * CUSTO_POR_KM;
    }
}