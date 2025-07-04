package servico;

import java.util.Objects;

public class NotificadorFactory {
    public static INotificador criarNotificador(String tipo) {
        if (Objects.equals(tipo, "EMAIL")) {
            return new NotificadorEmail();
        } else if (Objects.equals(tipo, "SMS")) {
            return new NotificadorSMS();
        } else if (Objects.equals(tipo, "WHATSAPP")) {
            return new NotificadorWhatsApp();
        } else {
            throw new IllegalArgumentException("Tipo de notificador inválido: " + tipo);
        }
    }
}