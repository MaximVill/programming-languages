import lombok.Data;
import java.util.List;

// Класс книги
@Data
class Book {
    private String name;
    private String author;
    private int publishingYear;
    private String isbn;
    private String publisher;
}

// Класс посетителя
@Data
class Visitor {
    private String name;
    private String surname;
    private String phone;
    private boolean subscribed;
    private List<Book> favoriteBooks;
}

public class Main {
    public static void main(String[] args) {
    }
}