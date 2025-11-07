package utils;

import java.util.Random;

public class ArrayUtils {
    private static final Random random = new Random();

    // Создаю массив из N чисел от 0 до 100
    public static int[] randomMassive(int N) {
        int[] massive = new int[N];
        for (int i = 0; i < N; i++) {
            massive[i] = random.nextInt(101);
        }
        return massive;
    }

    // Преобразую список в массив
    public static int[] listToInArray(java.util.List<Integer> List) {
        return List.stream().mapToInt(Integer::intValue).toArray();
    }
}