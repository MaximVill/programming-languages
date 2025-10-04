package utils;
import java.util.*;

// Кол-во вхождение числа в массив
public class FrequencyCounter {
    public static Map<Integer, Integer> countFrequencies(List<Integer> List) {
        Map<Integer, Integer> counts = new HashMap<>();
        for (Integer num : List) {
            counts.put(num, counts.getOrDefault(num, 0) + 1);
        }
        return counts;
    }
}
