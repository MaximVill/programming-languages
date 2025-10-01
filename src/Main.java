import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        System.out.print("Сколко элементов будет в массиве?\n>> ");

        int N = console.nextInt();
        int[] massive = new int[N]; // Объявил массив и выделил ему память под N ячеек

        for (int i = 0; i < N; i++) {
            massive[i] = (int)(Math.random() * 100); // заполняем массив числами от 0 до 100
            // System.out.println(massive[i]);
        }

        // создаю список на основе массива
        ArrayList<Integer> List = new ArrayList<>();
        for (int value : massive) {
            List.add(value);
        }
        // System.out.println(List);

        // Сортирую список по возрастанию
        Collections.sort(List);
        // System.out.println(List);

        // Сортирую список в обратном порядке
        Collections.sort(List, Collections.reverseOrder());
        // System.out.println(List);
    }
}