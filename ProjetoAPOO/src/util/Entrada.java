package util;

import java.util.Scanner;

public class Entrada {
    private static Entrada instance;
    private final Scanner scanner = new Scanner(System.in);

    private Entrada() {}

    public static Entrada getInstance() {
        if (instance == null) instance = new Entrada();
        return instance;
    }

    public int lerInt(String prompt) {
        System.out.print(prompt);
        return scanner.nextInt();
    }

    public String lerTexto(String prompt) {
        System.out.print(prompt);
        return scanner.next();
    }

    public double lerDouble(String prompt) {
        System.out.print(prompt);
        return scanner.nextDouble();
    }
}