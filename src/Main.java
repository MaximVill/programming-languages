import java.util.*;

class Human implements Comparable<Human>{
    String firstName;
    String lastName;
    int age;

    @Override
    public int compareTo(Human other) {
        return this.lastName.compareTo(other.lastName);
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " (" + age + ")";
    }
}

public class Main {
    public static void main(String[] args) {
        List<Human> humans = new ArrayList<>();
        humans.add(new Human());
        humans.get(0).firstName = "Илья";
        humans.get(0).lastName = "Абрамов";
        humans.get(0).age = 20;

        humans.add(new Human());
        humans.get(1).firstName = "Владислав";
        humans.get(1).lastName = "Базяк";
        humans.get(1).age = 19;

        humans.add(new Human());
        humans.get(2).firstName = "Иван";
        humans.get(2).lastName = "толкачев";
        humans.get(2).age = 22;

        Set<Human> hashSet = new HashSet<>(humans);
        System.out.println("HashSet: " + hashSet);

        Set<Human> linkedHashSet = new LinkedHashSet<>(humans);
        System.out.println("LinkedHashSet: " + linkedHashSet);

        Set<Human> treeSet = new TreeSet<>(humans);
        System.out.println("TreeSet (по фамилии): " + treeSet);

        class HumanComparatorByLastName implements Comparator<Human> {
            @Override
            public int compare(Human h1, Human h2) {
                return h1.lastName.compareTo(h2.lastName);
            }
        }

        Set<Human> treeSetByLastName = new TreeSet<>(new HumanComparatorByLastName());
        treeSetByLastName.addAll(humans);
        System.out.println("TreeSet (с HumanComparatorByLastName): " + treeSetByLastName);
    }
}

/*
Объяснение различий:

1. HashSet:
   - Не гарантирует порядок элементов.
   - Вывод может быть в любом порядке (зависит от хеш-кодов).
   - Каждый объект считается уникальным, (даже с одинаковыми полями) потому-что не переопределены equals() и hashCode().

2. LinkedHashSet:
   - Сохраняет порядок вставки.

3. TreeSet (без компаратора):
   - Использует Comparable (по фамилии).
   - Сортирует по возрастанию фамилий.

4. TreeSet с HumanComparatorByLastName:
   - То же, что и выше (потому что Comparable тоже по фамилии).

5. TreeSet с компаратором по возрасту:
   - Сортирует по возрасту.
*/