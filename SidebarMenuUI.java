
import javax.swing.*;
        import java.awt.*;
        import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SidebarMenuUI extends JFrame {
    private JPanel sidebar;
    private JPanel mainPanel;
    private boolean isCollapsed = false; // ใช้สำหรับยุบ/ขยาย Sidebar

    public SidebarMenuUI() {
        setTitle("Sidebar Menu");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // **สร้าง Sidebar Panel**
        sidebar = new JPanel();
        sidebar.setLayout(new GridLayout(7, 1, 5, 5));
        sidebar.setBackground(new Color(50, 80, 160)); // Modern Blue
        sidebar.setPreferredSize(new Dimension(220, getHeight())); // ขยายให้ใหญ่ขึ้น

        // **ปุ่ม Toggle (ยุบ/ขยาย)**
        JButton toggleButton = new JButton("☰");
        toggleButton.setFont(new Font("Arial", Font.BOLD, 18)); // ขยายตัวอักษร
        toggleButton.setForeground(Color.WHITE);
        toggleButton.setBackground(new Color(30, 60, 120));
        toggleButton.setFocusPainted(false);
        toggleButton.addActionListener(e -> toggleSidebar());
        sidebar.add(toggleButton);

        // **สร้างเมนู Sidebar**
        String[] menuItems = {"Dashboard", "Card Management", "Access Control", "Logs & Audit Trail", "Settings"};
        String[] icons = {"🏠", "🔑", "🔒", "📜", "⚙️"};

        for (int i = 0; i < menuItems.length; i++) {
            JButton btn = new JButton(icons[i] + "  " + menuItems[i]);
            btn.setFont(new Font("Arial", Font.PLAIN, 16)); // เพิ่มขนาดตัวอักษร
            btn.setForeground(Color.WHITE);
            btn.setBackground(new Color(30, 60, 120));
            btn.setFocusPainted(false);
            btn.setHorizontalAlignment(SwingConstants.LEFT);
            btn.setBorderPainted(false);
            btn.setPreferredSize(new Dimension(200, 50)); // ขยายปุ่มให้สูงขึ้น

            // **เพิ่ม Hover Effect**
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

        // **สร้าง Main Panel (เปลี่ยนเนื้อหาตามเมนู)**
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(new JLabel("🏠 Welcome to Dashboard", SwingConstants.CENTER), BorderLayout.CENTER);

        // **เพิ่ม Sidebar และ Main Panel ลงใน Frame**
        add(sidebar, BorderLayout.WEST);
        add(mainPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    // **เมธอดสำหรับเปลี่ยนหน้าใน Main Panel**
    private void switchPanel(String title) {
        mainPanel.removeAll();
        JLabel titleLabel = new JLabel("📌 You selected: " + title, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20)); // ขยายตัวอักษรให้ชัดขึ้น
        mainPanel.add(titleLabel, BorderLayout.CENTER);
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    // **เมธอดสำหรับยุบ/ขยาย Sidebar**
    private void toggleSidebar() {
        if (isCollapsed) {
            sidebar.setPreferredSize(new Dimension(220, getHeight())); // ขยาย
        } else {
            sidebar.setPreferredSize(new Dimension(60, getHeight())); // ยุบ
        }
        isCollapsed = !isCollapsed;
        sidebar.revalidate();
    }

    public static void main(String[] args) {
        new SidebarMenuUI();
    }
}
