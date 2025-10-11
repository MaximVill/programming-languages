import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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


        // №6. Вывести список всех сгенерированных SMS-сообщений на экран.
        // Среднее количество книг:
        double averageBooks = visitors.stream().mapToDouble(v -> v.getFavoriteBooks().size())
                .average().orElse(0.0); // 2.8

        // Группировка согласившихся на рассылку:
        List<SmsMessage> smsMessages = visitors.stream().filter(Visitor::isSubscribed).map(visitor -> {
            int count = visitor.getFavoriteBooks().size();
            String msg;

            if (count > averageBooks) {
                msg = "you are a bookworm";
            } else if (count < averageBooks) {
                msg = "read more";
            } else {
                msg = "fine";
            }

            SmsMessage sms = new SmsMessage();
            sms.setPhoneNumber(visitor.getPhone());
            sms.setMessage(msg);
            return sms;
        }).collect(Collectors.toList());

        // Вывод:
        System.out.println("\nЗАДАНИЕ 6, СПИСОК ВСЕХ СГЕНЕРИРОВАННЫХ ВСЕХ SMS-СООБЩЕНИЙ:");
        for (SmsMessage sms : smsMessages) {
            System.out.println(sms.getPhoneNumber() + " " + sms.getMessage());
        }
    }
}