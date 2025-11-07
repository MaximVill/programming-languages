package srp;

import java.time.LocalDateTime;

public class ReportFormatter {
    public String format(ReportData data) {
        return """
                === Report ===
                Sum: %s
                Average: %s
                Generated at: %s
                """.formatted(data.sum, data.average, LocalDateTime.now());
    }
}
