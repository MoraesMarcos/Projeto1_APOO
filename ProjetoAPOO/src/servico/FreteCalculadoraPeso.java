package servico;

import model.Pedido;

public class FreteCalculadoraPeso implements IFreteCalculadora {

    private static final double VALOR_POR_KG = 5.00;

    @Override
    public double calcular(Pedido pedido) {
        double pesoTotal = pedido.calcularPesoTotal();
        return pesoTotal * VALOR_POR_KG;
    }
}