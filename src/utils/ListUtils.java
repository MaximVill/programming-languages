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
}
