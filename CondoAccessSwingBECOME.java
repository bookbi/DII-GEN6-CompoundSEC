
import javax.swing.*;
        import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CondoAccessSwingBECOME extends JFrame {
    private JPanel sidebar, mainPanel;
    private boolean isCollapsed = false; // ใช้สำหรับยุบ/ขยาย Sidebar
    private JLabel userLabel;
    private JButton toggleButton;

    public CondoAccessSwingBECOME() {
        setTitle("Condo Access Control");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // ** Sidebar Panel **
        sidebar = new JPanel();
        sidebar.setLayout(new GridLayout(8, 1, 5, 5));
        sidebar.setBackground(new Color(30, 60, 120)); // Default Blue
        sidebar.setPreferredSize(new Dimension(200, getHeight()));

        // ** User Profile (ด้านบน Sidebar) **
        userLabel = new JLabel("👤 Admin", SwingConstants.CENTER);
        userLabel.setForeground(Color.WHITE);
        userLabel.setFont(new Font("Arial", Font.BOLD, 16));
        sidebar.add(userLabel);

        // ** ปุ่ม Toggle Sidebar **
        toggleButton = new JButton("☰");
        toggleButton.setFont(new Font("Arial", Font.BOLD, 16));
        toggleButton.setForeground(Color.WHITE);
        toggleButton.setBackground(new Color(20, 40, 80));
        toggleButton.setFocusPainted(false);
        toggleButton.addActionListener(e -> toggleSidebar());
        sidebar.add(toggleButton);

        // ** รายการเมนู **
        String[] menuItems = {"Dashboard", "Card Management", "Access Control", "Logs & Audit Trail", "Settings", "Logout"};
        String[] icons = {"🏠", "🔑", "🔒", "📜", "⚙️", "🚪"};

        for (int i = 0; i < menuItems.length; i++) {
            JButton btn = new JButton(icons[i] + "  " + menuItems[i]);
            btn.setForeground(Color.WHITE);
            btn.setBackground(new Color(30, 60, 120));
            btn.setFocusPainted(false);
            btn.setHorizontalAlignment(SwingConstants.LEFT);
            btn.setBorderPainted(false);
            btn.setFont(new Font("Arial", Font.BOLD, 14));

            // ** Hover Effect **
            btn.addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent e) {
                    btn.setBackground(new Color(80, 120, 200));
                }
                public void mouseExited(MouseEvent e) {
                    btn.setBackground(new Color(30, 60, 120));
                }
            });

            int finalI = i;
            btn.addActionListener(e -> switchPanel(menuItems[finalI]));
            sidebar.add(btn);
        }

        // ** Main Panel **
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(new JLabel("Welcome to Dashboard", SwingConstants.CENTER), BorderLayout.CENTER);

        // ** เพิ่ม Sidebar และ Main Panel ลงใน Frame **
        add(sidebar, BorderLayout.WEST);
        add(mainPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    // ** เมธอดสำหรับเปลี่ยนหน้า **
    private void switchPanel(String title) {
        mainPanel.removeAll();
        JLabel titleLabel = new JLabel(title, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        mainPanel.add(titleLabel, BorderLayout.CENTER);
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    // ** เมธอดสำหรับยุบ/ขยาย Sidebar **
    private void toggleSidebar() {
        new Thread(() -> {
            try {
                int step = isCollapsed ? 10 : -10;
                int targetSize = isCollapsed ? 200 : 50;
                int currentSize = sidebar.getPreferredSize().width;

                while ((isCollapsed && currentSize < targetSize) || (!isCollapsed && currentSize > targetSize)) {
                    currentSize += step;
                    sidebar.setPreferredSize(new Dimension(currentSize, getHeight()));
                    sidebar.revalidate();
                    Thread.sleep(10);
                }

                // เปลี่ยนสีตอนยุบ
                if (isCollapsed) {
                    sidebar.setBackground(new Color(30, 60, 120));
                } else {
                    sidebar.setBackground(Color.BLACK);
                }
                isCollapsed = !isCollapsed;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static void main(String[] args) {
        new CondoAccessSwingBECOME();
    }
}
