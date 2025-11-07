package srp;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;


/**
 * Подсказка:
 * если нужно изменить способ хранения, формат отчёта, или формулу вычисления — придётся менять один и тот же класс.
 */
public class ReportManager {

    private final List<Integer> data;

    public ReportManager(List<Integer> data) {
        this.data = data;
    }

    public void generateReport() {
        System.out.println("=== Report ===");
        int sum = calculateSum();
        double avg = calculateAverage();
        System.out.println("Sum: " + sum);
        System.out.println("Average: " + avg);
        System.out.println("Generated at: " + LocalDateTime.now());

        saveToFile(sum, avg);
    }

    private int calculateSum() {
        int sum = 0;
        for (int n : data) {
            sum += n;
        }
        return sum;
    }

    private double calculateAverage() {
        if (data.isEmpty()) return 0;
        return (double) calculateSum() / data.size();
    }

    private void saveToFile(int sum, double avg) {
        try (FileWriter writer = new FileWriter("report.txt")) {
            writer.write("=== Report ===\n");
            writer.write("Sum: " + sum + "\n");
            writer.write("Average: " + avg + "\n");
            writer.write("Generated at: " + LocalDateTime.now() + "\n");
            System.out.println("Report saved to report.txt");
        } catch (IOException e) {
            System.out.println("Error writing report file: " + e.getMessage());
        }
    }
}
