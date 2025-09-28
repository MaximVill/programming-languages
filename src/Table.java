public class Table {
    private final int[][] massive;
    public Table(int rows, int cols) {
        if (rows < 0 || cols < 0) {
            throw new IllegalArgumentException("rows and cols cannot be negative.");
        }
        massive = new int[rows][cols]; // автоматически заполнилась нулями
    }
}
