import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws Exception {
        Scanner console = new Scanner(System.in);
        System.out.print("Ваш никнейм: ");
        String nick = console.nextLine();

        try (Socket socket = new Socket("localhost", 8080);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

            String serverMsg = in.readLine();
            if (!"NICK".equals(serverMsg)) return;
            out.println(nick);

            // Поток для входящих сообщений
            Thread reader = new Thread(() -> {
                try {
                    String msg;
                    while ((msg = in.readLine()) != null) {
                        System.out.println(msg);
                    }
                } catch (IOException ignored) {}
            });
            reader.setDaemon(true);
            reader.start();

            // Основной цикл
            while (true) {
                System.out.println("\nКоманды: /all <текст>, /msg, exit");
                String input = console.nextLine().trim();

                if ("exit".equalsIgnoreCase(input)) {
                    out.println("EXIT");
                    break;
                } else if (input.startsWith("/all ")) {
                    out.println("ALL:" + input.substring(5));
                } else if ("/msg".equals(input)) {
                    out.println("LIST");
                    String list = in.readLine(); // ждём ответ
                    if (list != null && list.startsWith("CLIENTS: ")) {
                        System.out.println(list);
                        System.out.print("Кому: ");
                        String to = console.nextLine().trim();
                        System.out.print("Текст: ");
                        String text = console.nextLine();
                        out.println("TO:" + to + ":" + text);
                    }
                }
            }

        }
    }
}