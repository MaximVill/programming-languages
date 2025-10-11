import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws Exception {
        Gson gson = new Gson();
        Type listType = new TypeToken<List<Visitor>>(){}.getType();
        List<Visitor> visitors = gson.fromJson(new FileReader("books.json"), listType);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n<< МЕНЮ >>");
            System.out.println("1. СПИСОК ПОСЕТИТЕЛЕЙ И ИХ КОЛ-ВО");
            System.out.println("2. СПИСОК УНИКАЛЬНЫХ КНИГ В ИЗБРАННОМ");
            System.out.println("3. КНИГИ ПО ГОДУ ИЗДАНЯ");
            System.out.println("4. АВТОР 'Jane Austen'");
            System.out.println("5. МАКС ЧИСЛО КНИГ В ИЗБРАННОМ");
            System.out.println("6. SMS СООБЩЕНИЯ");
            System.out.println("0. ВЫХОД\n");

            System.out.print(">> ВВОД: ");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1: Task1.run(visitors); break;
                case 2: Task2.run(visitors); break;
                case 3: Task3.run(visitors); break;
                case 4: Task4.run(visitors); break;
                case 5: Task5.run(visitors); break;
                case 6: Task6.run(visitors); break;
                case 0: return;
                default: System.out.println("Неверный выбор.");
            }
        }

    }
}