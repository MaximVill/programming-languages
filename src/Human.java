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