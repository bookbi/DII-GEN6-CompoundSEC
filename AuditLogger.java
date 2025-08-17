import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AuditLogger {
    private static final String FILE_PATH = "access_log.csv";

    public static void logAccessAttempt(String cardID, String action, String user, boolean success) {
        try (FileWriter writer = new FileWriter(FILE_PATH, true)) {
            writer.append(cardID).append(",")
                    .append(user).append(",")
                    .append(action).append(",")
                    .append(success ? "SUCCESS" : "FAILED").append("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<String[]> getLogs() {
        List<String[]> logs = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                logs.add(line.split(","));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return logs;
    }
}
