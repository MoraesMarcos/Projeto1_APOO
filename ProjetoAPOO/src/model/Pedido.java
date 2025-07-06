package model;

import servico.FreteEscolhido;
import servico.Notificador;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private final Cliente cliente;
    private final List<ItemPedido> itens = new ArrayList<>();
    private FreteEscolhido freteEscolhido;
    private Notificador notificador;

    private Pedido(Cliente cliente) {
        this.cliente = cliente;
    }

    public void adicionarItem(Produto produto, int quantidade) {
        itens.add(new ItemPedido(produto, quantidade));
    }

    public double getTotal() {
        return itens.stream().mapToDouble(ItemPedido::getTotal).sum();
    }

    public double getPesoTotal() {
        return itens.stream().mapToDouble(ItemPedido::getPesoTotal).sum();
    }

    public double calcularFrete() {
        return freteEscolhido != null ? freteEscolhido.calcularFrete(this) : 0;
    }

    public void setFrete(FreteEscolhido freteEscolhido) {
        this.freteEscolhido = freteEscolhido;
    }

    public void setNotificador(Notificador notificador) {
        this.notificador = notificador;
    }

    public void notificar() {
        if (notificador != null) notificador.notificar(cliente);
    }

    public Cliente getCliente() { return cliente; }
    public List<ItemPedido> getItens() { return itens; }

    public static class PedidoBuilder {
        private final Pedido pedido;

        public PedidoBuilder(Cliente cliente) {
            pedido = new Pedido(cliente);
        }

        public PedidoBuilder adicionarItem(Produto produto, int quantidade) {
            pedido.adicionarItem(produto, quantidade);
            return this;
        }

        public PedidoBuilder setFrete(FreteEscolhido freteEscolhido) {
            pedido.setFrete(freteEscolhido);
            return this;
        }

        public PedidoBuilder setNotificador(Notificador notificador) {
            pedido.setNotificador(notificador);
            return this;
        }

        public Pedido build() {
            return pedido;
        }
    }
}