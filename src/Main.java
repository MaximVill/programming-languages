import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=".repeat(50));
        System.out.println("ЛАБОРАТОРНАЯ РАБОТА №6");
        System.out.println("Рефлексия, аннотации и работа с файловой системой");
        System.out.println("=".repeat(50));

        // === ЗАДАНИЕ 1 ===
        System.out.println("\n" + "=".repeat(50));
        System.out.println("ЗАДАНИЕ 1: Рефлексия и аннотации");
        System.out.println("=".repeat(50));
        try {
            Invoker.main(new String[]{});
        } catch (Exception e) {
            System.err.println("Ошибка в задании 1: " + e.getMessage());
        }

        scanner.close();
    }
}