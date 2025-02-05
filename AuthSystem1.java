
import javax.swing.*;
        import java.awt.*;
        import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

public class AuthSystem1 extends JFrame {
    private JTextField cardField;
    private JTextField levelField;
    private JButton loginButton;
    private JLabel statusLabel;

    public AuthSystem1() {
        setTitle("Access Control System");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 1));

        // UI Components
        JLabel cardLabel = new JLabel("Encoded Card:");
        cardField = new JTextField();

        JLabel levelLabel = new JLabel("Access Level:");
        levelField = new JTextField();

        loginButton = new JButton("Validate Access");
        statusLabel = new JLabel("", SwingConstants.CENTER);

        // Add components to frame
        add(cardLabel);
        add(cardField);
        add(levelLabel);
        add(levelField);
        add(loginButton);
        add(statusLabel);

        // Login button action
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleLogin();
            }
        });
    }

    private void handleLogin() {
        String cardInput = cardField.getText();
        String levelInput = levelField.getText();

        // จำลอง AuthSystem.AccessControl
        AccessControl accessControl = new AccessControl("CARD-10", "Admin");

        if (accessControl.validateAccess(cardInput, levelInput)) {
            statusLabel.setText("Access Granted!");
            statusLabel.setForeground(Color.GREEN);
        } else {
            statusLabel.setText("Access Denied!");
            statusLabel.setForeground(Color.RED);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AuthSystem1 authSystem = new AuthSystem1();
            authSystem.setVisible(true);
        });
    }

    public static class AccessControl {
        private String encodedCard;
        private String accessLevel;

        public AccessControl(String encodedCard, String accessLevel) {
            this.encodedCard = encodedCard;
            this.accessLevel = accessLevel;
        }

        // ตรวจสอบสิทธิ์ของบัตร
        public boolean validateAccess(String cardInput, String levelInput) {
            LocalDateTime currentTime = LocalDateTime.now();
            // จำลองการเข้ารหัส (แค่ตัวอย่าง)
            String expectedCard = "CARD-" + currentTime.getHour(); // Time-Encoded
            return cardInput.equals(expectedCard) && levelInput.equals(accessLevel);
        }
    }
}
