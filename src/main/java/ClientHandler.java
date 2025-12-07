import java.io.*;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class ClientHandler implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(ClientHandler.class);
    private final Socket socket;
    private String nickname; // никнейм клиента
    private static final ConcurrentHashMap<String, ClientHandler> clients = new ConcurrentHashMap<>();

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), true)) {

            // Получаю никнейм от клиента
            writer.println("Введите ваш никнейм:");
            nickname = reader.readLine();
            if (nickname == null || nickname.trim().isEmpty()) {
                writer.println("Никнейм не может быть пустым. Отключение.");
                return;
            }
            clients.put(nickname, this);
            logger.info("Подключился: {}", nickname);

            // Уведомление всех о новом подключении
            broadcastMessage(null, "ALL", nickname + " присоединился к чату.");

            // Обработка сообщений от клиента
            String message;
            while ((message = reader.readLine()) != null) {
                // Парсим сообщение в формате: [тип] [адресат] : текст
                if (message.startsWith("ALL:")) {
                    String text = message.substring(4).trim();
                    broadcastMessage(nickname, "ALL", text);
                } else if (message.startsWith("TO:")) {
                    int colonIndex = message.indexOf(':', 3); // ищем первый двоеточие после "TO:"
                    if (colonIndex != -1) {
                        String recipient = message.substring(3, colonIndex).trim();
                        String text = message.substring(colonIndex + 1).trim();
                        sendMessageToRecipient(nickname, recipient, text);
                    } else {
                        logger.warn("Неверный формат. Используйте: TO: <ник> : <сообщение>");
                    }
                } else {
                    logger.warn("Неизвестная команда. Используйте ALL: или TO: <ник> :");
                }
            }

        } catch (IOException e) {
            logger.error("Ошибка при обработке клиента {}: {}", nickname, e.getMessage());
        } finally {
            if (nickname != null) {
                clients.remove(nickname);
                broadcastMessage(null, "ALL", nickname + " покинул чат.");
            }
            try {
                socket.close();
            } catch (IOException e) {
                logger.error("Ошибка при закрытии сокета: {}", e.getMessage());
            }
        }
    }

    // Рассылка всем клиентам
    private void broadcastMessage(String sender, String recipient, String message) {
        for (ClientHandler client : clients.values()) {
            client.sendMessage(sender, recipient, message);
        }
    }

    // Отправка личного сообщения конкретному получателю
    private void sendMessageToRecipient(String sender, String recipient, String message) {
        ClientHandler target = clients.get(recipient);
        if (target != null) {
            target.sendMessage(sender, recipient, message);
        } else {
            // Отправляем отправителю, что получатель не найден
            try {
                PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
                writer.println("Получатель '" + recipient + "' не найден.");
            } catch (IOException e) {
                logger.error("Ошибка при отправке сообщения об ошибке: {}", e.getMessage());
            }
        }
    }

    // Внутренний метод для отправки сообщения конкретному клиенту
    private void sendMessage(String sender, String recipient, String message) {
        try {
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            logger.warn("[{} -> {} ]: {}", sender, recipient, message);
        } catch (Exception e) {
            logger.error("Ошибка при отправке сообщения клиенту {}: {}", nickname, e.getMessage());
        }
    }

    // Метод для получения списка клиентов (для клиента)
    public String getClientsList() {
        return String.join(", ", clients.keySet());
    }
}