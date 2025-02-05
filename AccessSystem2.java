
import javax.swing.*;
        import java.awt.*;
        import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

public class AccessSystem2 extends JFrame {
    private JTextField userField;
    private JTextField cardField;
    private JButton validateButton;
    private JLabel statusLabel;

    private AuditLog auditLog;

    public AccessSystem2() {
        // เชื่อมต่อฐานข้อมูล
        DatabaseManager2 dbManager = new DatabaseManager2("access_system.db");
        auditLog = new AuditLog(dbManager);

        // ตั้งค่า GUI
        setTitle("Access System");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 1));

        JLabel userLabel = new JLabel("User:");
        userField = new JTextField();

        JLabel cardLabel = new JLabel("Card:");
        cardField = new JTextField();

        validateButton = new JButton("Validate");
        statusLabel = new JLabel("", SwingConstants.CENTER);

        // เพิ่ม Components
        add(userLabel);
        add(userField);
        add(cardLabel);
        add(cardField);
        add(validateButton);
        add(statusLabel);

        // ตรวจสอบสิทธิ์เมื่อคลิกปุ่ม
        validateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleAccess();
            }
        });
    }

    private void handleAccess() {
        String user = userField.getText();
        String card = cardField.getText();

        // ตรวจสอบสิทธิ์การเข้าถึง (จำลอง)
        boolean accessGranted = card.equals("CARD-1234"); // ตัวอย่างบัตรที่ถูกต้อง

        String status = accessGranted ? "GRANTED" : "DENIED";
        statusLabel.setText(accessGranted ? "Access Granted!" : "Access Denied!");
        statusLabel.setForeground(accessGranted ? Color.GREEN : Color.RED);

        // บันทึกประวัติการใช้งาน
        auditLog.logAccess(user, card, status);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AccessSystem2 accessSystem = new AccessSystem2();
            accessSystem.setVisible(true);
        });
    }

    public static class AuditLog {
        private DatabaseManager2 dbManager;

        public AuditLog(DatabaseManager2 dbManager) {
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
}
