import java.util.*;
import utils.*;

public class Main {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);

        // ==========================================
        // ЗАДАНИЕ 1: Collections
        // ==========================================
        System.out.println("=".repeat(50));
        System.out.println("ЗАДАНИЕ 1: Collections");
        System.out.println("=".repeat(50));

        System.out.print("Сколько элементов будет в массиве?\n>> ");
        int N = console.nextInt();

        // №1. Создаю массив
        int[] massive = ArrayUtils.randomMassive(N);
        System.out.println("1. Исходный массив: " + Arrays.toString(massive));

        // №2. Создаю список
        ArrayList<Integer> list = ListUtils.List(massive);
        System.out.println("2. Список: " + list);

        // №3. Сортирую список по возрастанию
        ArrayList<Integer> sortAscending = new ArrayList<>(list);
        Collections.sort(sortAscending);
        System.out.println("3. Сортировка по возрастанию: " + sortAscending);

        // №4. Сортирую список в обратном порядке
        ArrayList<Integer> sortDecreasing = new ArrayList<>(list);
        Collections.sort(sortDecreasing, Collections.reverseOrder());
        System.out.println("4. Сортировка по убыванию: " + sortDecreasing);

        // №5. Перемешиваю список
        ArrayList<Integer> shuffled = new ArrayList<>(list);
        Collections.shuffle(shuffled);
        System.out.println("5. Перемешанный список: " + shuffled);

        // №6. Технический сдвиг на 1
        ArrayList<Integer> rotated = new ArrayList<>(list);
        Collections.rotate(rotated, 1);
        System.out.println("6. Сдвиг на 1: " + rotated);

        // №7. Уникальные элементы
        Set<Integer> unique = ListUtils.uniqueElements(list);
        System.out.println("7. Уникальные элементы: " + unique);

        // №8. Дубликаты
        Set<Integer> duplicates = ListUtils.dublicates(list);
        System.out.println("8. Дубликаты: " + (duplicates.isEmpty() ? "нет" : duplicates));

        // №9. Список → массив
        int[] newMassive = ArrayUtils.listToInArray(list);
        System.out.println("9. Новый массив: " + Arrays.toString(newMassive));

        // №10. Частота вхождений
        Map<Integer, Integer> counts = FrequencyCounter.countFrequencies(list);
        System.out.println("10. Частота вхождений:");
        counts.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(e -> System.out.println(e.getKey() + " – " + e.getValue() + " раз(а)"));

        // ==========================================
        // ЗАДАНИЕ 2: PrimesGenerator
        // ==========================================
        System.out.println("\n" + "=".repeat(50));
        System.out.println("ЗАДАНИЕ 2: PrimesGenerator");
        System.out.println("=".repeat(50));

        System.out.print("Сколько простых чисел вывести?\n>> ");
        int M = console.nextInt();

        PrimeGenerator generator = new PrimeGenerator(M);
        int[] primes = new int[M];
        int index = 0;
        for (int prime : generator) {
            primes[index] = prime;
            index++;
        }

        System.out.print("Прямой порядок: ");
        for (int num : primes) System.out.print(num + " ");
        System.out.println();

        System.out.print("Обратный порядок: ");
        for (int i = primes.length - 1; i >= 0; i--) System.out.print(primes[i] + " ");
        System.out.println();

        // ==========================================
        // ЗАДАНИЕ 3: Human
        // ==========================================
        System.out.println("\n" + "=".repeat(50));
        System.out.println("ЗАДАНИЕ 3: Human");
        System.out.println("=".repeat(50));

        List<Human> humans = new ArrayList<>();
        Human h1 = new Human(); h1.firstName = "Илья"; h1.lastName = "Абрамов"; h1.age = 20;
        Human h2 = new Human(); h2.firstName = "Владислав"; h2.lastName = "Базяк"; h2.age = 19;
        Human h3 = new Human(); h3.firstName = "Иван"; h3.lastName = "Толкачёв"; h3.age = 22;
        humans.add(h1); humans.add(h2); humans.add(h3);

        System.out.println("HashSet: " + new HashSet<>(humans));
        System.out.println("LinkedHashSet: " + new LinkedHashSet<>(humans));
        System.out.println("TreeSet (по фамилии): " + new TreeSet<>(humans));

        class HumanComparatorByLastName implements Comparator<Human> {
            public int compare(Human h1, Human h2) {
                return h1.lastName.compareTo(h2.lastName);
            }
        }

        Set<Human> treeSetByLastName = new TreeSet<>(new HumanComparatorByLastName());
        treeSetByLastName.addAll(humans);
        System.out.println("TreeSet (с компаратором): " + treeSetByLastName);

        console.close();
        System.out.println("\n✅ Все задания выполнены.");
    }
}