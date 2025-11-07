public class Table {
    private final int[][] massive;

    public Table(int rows, int cols) {
        if (rows < 0 || cols < 0) {
            throw new IllegalArgumentException("rows and cols cannot be negative.");
        }
        massive = new int[rows][cols]; // автоматически заполнилась нулями
    }

    public int get_value(int row, int col) {
        return massive[row][col];
    }

    public void set_value(int row, int col, int value) {
        massive[row][col] = value;
    }

    public int rows() {
        return massive.length;
    }

    public int cols() {
        if (massive.length == 0) {
            return 0;
        } else {
            return massive[0].length;
        }
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < rows(); i++) {
            for (int j = 0; j < cols(); j++) {
                str.append(massive[i][j]);
                if (j < cols() - 1) {
                    str.append(" ");
                }
            }
            if (i < rows() - 1) {
                str.append("\n");
            }
        }
        return str.toString();
    }

    public double average() {
        if (rows() == 0 || cols() == 0) {
            return 0.0; // или можно выбросить исключение — зависит от требований
        }
        long sum = 0;
        for (int i = 0; i < rows(); i++) {
            for (int j = 0; j < cols(); j++) {
                sum += massive[i][j];
            }
        }
        return (double) sum / (rows() * cols());
    }

}