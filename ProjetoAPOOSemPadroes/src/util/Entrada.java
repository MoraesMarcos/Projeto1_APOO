package util;

import java.util.Scanner;

public class Entrada {
    private static final Scanner scanner = new Scanner(System.in);

    public static int lerInt(String mensagem) {
        System.out.print(mensagem);
        int valor = scanner.nextInt();
        scanner.nextLine(); // Consome a quebra de linha pendente
        return valor;
    }

    public static String lerString(String mensagem) {
        System.out.print(mensagem);
        return scanner.nextLine();
    }

    public static double lerDouble(String mensagem) {
        System.out.print(mensagem);
        double valor = scanner.nextDouble();
        scanner.nextLine(); // Consome a quebra de linha pendente
        return valor;
    }
}