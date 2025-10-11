import java.util.List;

public class Task1 {
    public static void run(List<Visitor> visitors) {
        // №1. Вывести список посетителей и их количество.
        System.out.println("ЗАДАНИЕ 1: ПРОНУМЕРОВАННЫЙ СПИСОК ПОСЕТИТЕЛЕЙ");
        for (int i = 0; i < visitors.size(); i++) {
            Visitor v = visitors.get(i);
            System.out.println((i + 1) + ") " + v.getName() + " " + v.getSurname()); // 15 Посетителей
        }
    }
}
