import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminLogin extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;


    public AdminLogin() {
        setTitle("🪷For Admin ");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));
        panel.setBackground(Color.pink);

        // ช่องกรอกข้อมูล
        panel.add(new JLabel("Username:"));
        usernameField = new JTextField();
        panel.add(usernameField);

        panel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        panel.add(passwordField);

        loginButton = new JButton("Login");
        panel.add(loginButton);

        add(panel, BorderLayout.CENTER);

        // Action Listener สำหรับปุ่ม Login
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // ตรวจสอบข้อมูลการล็อกอิน
                if (validateLogin(usernameField.getText(), new String(passwordField.getPassword()))) {
                    // ถ้าข้อมูลถูกต้อง เปิด MainFrame
                    new MainFrame().setVisible(true);
                    dispose();  // ปิดหน้า Login
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Username or Password");
                }
            }
        });
    }

    // ฟังก์ชันสำหรับตรวจสอบข้อมูลล็อกอิน
    private boolean validateLogin(String username, String password) {
        return username.equals("white") && password.equals("lotus");  // ตัวอย่างการตรวจสอบ username และ password
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AdminLogin().setVisible(true);
            }
        });
    }
}
