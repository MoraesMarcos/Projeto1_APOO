package util;

import java.io.*;
import java.util.List;

public class Persistencia {

    public static <T> void salvar(String caminho, List<T> lista) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(caminho))) {
            oos.writeObject(lista);
            System.out.println("Dados salvos em: " + caminho);
        } catch (IOException e) {
            System.out.println("Erro ao salvar dados: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> List<T> carregar(String caminho) {
        File arquivo = new File(caminho);
        if (!arquivo.exists()) {
            System.out.println("Arquivo não encontrado: " + caminho);
            return null;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(caminho))) {
            return (List<T>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar dados: " + e.getMessage());
            return null;
        }
    }
}
