package srp;

import java.io.FileWriter;
import java.io.IOException;

public class ReportSaver {
    public void saveToFile(String report) {
        try (FileWriter writer = new FileWriter("report.txt")) {
            writer.write(report);
            System.out.println("Report saved to report.txt");
        } catch (IOException e) {
            System.out.println("Error writing report file: " + e.getMessage());
        }
    }
}
