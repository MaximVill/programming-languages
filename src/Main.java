import java.util.*;


public class Main {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        System.out.print("Сколко элементов будет в массиве?\n>> ");

        int N = console.nextInt();
        int[] massive = new int[N]; // Объявил массив и выделил ему память под N ячеек

        for (int i = 0; i < N; i++) {
            massive[i] = (int)(Math.random() * 100); // заполняем массив числами от 0 до 100
            System.out.println(massive[i]);
        }

        // создаю список на основе массива
        ArrayList<Integer> List = new ArrayList<>();
        for (int value : massive) {
            List.add(value);
        }
        System.out.println(List);

        // Сортирую список по возрастанию
         Collections.sort(List);
         System.out.println(List);

        // Сортирую список в обратном порядке
         Collections.sort(List, Collections.reverseOrder());
         System.out.println(List);

        // Перемешиваю список
         Collections.shuffle(List);
         System.out.println(List);

        // Технический сдвиг на 1
         Collections.rotate(List, 1);
         System.out.println(List);

        // Оставил только уникальные элементы. Для этого временно сделаю числа от 0 до 9 и чтобы их стало 100 штук
         Set<Integer> uniqueElements = new HashSet<>(List);
         System.out.println(uniqueElements);

        // Ищу дубликаты
        Set<Integer> seen = new HashSet<>(); // уже встречавшиеся элементы
        Set<Integer> duplicates = new HashSet<>(); // найденные дубликаты

        for (Integer num : List) {
            if (!seen.add(num)) { // add() возвращает false, если элемент уже был
                duplicates.add(num);
            }
        }

        if (duplicates.isEmpty()) {
            System.out.println("Дубликатов нет.");
        } else {
            System.out.println(duplicates);
        }

        // Получаю из списка массив
        int[] newMassive = new int[List.size()];
        for (int i = 0; i <List.size(); i++) {
            newMassive[i] = List.get(i);
            System.out.println(newMassive[i]);
        }

        // Подсчитываем количество вхождений каждого числа
        Map<Integer, Integer> counts = new HashMap<>();
        for (Integer num : List) {
            counts.put(num, counts.getOrDefault(num, 0) + 1);
        }

        // Выводим результат
        for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
            System.out.println(entry.getKey() + " — " + entry.getValue() + " раз(а)");
        }
    }
}