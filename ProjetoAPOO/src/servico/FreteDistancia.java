package servico;

import model.Pedido;

public class FreteDistancia implements FreteEscolhido {
    private final double distancia;

    public FreteDistancia(double distancia) {
        this.distancia = distancia;
    }

    @Override
    public double calcularFrete(Pedido pedido) {
        return distancia * 0.50;
    }
}
