import java.util.List;

public class Task5 {
    public static void run(List<Visitor> visitors) {
        // №5. Вывести максимальное число книг, добавленных одним посетителем в избранное.
        int maxBooks = visitors.stream().mapToInt(v -> v.getFavoriteBooks().size())
                .max().orElse(0);
        System.out.println("\nЗАДАНИЕ 5, МАКСИМАЛЬНОЕ ЧИСЛО ИЗБРАННЫХ КНИГ У ОДНОГО ПОСЕТИТЕЛЯ: " + maxBooks);
    }
}
