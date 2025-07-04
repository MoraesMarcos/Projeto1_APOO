package servico;

public class FabricaFreteDistancia implements FabricaFrete {
    @Override
    public FreteCalculadora criarCalculadoraFrete() {
        return new FreteCalculadoraDistancia();
    }
}