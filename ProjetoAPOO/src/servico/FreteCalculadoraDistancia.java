package servico;

public class FreteCalculadoraDistancia {
    private static final double VALOR_POR_KM = 0.5;

    public double calcular(double distanciaKm) {
        return distanciaKm * VALOR_POR_KM;
    }
}
