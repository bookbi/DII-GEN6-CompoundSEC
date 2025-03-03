import javax.swing.*;
import java.awt.*;

public class Login extends JFrame {
    private JTextField cardIDField, nameField;
    private JButton accessButton;
    private String signupCardID, signupCardholder;
    // รับข้อมูลจาก Signup

    public Login(String cardID, String cardholder) {
        this.signupCardID = cardID;  // ข้อมูลที่ส่งมาจาก Signup
        this.signupCardholder = cardholder;
        setTitle("Login");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // 👉 Sidebar (ด้านซ้าย)
        JPanel sidebar = new JPanel();
        sidebar.setBackground(new Color(33, 79, 155)); // สีน้ำเงินเข้ม
        sidebar.setPreferredSize(new Dimension(220, getHeight()));
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));

        JLabel logo = new JLabel("🪷The White Lotus", SwingConstants.CENTER);
        logo.setForeground(Color.WHITE);
        logo.setFont(new Font("Arial", Font.BOLD, 18));


        JButton backButton = new JButton("⬅Back");

        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        sidebar.add(Box.createVerticalStrut(20));
        sidebar.add(logo);

        sidebar.add(Box.createVerticalGlue());
        sidebar.add(backButton);

        // 👉 พาเนลหลัก (Main Content)
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBackground(new Color(230, 240, 255));

        JLabel lockIcon = new JLabel("🔒 Log in");
        lockIcon.setFont(new Font("Arial", Font.BOLD, 20));
        lockIcon.setBounds(370, 30, 200, 30);
        mainPanel.add(lockIcon);

        JLabel cardIDLabel = new JLabel("Card ID:");
        cardIDLabel.setBounds(250, 90, 100, 25);
        mainPanel.add(cardIDLabel);

        JTextField cardIDField = new JTextField();
        cardIDField.setBounds(370, 90, 200, 30);
        mainPanel.add(cardIDField);

        JLabel nameLabel = new JLabel("Cardholder:");
        nameLabel.setBounds(250, 140, 120, 25);
        mainPanel.add(nameLabel);

        JTextField nameField = new JTextField();
        nameField.setBounds(370, 140, 200, 30);
        mainPanel.add(nameField);

        JLabel permissionsLabel = new JLabel("Permissions:");
        permissionsLabel.setBounds(250, 190, 100, 25);
        mainPanel.add(permissionsLabel);

        // 🏢 สร้างปุ่ม Floor (แต่ละชั้นมี 12 ห้อง)
        JPanel floorPanel = new JPanel(new FlowLayout());
        floorPanel.setBounds(370, 190, 400, 100);
        for (int i = 1; i <= 3; i++) {  // มี 3 ชั้น
            JButton floorButton = new JButton("Floor " + i);
            floorButton.setBackground(new Color(245, 230, 230));
            floorPanel.add(floorButton);

            int floor = i;
            floorButton.addActionListener(e -> showRoomSelection(floor));
        }
        mainPanel.add(floorPanel);

        // ปุ่ม Access
        JButton accessButton = new JButton("Access");
        accessButton.setBounds(420, 300, 120, 40);
        accessButton.setBackground(new Color(80, 50, 120));
        accessButton.setForeground(Color.black);
        mainPanel.add(accessButton);

        // ปุ่ม Settings (มุมขวาล่าง)
        JButton settingsButton = new JButton("⚙");
        settingsButton.setBounds(780, 400, 50, 50);
        settingsButton.setBackground(new Color(120, 90, 150));
        settingsButton.setForeground(Color.WHITE);
        mainPanel.add(settingsButton);

        // เพิ่ม ActionListener ให้ปุ่ม backButton
        backButton.addActionListener(e -> {
            new CustomersFrame(); // เปิด CustomersFrame
            dispose(); // ปิด Login
        });

        // เพิ่มองค์ประกอบต่างๆ ลงใน Frame
        add(sidebar, BorderLayout.WEST);
        add(mainPanel, BorderLayout.CENTER);

        setLocationRelativeTo(null);
        setVisible(true);
        // ตรวจสอบข้อมูลที่กรอก
        accessButton.addActionListener(e -> {
            if (cardIDField.getText().equals(signupCardID) && nameField.getText().equals(signupCardholder)) {
                // แสดงข้อความ "Welcome to the White Lotus"
                JOptionPane.showMessageDialog(this, "🪷 Welcome to the White Lotus!");
                openDashboard();
            } else {
                JOptionPane.showMessageDialog(this, "❌ Invalid login credentials.");
            }
        });

        mainPanel.add(accessButton);

        add(sidebar, BorderLayout.WEST);
        add(mainPanel, BorderLayout.CENTER);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public Login() {

    }

    private void openDashboard() {
        new DashboardPanel();  // เมื่อเข้าสู่ระบบสำเร็จ
        dispose();  // ปิดหน้าต่าง Login
    }



    // 🏠 แสดงหน้าต่างเลือกห้องของแต่ละชั้น (มี 12 ห้อง)
    private void showRoomSelection(int floor) {
        JFrame roomFrame = new JFrame("Floor " + floor + " Rooms");
        roomFrame.setSize(400, 300);
        roomFrame.setLayout(new GridLayout(3, 4, 10, 10));

        for (int i = 1; i <= 12; i++) {
            JButton roomButton = new JButton("Room " + i);
            roomButton.addActionListener(e -> {
                // แสดงข้อความเมื่อเลือกห้องเสร็จ
                JOptionPane.showMessageDialog(this, "เลือกห้องเสร็จสมบูรณ์");
                roomFrame.dispose();  // ปิดหน้าต่างห้องเมื่อเลือกเสร็จ
            });
            roomFrame.add(roomButton);
        }

        roomFrame.setLocationRelativeTo(this);
        roomFrame.setVisible(true);
    }

    private void checkCardStatus(String cardId) {
        String status = DataModel.getCards().toString();  // ตรวจสอบสถานะจาก DataModel
        if ("Revoked".equals(status)) {
            JOptionPane.showMessageDialog(this, "❌ This card has been revoked and cannot be used.");
            return;  // ปิดการเข้าสู่ระบบ
        }
        // อนุญาตให้เข้าสู่ระบบถ้าสถานะไม่เป็น "Revoked"
    }



    public static void main(String... args) {
        new Login("229", "Bookbig");
    }

}

