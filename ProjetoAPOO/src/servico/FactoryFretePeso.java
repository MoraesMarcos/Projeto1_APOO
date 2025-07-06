package servico;

public class FactoryFretePeso implements FreteFactory {
    @Override
    public FreteEscolhido criarFrete() {
        return new FretePeso();
    }
}
