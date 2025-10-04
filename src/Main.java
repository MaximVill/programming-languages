import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

class PrimeGenerator implements Iterable<Integer> {
    private final int N;
    public PrimeGenerator(int N) {
        this.N = N;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            private int count = 0;
            private int current = 2;

            @Override
            public boolean hasNext() {
                return count < N;
            }

            @Override
            public Integer next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                while (!isPrime(current)){
                    current++;
                }
                int prime = current;
                current++;
                count++;
                return prime;

            }

            private boolean isPrime(int x) {
                if (x < 2) return false;
                for (int i = 2; i < x; i++) {
                    if (x % i == 0) {
                        return false;
                    }
                }
                return true;
            }
        };
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Сколько простых чисел вывести?\n>> ");
        int N = scanner.nextInt();
    }
}