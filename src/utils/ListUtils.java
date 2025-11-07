package utils;
import java.util.*;

public class ListUtils {
    // создаю список на основе массива
    public static ArrayList<Integer> List(int[] massive) {
        ArrayList<Integer> List = new ArrayList<>();
        for (int value : massive) {
            List.add(value);
        }
        return List;
    }

    // Уникальные элементы списка
    public static Set<Integer> uniqueElements(List<Integer> List) {
        return new LinkedHashSet<>(List);
    }

    // Дублирующиеся элементы списка
    public static Set<Integer> dublicates(List<Integer> List) {
        Set<Integer> seen = new HashSet<>(); // уже встречавшиеся элементы
        Set<Integer> duplicates = new HashSet<>(); // найденные дубликаты

        for (Integer num : List) {
            if (!seen.add(num)) { // add() возвращает false, если элемент уже был
                duplicates.add(num);
            }
        }
        return duplicates;
    }
}