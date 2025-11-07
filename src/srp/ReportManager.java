package srp;

import java.util.List;

public class ReportManager {

    private final List<Integer> data;

    public ReportManager(List<Integer> data) {
        this.data = data;
    }

    public void generateReport() {
        ReportCalculator calculator = new ReportCalculator(data);
        ReportData reportData = calculator.calculate();

        ReportFormatter formatter = new ReportFormatter();
        String report = formatter.format(reportData);

        System.out.println(report);

        ReportSaver saver = new ReportSaver();
        saver.saveToFile(report);
    }
}
