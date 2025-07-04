package servico;

import model.Pedido;

public interface FreteCalculadora {
    double calcular(Pedido pedido);
    double calcular(double distancia); // Para o cálculo por distância, se aplicável
}