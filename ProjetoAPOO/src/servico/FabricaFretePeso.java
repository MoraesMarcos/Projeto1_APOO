package servico;

public class FabricaFretePeso implements FabricaFrete {
    @Override
    public FreteCalculadora criarCalculadoraFrete() {
        return new FreteCalculadoraPeso();
    }
}