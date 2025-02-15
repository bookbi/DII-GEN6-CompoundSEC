
import javax.swing.*;
        import java.awt.*;
        import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class sidebarMenuUI extends JFrame {
    private JPanel sidebar;
    private JPanel mainPanel;
    private boolean isCollapsed = true;
    private Timer animationTimer;
    private int sidebarX = -180;
    private final int expandedX = 0;
    private final int collapsedX = -180;
    private boolean isDarkMode = false;
    private JLabel userLabel;

    public sidebarMenuUI() {
        setTitle("Smart Condo Access Control");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // **สร้าง Sidebar Panel**
        sidebar = new JPanel();
        sidebar.setLayout(new GridLayout(8, 1, 5, 5));
        sidebar.setBackground(new Color(50, 80, 160));
        sidebar.setBounds(sidebarX, 0, 180, getHeight());

        // **แสดงชื่อผู้ใช้**
        userLabel = new JLabel("👤 Welcome, Admin", SwingConstants.CENTER);
        userLabel.setForeground(Color.WHITE);
        sidebar.add(userLabel);

        // **ปุ่ม Toggle Sidebar**
        JButton toggleButton = new JButton(new ImageIcon("icons/menu.png"));
        toggleButton.setBackground(new Color(30, 60, 120));
        toggleButton.setFocusPainted(false);
        toggleButton.addActionListener(e -> slideSidebar());
        sidebar.add(toggleButton);

        // **สร้างเมนู**
        String[] menuItems = {"Dashboard", "Card Management", "Access Control", "Logs & Audit Trail", "Settings"};
        String[] iconPaths = {"icons/home.png", "icons/card.png", "icons/lock.png", "icons/logs.png", "icons/settings.png"};

        for (int i = 0; i < menuItems.length; i++) {
            JButton btn = createAnimatedButton(menuItems[i], iconPaths[i]);
            int finalI = i;
            btn.addActionListener(e -> switchPanel(menuItems[finalI]));
            sidebar.add(btn);
        }

        // **ปุ่มเปลี่ยน Dark/Light Mode**
        JButton themeButton = new JButton("🌙 Dark Mode");
        themeButton.setBackground(new Color(100, 100, 100));
        themeButton.setForeground(Color.WHITE);
        themeButton.setFocusPainted(false);
        themeButton.addActionListener(e -> toggleTheme(themeButton));
        sidebar.add(themeButton);

        // **ปุ่ม Logout**
        JButton logoutButton = new JButton("🚪 Logout");
        logoutButton.setBackground(Color.RED);
        logoutButton.setForeground(Color.WHITE);
        logoutButton.setFocusPainted(false);
        logoutButton.addActionListener(e -> System.exit(0));
        sidebar.add(logoutButton);

        // **สร้าง Main Panel (รองรับ Transition)**
        mainPanel = new JPanel(new CardLayout());
        mainPanel.setBounds(0, 0, getWidth(), getHeight());
        mainPanel.add(new DashboardPanel(), "Dashboard");
        mainPanel.add(new CardManagementPanel(), "Card Management");
        mainPanel.add(new AccessControlPanel(), "Access Control");
        mainPanel.add(new LogsAuditTrailPanel(), "Logs & Audit Trail");
        mainPanel.add(new SettingsPanel(), "Settings");

        add(sidebar);
        add(mainPanel);
        setVisible(true);
    }

    // **เมธอดสร้างปุ่มเมนูที่มี Animation**
    private JButton createAnimatedButton(String text, String iconPath) {
        JButton btn = new JButton(text, new ImageIcon(iconPath));
        btn.setForeground(Color.WHITE);
        btn.setBackground(new Color(30, 60, 120));
        btn.setFocusPainted(false);
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setBorderPainted(false);

        // **เพิ่ม Hover Effect**
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setBackground(new Color(80, 120, 200));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setBackground(new Color(30, 60, 120));
            }
        });

        // **เพิ่ม Animation เวลา Click (เด้งเล็กน้อย)**
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Timer bounceTimer = new Timer(10, new ActionListener() {
                    int step = 0;
                    boolean expanding = true;

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (expanding) {
                            btn.setBounds(btn.getX(), btn.getY() - 2, btn.getWidth(), btn.getHeight());
                            step++;
                            if (step > 3) expanding = false;
                        } else {
                            btn.setBounds(btn.getX(), btn.getY() + 2, btn.getWidth(), btn.getHeight());
                            step--;
                            if (step <= 0) ((Timer) e.getSource()).stop();
                        }
                    }
                });
                bounceTimer.start();
            }
        });

        return btn;
    }

    // **เมธอดเปลี่ยนหน้าใน Main Panel พร้อม Fade In Effect**
    private void switchPanel(String panelName) {
        CardLayout cl = (CardLayout) (mainPanel.getLayout());
        JPanel currentPanel = (JPanel) mainPanel.getComponent(0);
        currentPanel.setOpaque(false);

        Timer fadeIn = new Timer(20, new ActionListener() {
            float opacity = 0.0f;

            @Override
            public void actionPerformed(ActionEvent e) {
                opacity += 0.1f;
                if (opacity >= 1.0f) {
                    ((Timer) e.getSource()).stop();
                }
                currentPanel.repaint();
            }
        });

        fadeIn.start();
        cl.show(mainPanel, panelName);
    }

    // **Animation Slide Sidebar**
    private void slideSidebar() {
        int delay = 5;
        animationTimer = new Timer(delay, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (isCollapsed) {
                    if (sidebarX < expandedX) {
                        sidebarX += 10;
                        sidebar.setBounds(sidebarX, 0, 180, getHeight());
                    } else {
                        animationTimer.stop();
                        isCollapsed = false;
                    }
                } else {
                    if (sidebarX > collapsedX) {
                        sidebarX -= 10;
                        sidebar.setBounds(sidebarX, 0, 180, getHeight());
                    } else {
                        animationTimer.stop();
                        isCollapsed = true;
                    }
                }
            }
        });
        animationTimer.start();
    }

    // **Toggle Dark/Light Mode**
    private void toggleTheme(JButton themeButton) {
        if (isDarkMode) {
            getContentPane().setBackground(Color.WHITE);
            sidebar.setBackground(new Color(50, 80, 160));
            themeButton.setText("🌙 Dark Mode");
            isDarkMode = false;
        } else {
            getContentPane().setBackground(Color.DARK_GRAY);
            sidebar.setBackground(new Color(30, 30, 30));
            themeButton.setText("☀️ Light Mode");
            isDarkMode = true;
        }
        repaint();
    }

    // **หน้าต่างต่าง ๆ**
    class DashboardPanel extends JPanel {
        public DashboardPanel() {
            setLayout(new BorderLayout());
            add(new JLabel("🏠 Dashboard Overview", SwingConstants.CENTER), BorderLayout.CENTER);
        }
    }

    class CardManagementPanel extends JPanel {
        public CardManagementPanel() {
            setLayout(new BorderLayout());
            add(new JLabel("🔑 Manage Cards", SwingConstants.CENTER), BorderLayout.CENTER);
        }
    }

    class AccessControlPanel extends JPanel {
        public AccessControlPanel() {
            setLayout(new BorderLayout());
            add(new JLabel("🔒 Access Control Management", SwingConstants.CENTER), BorderLayout.CENTER);
        }
    }

    class LogsAuditTrailPanel extends JPanel {
        public LogsAuditTrailPanel() {
            setLayout(new BorderLayout());
            add(new JLabel("📜 View Logs & Audit Trail", SwingConstants.CENTER), BorderLayout.CENTER);
        }
    }

    class SettingsPanel extends JPanel {
        public SettingsPanel() {
            setLayout(new BorderLayout());
            add(new JLabel("⚙️ System Settings", SwingConstants.CENTER), BorderLayout.CENTER);
        }
    }

    public static void main(String[] args) {
        new sidebarMenuUI();
    }
}
