package servico;

import model.Cliente;
import model.Pedido;
import model.Produto;

public interface PedidoBuilder {
    PedidoBuilder comCliente(Cliente cliente);
    PedidoBuilder adicionarItem(Produto produto, int quantidade);
    PedidoBuilder comFrete(double frete);
    Pedido build(); // Método para construir o objeto Pedido final
}