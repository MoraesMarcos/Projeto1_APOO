package servico;

import model.Pedido;

public class FreteDistancia {
    private static final double VALOR_POR_KM = 0.50;

    public double calcular(Pedido pedido, double distancia) {
        // O parâmetro 'pedido' não é usado aqui, mas poderia ser para regras futuras
        return distancia * VALOR_POR_KM;
    }
}