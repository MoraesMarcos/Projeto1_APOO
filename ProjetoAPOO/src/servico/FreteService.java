package servico;

import model.Pedido;

public class FreteService {

    private IFreteCalculadora calculadora;

    public static class Builder {
        private double distancia = 0.0;
        private boolean porPeso = false;
        private boolean porDistancia = false;

        public Builder comDistancia(double distancia) {
            this.distancia = distancia;
            this.porDistancia = true;
            this.porPeso = false;
            return this;
        }

        public Builder porPeso() {
            this.porPeso = true;
            this.porDistancia = false;
            return this;
        }

        public FreteService build() {
            FreteService service = new FreteService();
            if (porPeso) {
                service.setCalculadora(new FreteCalculadoraPeso());
            } else if (porDistancia) {
                service.setCalculadora(new FreteCalculadoraDistancia(distancia));
            } else {
                throw new IllegalStateException("Tipo de cálculo de frete não especificado.");
            }
            return service;
        }
    }

    private void setCalculadora(IFreteCalculadora calculadora) {
        this.calculadora = calculadora;
    }

    public double calcularFrete(Pedido pedido) {
        if (calculadora == null) {
            throw new IllegalStateException("Calculadora de frete não configurada.");
        }
        return calculadora.calcular(pedido);
    }
}