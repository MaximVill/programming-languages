import java.util.List;

public class Task4 {
    public static void run(List<Visitor> visitors) {
        // №4. Проверить, есть ли у кого-то из посетителей в списке избранных книг произведение автора "Jane Austen".
        boolean hasJaneAusten = visitors.stream().flatMap(v -> v.getFavoriteBooks().stream())
                .anyMatch(b -> "Jane Austen".equals(b.getAuthor()));
        System.out.println("\nЗАДАНИЕ 4, ЕСТЬ ЛИ КНИГИ Jane Austen? >> " + hasJaneAusten + " <<");
    }
}
