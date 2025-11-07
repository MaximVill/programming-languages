public class Main {
    public static void main(String[] args) {
        // №1: Button
        System.out.println("=== ЗАДАНИЕ 1: Button ===");
        Button button = new Button();
        button.click();
        button.click();

        // №2: Balance
        System.out.println("\n=== ЗАДАНИЕ 2: Balance ===");
        Balance balance1 = new Balance();
        balance1.addRight(3);
        balance1.addLeft(2);
        balance1.result();

        Balance balance2 = new Balance();
        balance2.addRight(2);
        balance2.addLeft(3);
        balance2.result();

        Balance balance3 = new Balance();
        balance3.addRight(3);
        balance3.addLeft(3);
        balance3.result();

        // №3: Bell
        System.out.println("\n=== ЗАДАНИЕ 3: Bell ===");
        Bell bell = new Bell();
        bell.sound();
        bell.sound();

        //№4: OddEvenSeparator
        System.out.println("\n=== ЗАДАНИЕ 4: OddEvenSeparator ===");
        OddEvenSeparator separator = new OddEvenSeparator();
        separator.addNumber(9147);
        separator.addNumber(234);
        separator.addNumber(2128194124);
        separator.addNumber(123);

        separator.even();
        separator.odd();

        // №5: Table
        System.out.println("\n=== ЗАДАНИЕ 5: Table ===");
        Table table = new Table(3, 3);
        table.set_value(0, 0, 1);
        table.set_value(1, 1, 2);
        table.set_value(2, 2, 8);

        System.out.println(table.toString());
        System.out.println();
        System.out.println("Average: " + table.average());
        System.out.println("Rows: " + table.rows());
        System.out.println("Cols: " + table.cols());
    }
}