import java.util.List;
import java.util.stream.Collectors;

public class Task2 {
    public static void run(List<Visitor> visitors) {
        // №2. Вывести список и количество всех книг, добавленных посетителями в избранное, без повторений.
        System.out.println("\nЗАДАНИЕ 2: ПРОНУМЕРОВАННЫЙ СПИСОК УНИКАЛЬНЫХ КНИГ ИЗ ИЗБРАННОГО");
        List<Book> uniqueBooks = visitors.stream().flatMap(v -> v.getFavoriteBooks().stream())
                .distinct().collect(Collectors.toList());
        for (int i = 0; i < uniqueBooks.size(); i++){
            Book b = uniqueBooks.get(i);
            System.out.println((i + 1) + ") " + b.getName() + " — " + b.getAuthor()); // 20 уникальных книг в избранном
        }
    }
}
