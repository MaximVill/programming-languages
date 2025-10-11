import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.Data;

import java.io.FileReader;
import java.lang.reflect.Type;
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
    public static void main(String[] args) throws Exception {
        Gson gson = new Gson();
        Type listType = new TypeToken<List<Visitor>>(){}.getType();
        List<Visitor> visitors = gson.fromJson(new FileReader("books.json"), listType);

        // №1. Вывести список посетителей и их количество.
        System.out.println("ЗАДАНИЕ 1: СПИСОК ПОСЕТИТЕЛЕЙ И ИХ КОЛИЧЕСТВО");
        for (int i = 0; i < visitors.size(); i++) {
            Visitor v = visitors.get(i);
            System.out.println((i + 1) + ") " + v.getName() + " " + v.getSurname());
        }
    }
}