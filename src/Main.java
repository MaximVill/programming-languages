import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

// Класс PrimesGenerator генерирует N простых чисел по одному
class PrimeGenerator implements Iterable<Integer> {
    private final int N; // Сколько простых чисел нужно

    // запоминает, сколько простых чисел нужно.
    public PrimeGenerator(int N) {
        this.N = N;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            private int count = 0; // Сколько простых чисел уже есть
            private int current = 2; // Это первое простое число, т.к 1 не может им быть

            // Движемся дальше, если количество простых чисел не превышает заданного количества
            @Override
            public boolean hasNext() {
                return count < N;
            }

            @Override
            public Integer next() {
                if (!hasNext()) { // Проверка на исключение
                    throw new NoSuchElementException();
                }

                // пока число не окажется простым двигаемся дальше
                while (!isPrime(current)){
                    current++;
                }

                int prime = current; // запоминаем простое число
                current++; // новое крайнее простое число
                count++; // увеличиваем счетчик найденных
                return prime;

            }

            // проверка на просто число
            private boolean isPrime(int x) {
                if (x < 2) return false; // 0 и 1 не простые числа
                for (int i = 2; i < x; i++) {
                    if (x % i == 0) {
                        return false; // если у числа есть делитель, значит оно составное
                    }
                }
                return true; // если число обошло проверку, то оно простое
            }
        };
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Сколько простых чисел вывести?\n>> ");
        int N = scanner.nextInt();

        PrimeGenerator generator = new PrimeGenerator(N);
        int[] primes = new int[N];
        int index = 0;
        for (int prime : generator) {
            primes[index] = prime;
            index++;
        }

        System.out.println("Прямой порядок: ");
        for (int num : primes) {
            System.out.print(num + " ");
        }

        System.out.println();
        System.out.println();

        System.out.println("Обратный порядок: ");
        for (int i = primes.length - 1; i >= 0; i--) {
            System.out.print(primes[i] + " ");
        }
    }
}