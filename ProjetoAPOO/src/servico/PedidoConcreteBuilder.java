package servico;

import model.Cliente;
import model.Pedido;
import model.Produto;
import model.ItemPedido;

import java.util.ArrayList;
import java.util.List;

public class PedidoConcreteBuilder implements PedidoBuilder {
    private Cliente cliente;
    private List<ItemPedido> itens;
    private double frete;

    public PedidoConcreteBuilder() {
        this.itens = new ArrayList<>();
        this.frete = 0.0; // Valor inicial do frete
    }

    @Override
    public PedidoBuilder comCliente(Cliente cliente) {
        this.cliente = cliente;
        return this; // Retorna o próprio builder para encadeamento de chamadas
    }

    @Override
    public PedidoBuilder adicionarItem(Produto produto, int quantidade) {
        if (produto != null && quantidade > 0) {
            this.itens.add(new ItemPedido(produto, quantidade));
        }
        return this;
    }

    @Override
    public PedidoBuilder comFrete(double frete) {
        this.frete = frete;
        return this;
    }

    @Override
    public Pedido build() {
        if (this.cliente == null) {
            throw new IllegalStateException("Um pedido deve ter um cliente.");
        }

        Pedido pedido = new Pedido(this.cliente);
        pedido.setItens(this.itens);
        pedido.setFrete(this.frete);
        return pedido;
    }
}