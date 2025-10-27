import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

         // №1. Рефлексия и аннотации
        System.out.println("\n" + "=".repeat(50));
        System.out.println("ЗАДАНИЕ 1: Рефлексия и аннотации");
        System.out.println("=".repeat(50));
        try {
            Invoker.main(new String[]{});
        } catch (Exception e) {
            System.err.println("Ошибка в задании 1: " + e.getMessage());
        }

        // №2. Работа с файловой системой
        System.out.println("\n" + "=".repeat(50));
        System.out.println("ЗАДАНИЕ 2: Работа с файловой системой");
        System.out.println("=".repeat(50));
        try {
            FileSystemManager.main(new String[]{});
        } catch (Exception e) {
            System.err.println("Удалите папку Vill в корне проекта");
        }

        scanner.close();
    }
}