package servico;

public class FreteCalculadoraDistancia {

    private static final double VALOR_POR_KM = 0.50;

    public double calcular(double distanciaEmKm) {
        return distanciaEmKm * VALOR_POR_KM;
    }
}