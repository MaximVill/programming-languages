import dao.*;
import model.Book;
import model.Music;
import util.JsonDataLoader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void start() throws IOException, SQLException {
        MusicDao musicDao = new MusicDao();
        BookDao bookDao = new BookDao();
        JsonDataLoader loader = new JsonDataLoader();
        musicDao.createTableIfNotExists();
        String musicSql = new String(Files.readAllBytes(Paths.get("music-create.sql")));
        musicDao.populateFromSqlScript(musicSql);

        // 1. Получить список музыкальных композиций.
        System.out.println("=== Все композиции ===");
        for (Music m : musicDao.findAll()) System.out.println(m);

        // 2. Получить композиции, в названиях которых отсутствуют буквы m и t (без учёта регистра).
        System.out.println("\n=== Без m и t ===");
        for (Music m : musicDao.findWithoutMAndT()) System.out.println(m);

        // 3. Добавить любую свою любимую композицию.
        musicDao.insert("Ni**as In Paris");

        // 4. Используя файл books.json:
        new VisitorDao().createTableIfNotExists();
        bookDao.createTableIfNotExists();
        loader.loadFromJson("books.json");

        // 5. Вернуть отсортированный список книг по году издания.
        System.out.println("\n=== Книги по году ===");
        for (Book b : bookDao.findAllOrderedByYear()) {
            System.out.printf("%4d: %s — %s%n", b.getPublishingYear(), b.getTitle(), b.getAuthor());
        }

        // 6. Вывести книги, младше 2000 года.
        System.out.println("\n=== Книги до 2000 ===");
        for (Book b : bookDao.findAllBeforeYear(2000)) {
            System.out.printf("%4d: %s — %s%n", b.getPublishingYear(), b.getTitle(), b.getAuthor());
        }

        // 7. Добавить информацию о себе и свои любимые книги.
        int myId = new VisitorDao().findOrCreate("Максим", "Вилл", "8-800-555-35-35", true);
        bookDao.insert(myId, "Clean Code", "Robert C. Martin", 2008, "9780132350884", "Prentice Hall");
        bookDao.insert(myId, "Effective Java", "Joshua Bloch", 2018, "9780134685991", "Addison-Wesley");

        // 8. Удалить созданные таблицы посетителей и книг.
        bookDao.dropTables();
        System.out.println("\nТаблицы visitors и books удалены.");
    }
}