package servico;

public class FactoryFreteDistancia implements FreteFactory {
    private final double distancia;

    public FactoryFreteDistancia(double distancia) {
        this.distancia = distancia;
    }

    @Override
    public FreteEscolhido criarFrete() {
        return new FreteDistancia(distancia);
    }
}
