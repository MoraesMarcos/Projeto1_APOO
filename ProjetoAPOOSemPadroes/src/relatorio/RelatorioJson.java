package relatorio;

import model.Pedido;

public class RelatorioJson {
    public String gerar(Pedido pedido) {
        // Usando String.format para garantir a formatação correta do número no JSON
        return String.format(
                "{ \"cliente\": \"%s\", \"total\": %.2f }",
                pedido.getCliente().getNome(),
                pedido.getTotalComFrete()
        ).replace(',', '.'); // Garante que o separador decimal seja um ponto
    }
}