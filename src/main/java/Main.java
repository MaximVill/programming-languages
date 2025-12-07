import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;

public class Main {
    private static final String DB_URL = "jdbc:h2:~/musicdb";
    private static final String USER = "sa";
    private static final String PASS = "";

    public static void main(String[] args) {
        try {
            executeLab9();
        } catch (Exception e) {
            System.err.println("Ошибка выполнения лабораторной:");
            e.printStackTrace();
        }
    }

    private static void executeLab9() throws IOException, SQLException {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS)) {
            // 1. Получить список музыкальных композиций.
            createMusicTable(connection);
            System.out.println("=== Все композиции ===");
            listAllMusic(connection);

            // 2. Получить композиции, в названиях которых отсутствуют буквы m и t (без учёта регистра).
            System.out.println("\n=== Композиции без букв m и t ===");
            listMusicWithoutMandT(connection);

            // 3. Добавить любую свою любимую композицию.
            insertMyFavoriteSong(connection);

            // 4. Используя файл books.json
            System.out.println("\n=== Загрузка данных из books.json ===");
            processBooksJson(connection);

            // 5. Вернуть отсортированный список книг по году издания.
            System.out.println("\n=== Книги, отсортированные по году издания ===");
            listBooksOrderedByYear(connection);

            // 6. Вывести книги, младше 2000 года.
            System.out.println("\n=== Книги до 2000 года ===");
            listBooksBefore2000(connection);

            // 7. Добавить информацию о себе и свои любимые книги.
            System.out.println("\n=== Мои любимые книги ===");
            addMyselfAndBooks(connection);

            // 8. Удалить созданные таблицы посетителей и книг.
            dropVisitorAndBookTables(connection);
        }
    }

    private static void createMusicTable(Connection connection) throws SQLException, IOException {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("CREATE SCHEMA IF NOT EXISTS study");
        }

        String sql = new String(Files.readAllBytes(Paths.get("music-create.sql")));
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
        }
        System.out.println("Таблица study.music создана и заполнена.");
    }

    private static void listAllMusic(Connection connection) throws SQLException {
        String sql = "SELECT id, name FROM study.music";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println(rs.getInt("id") + ": " + rs.getString("name"));
            }
        }
    }

    private static void listMusicWithoutMandT(Connection connection) throws SQLException {
        String sql = "SELECT id, name FROM study.music " +
                "WHERE LOWER(name) NOT LIKE '%m%' AND LOWER(name) NOT LIKE '%t%'";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println(rs.getInt("id") + ": " + rs.getString("name"));
            }
        }
    }

    private static void insertMyFavoriteSong(Connection connection) throws SQLException {
        int nextId;
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT COALESCE(MAX(id), 0) + 1 FROM study.music")) {
            rs.next();
            nextId = rs.getInt(1);
        }
        String sql = "INSERT INTO study.music (id, name) VALUES (?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, nextId);
            ps.setString(2, "Ni**as In Paris");
            ps.executeUpdate();
        }
        System.out.println("Добавлена композиция с id=" + nextId);
    }

    private static void processBooksJson(Connection connection) throws IOException, SQLException {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("CREATE TABLE IF NOT EXISTS visitors (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "name VARCHAR(100) NOT NULL, " +
                    "surname VARCHAR(100) NOT NULL, " +
                    "phone VARCHAR(20), " +
                    "subscribed BOOLEAN)");
            stmt.execute("CREATE TABLE IF NOT EXISTS books (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "visitor_id INT NOT NULL, " +
                    "title VARCHAR(255) NOT NULL, " +
                    "author VARCHAR(255) NOT NULL, " +
                    "publishing_year INT, " +
                    "isbn VARCHAR(20), " +
                    "publisher VARCHAR(255), " +
                    "FOREIGN KEY (visitor_id) REFERENCES visitors(id))");
        }

        String json = new String(Files.readAllBytes(Paths.get("books.json")));
        JSONArray people = new JSONArray(json);

        for (int i = 0; i < people.length(); i++) {
            JSONObject person = people.getJSONObject(i);
            String name = person.getString("name");
            String surname = person.getString("surname");
            String phone = person.getString("phone");
            boolean subscribed = person.getBoolean("subscribed");

            // Проверка уникальности по name и surname
            int visitorId;
            try (PreparedStatement ps = connection.prepareStatement(
                    "SELECT id FROM visitors WHERE name = ? AND surname = ?")) {
                ps.setString(1, name);
                ps.setString(2, surname);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    visitorId = rs.getInt("id");
                } else {
                    // Вставить нового посетителя
                    try (PreparedStatement ins = connection.prepareStatement(
                            "INSERT INTO visitors (name, surname, phone, subscribed) VALUES (?, ?, ?, ?)",
                            Statement.RETURN_GENERATED_KEYS)) {
                        ins.setString(1, name);
                        ins.setString(2, surname);
                        ins.setString(3, phone);
                        ins.setBoolean(4, subscribed);
                        ins.executeUpdate();
                        rs = ins.getGeneratedKeys();
                        rs.next();
                        visitorId = rs.getInt(1);
                    }
                }
            }

            // Проверка уникальности по isbn и visitor_id
            JSONArray favBooks = person.getJSONArray("favoriteBooks");
            for (int j = 0; j < favBooks.length(); j++) {
                JSONObject book = favBooks.getJSONObject(j);
                String title = book.getString("name");
                String author = book.getString("author");
                int year = book.getInt("publishingYear");
                String isbn = book.getString("isbn");
                String publisher = book.getString("publisher");

                try (PreparedStatement check = connection.prepareStatement(
                        "SELECT 1 FROM books WHERE visitor_id = ? AND isbn = ?")) {
                    check.setInt(1, visitorId);
                    check.setString(2, isbn);
                    if (!check.executeQuery().next()) {
                        try (PreparedStatement ins = connection.prepareStatement(
                                "INSERT INTO books (visitor_id, title, author, publishing_year, isbn, publisher) " +
                                        "VALUES (?, ?, ?, ?, ?, ?)")) {
                            ins.setInt(1, visitorId);
                            ins.setString(2, title);
                            ins.setString(3, author);
                            ins.setInt(4, year);
                            ins.setString(5, isbn);
                            ins.setString(6, publisher);
                            ins.executeUpdate();
                        }
                    }
                }
            }
        }
        System.out.println("Данные из books.json загружены.");
    }

    private static void listBooksOrderedByYear(Connection connection) throws SQLException {
        String sql = "SELECT title, author, publishing_year FROM books ORDER BY publishing_year";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.printf("%4d: %s — %s%n",
                        rs.getInt("publishing_year"),
                        rs.getString("title"),
                        rs.getString("author"));
            }
        }
    }

    private static void listBooksBefore2000(Connection connection) throws SQLException {
        String sql = "SELECT title, author, publishing_year FROM books WHERE publishing_year < 2000";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.printf("%4d: %s — %s%n",
                        rs.getInt("publishing_year"),
                        rs.getString("title"),
                        rs.getString("author"));
            }
        }
    }

    private static void addMyselfAndBooks(Connection connection) throws SQLException {
        String myName = "Максим";
        String mySurname = "Вилл";
        String myPhone = "8-800-555-35-35";
        boolean subscribed = true;

        int myId;
        try (PreparedStatement ps = connection.prepareStatement(
                "SELECT id FROM visitors WHERE name = ? AND surname = ?")) {
            ps.setString(1, myName);
            ps.setString(2, mySurname);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                myId = rs.getInt("id");
            } else {
                try (PreparedStatement ins = connection.prepareStatement(
                        "INSERT INTO visitors (name, surname, phone, subscribed) VALUES (?, ?, ?, ?)",
                        Statement.RETURN_GENERATED_KEYS)) {
                    ins.setString(1, myName);
                    ins.setString(2, mySurname);
                    ins.setString(3, myPhone);
                    ins.setBoolean(4, subscribed);
                    ins.executeUpdate();
                    rs = ins.getGeneratedKeys();
                    rs.next();
                    myId = rs.getInt(1);
                }
            }
        }

        // Добавляю книги
        String[][] myBooks = {
                {"Clean Code", "Robert C. Martin", "2008", "9780132350884", "Prentice Hall"},
                {"Effective Java", "Joshua Bloch", "2018", "9780134685991", "Addison-Wesley"}
        };

        for (String[] book : myBooks) {
            try (PreparedStatement check = connection.prepareStatement(
                    "SELECT 1 FROM books WHERE visitor_id = ? AND isbn = ?")) {
                check.setInt(1, myId);
                check.setString(2, book[3]);
                if (!check.executeQuery().next()) {
                    try (PreparedStatement ins = connection.prepareStatement(
                            "INSERT INTO books (visitor_id, title, author, publishing_year, isbn, publisher) " +
                                    "VALUES (?, ?, ?, ?, ?, ?)")) {
                        ins.setInt(1, myId);
                        ins.setString(2, book[0]);
                        ins.setString(3, book[1]);
                        ins.setInt(4, Integer.parseInt(book[2]));
                        ins.setString(5, book[3]);
                        ins.setString(6, book[4]);
                        ins.executeUpdate();
                    }
                }
            }
        }

        // Вывод моих книг
        System.out.println("Мои книги:");
        try (PreparedStatement ps = connection.prepareStatement(
                "SELECT b.title, b.author FROM books b " +
                        "JOIN visitors v ON b.visitor_id = v.id " +
                        "WHERE v.name = ? AND v.surname = ?")) {
            ps.setString(1, myName);
            ps.setString(2, mySurname);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println("  - " + rs.getString("title") + " (" + rs.getString("author") + ")");
            }
        }
    }

    private static void dropVisitorAndBookTables(Connection conn) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            stmt.execute("DROP TABLE IF EXISTS books");
            stmt.execute("DROP TABLE IF EXISTS visitors");
        }
        System.out.println("Таблицы visitors и books удалены.");
    }
}