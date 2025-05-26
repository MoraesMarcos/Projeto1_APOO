package servico;

public class FreteCalculadoraPeso {

    private static final double VALOR_POR_KG = 5.00;

    public double calcular(Pedido pedido) {
        double pesoTotal = pedido.calcularPesoTotal();
        return pesoTotal * VALOR_POR_KG;
    }
}