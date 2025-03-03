
import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private JPanel sidebar;
    private JPanel mainPanel;
    private boolean isCollapsed = false;
    private CustomersFrame customersFrame;  // ✅ เก็บ CustomersFrame

    public MainFrame() {
        setTitle("🪷 The White Lotus");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        customersFrame = new CustomersFrame();  // ✅ สร้าง CustomersFrame

        // **สร้าง Sidebar**
        sidebar = new JPanel();
        sidebar.setLayout(new GridLayout(8, 1, 5, 5));
        sidebar.setBackground(new Color(50, 80, 160)); // Modern Blue
        sidebar.setPreferredSize(new Dimension(220, getHeight()));


        // **เพิ่มโปรไฟล์ผู้ใช้**
        JLabel userLabel = new JLabel("🪷 The White Lotus", SwingConstants.CENTER);
        userLabel.setForeground(Color.WHITE);
        userLabel.setFont(new Font("Arial", Font.BOLD, 18));
        sidebar.add(userLabel);

        // **ปุ่ม Toggle Sidebar**
        JButton toggleButton = new JButton("☰");
        toggleButton.setFont(new Font("Arial", Font.BOLD, 16));
        toggleButton.setForeground(Color.BLACK);
        toggleButton.setBackground(Color.DARK_GRAY);
        toggleButton.addActionListener(e -> toggleSidebar());
        sidebar.add(toggleButton);

        // **เมนู**
        String[] menuItems = {"Card Management", "Dashboard", "Access Control", "Settings"};
        for (String item : menuItems) {
            JButton btn = new JButton(item);
            btn.setForeground(Color.WHITE);
            btn.setBackground(Color.DARK_GRAY);
            btn.setFocusPainted(false);
            btn.setHorizontalAlignment(SwingConstants.LEFT);
            btn.setBorderPainted(false);
            btn.setFont(new Font("Arial", Font.BOLD, 14));
            btn.setMargin(new Insets(10, 20, 10, 20));

            btn.addActionListener(e -> switchPanel(item));
            sidebar.add(btn);
        }

        // **ปุ่ม Logout**
        JButton logoutButton = new JButton("⬅");
        logoutButton.setForeground(Color.BLACK);
        logoutButton.setBackground(new Color(200, 50, 50));
        logoutButton.setFocusPainted(false);
        logoutButton.setFont(new Font("Arial", Font.BOLD, 14));
        logoutButton.setMargin(new Insets(10, 20, 10, 20));
        logoutButton.addActionListener(e -> {
            new Main(); // เปิด CustomersFrame
            dispose(); // ปิด MainFrame
        });
        sidebar.add(logoutButton);

        // **Main Panel**
        mainPanel = new JPanel(new BorderLayout());
        switchPanel("Dashboard");

        add(sidebar, BorderLayout.WEST);
        add(mainPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private void switchPanel(String panelName) {
        System.out.println("Switching to: " + panelName); // ✅ Debug ตรงนี้
        mainPanel.removeAll();

        switch (panelName) {
            case "Card Management":
                System.out.println("Opening CardManagementPanel..."); // ✅ Debug ตรงนี้
                mainPanel.add(new CardManagementPanel(customersFrame));
                break;
            case "Dashboard":
                mainPanel.add(new DashboardPanel(customersFrame));
                break;
            case "Access Control":
                mainPanel.add(new AccessControlPanel());
                break;
            case "Settings":
                mainPanel.add(new SettingsPanel(this));
                break;
            default:
                mainPanel.add(new JLabel("Unknown Page", SwingConstants.CENTER));
        }

        mainPanel.revalidate();
        mainPanel.repaint();
        if (customersFrame == null) {
            System.out.println("customersFrame is NULL! Check where it is initialized.");
        }

    }



    // **Toggle Sidebar (ยุบ/ขยาย)**
    private void toggleSidebar() {
        if (isCollapsed) {
            sidebar.setPreferredSize(new Dimension(220, getHeight()));
        } else {
            sidebar.setPreferredSize(new Dimension(60, getHeight()));
        }
        isCollapsed = !isCollapsed;
        sidebar.revalidate();
    }

    public static void main(String[] args) {
        new MainFrame();
    }
}



