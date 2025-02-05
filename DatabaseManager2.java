
import java.sql.*;

public class DatabaseManager2 {
    private Connection connection;

    public DatabaseManager2(String dbName) {
        try {
            // เชื่อมต่อฐานข้อมูล SQLite
            connection = DriverManager.getConnection("jdbc:sqlite:" + dbName);
            createTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // สร้างตารางสำหรับเก็บประวัติการใช้งาน (ถ้ายังไม่มี)
    private void createTable() {
        String createTableSQL = """
            CREATE TABLE IF NOT EXISTS audit_log (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                timestamp TEXT,
                user TEXT,
                card TEXT,
                status TEXT
            );
        """;
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(createTableSQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ฟังก์ชันบันทึกข้อมูลลงฐานข้อมูล
    public void insertLog(String timestamp, String user, String card, String status) {
        String insertSQL = "INSERT INTO audit_log (timestamp, user, card, status) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(insertSQL)) {
            pstmt.setString(1, timestamp);
            pstmt.setString(2, user);
            pstmt.setString(3, card);
            pstmt.setString(4, status);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
