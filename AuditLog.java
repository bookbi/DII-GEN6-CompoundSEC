
import java.time.LocalDateTime;

public class AuditLog {
    private DatabaseManager dbManager;

    public AuditLog(DatabaseManager dbManager) {
        this.dbManager = dbManager;
    }

    // บันทึกประวัติการใช้งาน
    public void logAccess(String user, String card, String status) {
        LocalDateTime now = LocalDateTime.now();
        String timestamp = now.toString();

        // บันทึกข้อมูลลงฐานข้อมูล
        dbManager.insertLog(timestamp, user, card, status);
    }
}
