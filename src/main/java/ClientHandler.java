import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.*;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;

public class ClientHandler implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(ClientHandler.class);
    private final Socket socket;
    private String nickname;
    private PrintWriter writer;
    private static final ConcurrentHashMap<String, ClientHandler> clients = new ConcurrentHashMap<>();

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            this.writer = new PrintWriter(socket.getOutputStream(), true);

            writer.println("NICK");
            nickname = reader.readLine();
            if (nickname == null || nickname.trim().isEmpty()) return;

            synchronized (clients) {
                clients.put(nickname, this);
            }

            logger.info("Подключился: {}", nickname);
            broadcast(null, "ALL", nickname + " присоединился к чату.");

            String line;
            while ((line = reader.readLine()) != null) {
                if (line.equals("EXIT")) {
                    break;
                } else if (line.startsWith("ALL:")) {
                    String msg = line.substring(4).trim();
                    logger.info("Сообщение от {} -> ALL: {}", nickname, msg);
                    broadcast(nickname, "ALL", msg);
                } else if (line.startsWith("TO:")) {
                    int i = line.indexOf(':', 3);
                    if (i > 0) {
                        String to = line.substring(3, i).trim();
                        String msg = line.substring(i + 1).trim();
                        logger.info("Сообщение от {} -> {}: {}", nickname, to, msg);
                        sendPrivate(to, nickname, msg);
                    }
                } else if (line.equals("LIST")) {
                    writer.println("CLIENTS: " + String.join(", ", clients.keySet()));
                }
            }

            // Отключение
            synchronized (clients) {
                clients.remove(nickname);
            }
            broadcast(null, "ALL", nickname + " покинул чат.");
            logger.info("Клиент {} отключился.", nickname);

        } catch (IOException ignored) {
        } finally {
            try {
                socket.close();
            } catch (IOException ignored) {}
        }
    }

    private void broadcast(String sender, String recipient, String msg) {
        for (ClientHandler c : clients.values()) {
            c.send(sender, recipient, msg);
        }
    }

    private void sendPrivate(String to, String sender, String msg) {
        ClientHandler target = clients.get(to);
        if (target != null) {
            target.send(sender, to, msg);
        } else {
            writer.println("ERROR: Получатель '" + to + "' не найден.");
        }
    }

    private void send(String sender, String recipient, String msg) {
        String formatted = (sender == null)
                ? "[" + recipient + "]: " + msg
                : "[" + sender + " -> " + recipient + "]: " + msg;
        writer.println(formatted);
    }
}