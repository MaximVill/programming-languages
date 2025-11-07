import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Task3 {
    public static void run(List<Visitor> visitors) {
        // №3. Отсортировать и вывести список всех книг по году издания.
        System.out.println("\nЗАДАНИЕ 3: ОТСОРТИРОВАННЫЙ СПИСОК ВСЕХ КНИГ ПО ГОДУ ИЗАДАНИЯ");
        List<Book> allBooksSorted = visitors.stream().flatMap(v -> v.getFavoriteBooks().stream())
                .sorted(Comparator.comparingInt(Book::getPublishingYear))
                .collect(Collectors.toList());
        for (int i = 0; i < allBooksSorted.size(); i++){
            Book b = allBooksSorted.get(i);
            System.out.println((i + 1) + ") " + b.getPublishingYear() + " — " + b.getName()); // 20 уникальных книг в избранном
        }
    }
}
