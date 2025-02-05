
import javax.swing.*;
        import java.awt.*;
        import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AuthSystem extends JFrame {
    private JTextField cardField;
    private JTextField levelField;
    private JButton loginButton;
    private JLabel statusLabel;

    public AuthSystem() {
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

        // จำลอง AccessControl
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
            AuthSystem authSystem = new AuthSystem();
            authSystem.setVisible(true);
        });
    }
}
