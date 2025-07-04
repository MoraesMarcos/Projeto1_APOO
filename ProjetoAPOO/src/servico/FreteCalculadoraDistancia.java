package servico;

import model.Pedido;

public class FreteCalculadoraDistancia implements IFreteCalculadora {

    private double distancia;

    public FreteCalculadoraDistancia(double distancia) {
        this.distancia = distancia;
    }

    @Override
    public double calcular(Pedido pedido) {
        return this.distancia * 0.50;
    }
}