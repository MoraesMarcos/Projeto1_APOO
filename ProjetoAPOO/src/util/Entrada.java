package util;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Entrada {
    private static Scanner scanner = new Scanner(System.in);

    public static String lerString(String mensagem) {
        System.out.print(mensagem);
        return scanner.nextLine();
    }

    public static int lerInt(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                int valor = scanner.nextInt();
                scanner.nextLine(); // Consumir a nova linha
                return valor;
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, digite um número inteiro.");
                scanner.nextLine(); // Consumir a entrada inválida
            }
        }
    }

    public static double lerDouble(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                double valor = scanner.nextDouble();
                scanner.nextLine(); // Consumir a nova linha
                return valor;
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, digite um número decimal.");
                scanner.nextLine(); // Consumir a entrada inválida
            }
        }
    }

    public static void fecharScanner() {
        if (scanner != null) {
            scanner.close();
        }
    }
}