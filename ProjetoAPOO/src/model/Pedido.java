package model;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private Cliente cliente;
    private List<ItemPedido> itens;
    private double frete;

    public Pedido(Cliente cliente) {
        this.cliente = cliente;
        this.itens = new ArrayList<>();
        this.frete = 0.0;
    }

    public Pedido(Cliente cliente, List<ItemPedido> itens, double frete) {
        this.cliente = cliente;
        this.itens = itens != null ? new ArrayList<>(itens) : new ArrayList<>();
        this.frete = frete;
    }

    public void adicionarItem(Produto produto, int quantidade) {
        if (produto != null && quantidade > 0) {
            this.itens.add(new ItemPedido(produto, quantidade));
        }
    }

    public double calcularTotalProdutos() {
        return itens.stream()
                .mapToDouble(ItemPedido::calcularSubtotal)
                .sum();
    }

    public double calcularPesoTotal() {
        return itens.stream()
                .mapToDouble(ItemPedido::calcularPesoTotalItem)
                .sum();
    }

    public double getTotalComFrete() {
        return calcularTotalProdutos() + frete;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public double getFrete() {
        return frete;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setItens(List<ItemPedido> itens) {
        this.itens = itens != null ? new ArrayList<>(itens) : new ArrayList<>();
    }

    public void setFrete(double frete) {
        this.frete = frete;
    }
}