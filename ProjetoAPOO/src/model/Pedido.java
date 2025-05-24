package model;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private Cliente cliente;
    private List<ItemPedido> itens;
    private double valorFrete;
    private double valorTotalPedido;

    public Pedido(Cliente cliente) {
        this.cliente = cliente;
        this.itens = new ArrayList<>();
    }

    public void adicionarItem(ItemPedido item) {
        this.itens.add(item);
    }

    public double calcularTotalProdutos() {
        double total = 0.0;
        for (ItemPedido item : itens) {
            total += item.getSubtotal();
        }
        return total;
    }

    public double calcularPesoTotal() {
        double pesoTotal = 0.0;
        for (ItemPedido item : itens) {
            pesoTotal += item.getPesoTotal();
        }
        return pesoTotal;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public double getValorFrete() {
        return valorFrete;
    }

    public void setValorFrete(double valorFrete) {
        this.valorFrete = valorFrete;
    }

    public double getValorTotalPedido() {
        return calcularTotalProdutos() + this.valorFrete;
    }
}