import java.util.ArrayList;
import java.util.List;

public class OddEvenSeparator {
    final List<Integer> evenNumbers = new ArrayList<>();
    final List<Integer> oddNumbers = new ArrayList<>();

    public void addNumber(int number) {
        if (number % 2 == 0) {
            evenNumbers.add(number);
        } else {
            oddNumbers.add(number);
        }
    }

    void even() {
        System.out.println("even");
    }

    void odd() {
        System.out.println("odd");
    }

}
