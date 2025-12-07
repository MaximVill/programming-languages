import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try (Scanner console = new Scanner(System.in)) {
            System.out.print("Введите IP-адрес сервера (по умолчанию localhost): ");
            String host = console.nextLine().trim();
            if (host.isEmpty()) host = "localhost";

            System.out.print("Введите порт (по умолчанию 8080): ");
            String portStr = console.nextLine().trim();
            int port = portStr.isEmpty() ? 8080 : Integer.parseInt(portStr);

            try (Socket socket = new Socket(host, port);
                 BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 PrintWriter writer = new PrintWriter(socket.getOutputStream(), true)) {

                String welcomeMessage = reader.readLine(); // "Введите ваш никнейм:"
                System.out.println(welcomeMessage);

                System.out.print("Ваш никнейм: ");
                String nickname = console.nextLine();
                writer.println(nickname);

                // Поток для получения сообщений от сервера
                Thread readerThread = new Thread(() -> {
                    String message;
                    try {
                        while ((message = reader.readLine()) != null) {
                            System.out.println(message);
                        }
                    } catch (IOException e) {
                        if (!socket.isClosed()) {
                            System.err.println("Соединение с сервером разорвано.");
                        }
                    }
                });
                readerThread.setDaemon(true);
                readerThread.start();

                // Основной цикл отправки сообщений
                System.out.println("Подключено! Введите 'exit' для выхода.");
                System.out.println("Для отправки сообщения используйте:\n  1 — всем, 2 — личное");

                while (true) {
                    System.out.print("\nВыберите тип сообщения (1/2): ");
                    String choice = console.nextLine().trim();

                    if ("exit".equalsIgnoreCase(choice)) {
                        break;
                    }

                    if ("1".equals(choice)) {
                        System.out.print("Текст сообщения (ALL): ");
                        String text = console.nextLine();
                        writer.println("ALL: " + text);

                    } else if ("2".equals(choice)) {
                        // попросим пользователя ввести вручную.
                        System.out.print("Ник получателя: ");
                        String recipient = console.nextLine().trim();
                        System.out.print("Текст сообщения: ");
                        String text = console.nextLine();
                        writer.println("TO: " + recipient + " : " + text);

                    } else {
                        System.out.println("Неверный выбор. Введите 1 (всем) или 2 (личное).");
                    }
                }

            } catch (NumberFormatException e) {
                System.err.println("Некорректный порт.");
            } catch (IOException e) {
                System.err.println("Не удалось подключиться к серверу: " + e.getMessage());
            }

        }
    }
}