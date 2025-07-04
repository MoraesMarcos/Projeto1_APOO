package servico;

import model.Pedido;

public interface FabricaFrete {
    FreteCalculadora criarCalculadoraFrete();
}