import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static final Logger logger = LoggerFactory.getLogger(Server.class);

    public static void main(String[] args) throws Exception {
        ServerSocket server = new ServerSocket(8080);
        logger.info("Сервер запущен на порту 8080");
        while (true) {
            Socket client = server.accept();
            new Thread(new ClientHandler(client)).start();
        }
    }
}