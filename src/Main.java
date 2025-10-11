import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.Data;

import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
        System.out.println("ЗАДАНИЕ 1: ПРОНУМЕРОВАННЫЙ СПИСОК ПОСЕТИТЕЛЕЙ");
        for (int i = 0; i < visitors.size(); i++) {
            Visitor v = visitors.get(i);
            System.out.println((i + 1) + ") " + v.getName() + " " + v.getSurname()); // 15 Посетителей
        }

        // №2. Вывести список и количество всех книг, добавленных посетителями в избранное, без повторений.
        System.out.println("\nЗАДАНИЕ 2: ПРОНУМЕРОВАННЫЙ СПИСОК КНИГ ИЗ ИЗБРАННОГО (БЕЗ ПОВТОРЕНИЙ)");
        List<Book> uniqueBooks = visitors.stream().flatMap(v -> v.getFavoriteBooks().stream())
                .distinct().collect(Collectors.toList());
        for (int i = 0; i < uniqueBooks.size(); i++){
            Book b = uniqueBooks.get(i);
            System.out.println((i + 1) + ") " + b.getName() + " — " + b.getAuthor()); // 20 уникальных книг в избранном
        }

        // №3. Отсортировать и вывести список всех книг по году издания.
        System.out.println("\nЗАДАНИЕ 3: ОТСОРТИРОВАННЫЙ СПИСОК ВСЕХ КНИГ ПО ГОДУ ИЗАДАНИЯ");
        List<Book> allBooksSorted = visitors.stream().flatMap(v -> v.getFavoriteBooks().stream())
                .sorted(Comparator.comparingInt(Book::getPublishingYear))
                .collect(Collectors.toList());
        for (int i = 0; i < allBooksSorted.size(); i++){
            Book b = allBooksSorted.get(i);
            System.out.println((i + 1) + ") " + b.getPublishingYear() + " — " + b.getName()); // 20 уникальных книг в избранном
        }

        // №4. Проверить, есть ли у кого-то из посетителей в списке избранных книг произведение автора "Jane Austen".
        boolean hasJaneAusten = visitors.stream().flatMap(v -> v.getFavoriteBooks().stream())
                .anyMatch(b -> "Jane Austen".equals(b.getAuthor()));
        System.out.println("\nЗАДАНИЕ 4, ЕСТЬ ЛИ КНИГИ Jane Austen? >> " + hasJaneAusten + " <<");

        // №5. Вывести максимальное число книг, добавленных одним посетителем в избранное.
        int maxBooks = visitors.stream().mapToInt(v -> v.getFavoriteBooks().size())
                .max().orElse(0);
        System.out.println("\nЗАДАНИЕ 5, МАКСИМАЛЬНОЕ ЧИСЛО ИЗБРАННЫХ КНИГ У ОДНОГО ПОСЕТИТЕЛЯ: " + maxBooks);
    }
}