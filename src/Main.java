public class Main {
    public static void main(String[] args) {
        Table table = new Table(3, 3);

        table.set_value(0, 0, 1);
        table.set_value(1, 1, 2);
        table.set_value(2, 2, 8);

        System.out.println(table.toString());
        System.out.println("");

        System.out.println("Average: " + table.average()); // 1.(3)
        System.out.println("Rows: " + table.rows());      // 3
        System.out.println("Cols: " + table.cols());      // 3
    }
}