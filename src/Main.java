import utils.ArrayUtils;
import utils.ListUtils;

import java.util.*;


public class Main {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        System.out.print("Сколко элементов будет в массиве?\n>> ");

        // №1. Создаю массив
        int N = console.nextInt();
        int[] massive = ArrayUtils.randomMassive(N);
        System.out.println("1. Исходный массив: " + Arrays.toString(massive));

        //№2. Создаю список
        ArrayList<Integer> List = ListUtils.List(massive);
        System.out.println("2. Список: " + List);

        // №3. Сортирую список по возрастанию
        ArrayList<Integer> sortAscending = new ArrayList<>(List);
        Collections.sort(sortAscending);
        System.out.println("3. Сортировка по возрастанию: " + sortAscending);

        // №4. Сортирую список в обратном порядке
        ArrayList<Integer> sortDecreasing = new ArrayList<>(List);
        Collections.sort(sortDecreasing, Collections.reverseOrder());
        System.out.println("4. Сортировка по убыванию: " + sortDecreasing);

        // №5. Перемешиваю список
        ArrayList<Integer> shuffled = new ArrayList<>(List);
        Collections.shuffle(shuffled);
        System.out.println("5. Перемешанный список относительно исходного: " + shuffled);

        // №6. Технический сдвиг на 1
        ArrayList<Integer> rotated = new ArrayList<>(List);
        Collections.rotate(rotated, 1);
        System.out.println("6. Технический сдвиг относительно исходного: " + rotated);

        // №7. Оставил только уникальные элементы
        Set<Integer> unique = ListUtils.uniqueElements(List);
        System.out.println("7. Уникальные элементы списка: " + unique);

        // №8. Ищу дубликаты
        Set<Integer> dublicates = ListUtils.dublicates(List);
        System.out.println("8. Дубликаты в списке: " + (dublicates.isEmpty() ? "В списке нет дубликатов" : dublicates));


        // Получаю из списка массив
        int[] newMassive = ArrayUtils.listToInArray(List);
        System.out.println("9. Новый массив: " + Arrays.toString(newMassive));
//
//        // Подсчитываем количество вхождений каждого числа
//        Map<Integer, Integer> counts = new HashMap<>();
//        for (Integer num : List) {
//            counts.put(num, counts.getOrDefault(num, 0) + 1);
//        }
//
//        // Выводим результат
//        for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
//            System.out.println(entry.getKey() + " — " + entry.getValue() + " раз(а)");
//        }
    }
}