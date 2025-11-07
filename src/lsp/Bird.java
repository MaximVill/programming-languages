package lsp;

/**
 * Подсказка:
 * Метод fly() теперь ведёт себя непредсказуемо для клиентского кода
 * Подкласс пингвина уменьшил функциональность базового класса (он “умеет меньше”)
 */
public abstract class Bird {
    public abstract void eat();

    public void fly() {
        System.out.println("Я лечу!");
    }
}
