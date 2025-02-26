//
//import javax.swing.*;
//        import java.awt.*;
//        import java.awt.event.MouseAdapter;
//import java.awt.event.MouseEvent;
//
//public class MainFrame extends JFrame {
//    private JPanel sidebar;
//    private JPanel mainPanel;
//    private boolean isCollapsed = false;
//
//    public MainFrame() {
//        setTitle("Condo Access Control");
//        setSize(900, 600);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setLocationRelativeTo(null);
//        setLayout(new BorderLayout());
//
//        // **สร้าง Sidebar**
//        sidebar = new JPanel();
//        sidebar.setLayout(new GridLayout(7, 1, 5, 5));
//        sidebar.setBackground(new Color(50, 80, 160)); // Modern Blue
//        sidebar.setPreferredSize(new Dimension(200, getHeight()));
//
//        // **เพิ่มโปรไฟล์ผู้ใช้**
//        JLabel userLabel = new JLabel("👤 Admin", SwingConstants.CENTER);
//        userLabel.setForeground(Color.WHITE);
//        userLabel.setFont(new Font("Arial", Font.BOLD, 16));
//        sidebar.add(userLabel);
//
//        // **ปุ่ม Toggle Sidebar**
//        JButton toggleButton = new JButton("☰");
//        toggleButton.setFont(new Font("Arial", Font.BOLD, 16));
//        toggleButton.setForeground(Color.WHITE);
//        toggleButton.setBackground(new Color(30, 60, 120));
//        toggleButton.addActionListener(e -> toggleSidebar());
//        sidebar.add(toggleButton);
//
//        // **เมนู**
//        String[] menuItems = {"Dashboard", "Card Management", "Access Control", "Logs & Audit Trail", "Settings"};
//        for (String item : menuItems) {
//            JButton btn = new JButton(item);
//            btn.setForeground(Color.WHITE);
//            btn.setBackground(new Color(30, 60, 120));
//            btn.setFocusPainted(false);
//            btn.setHorizontalAlignment(SwingConstants.LEFT);
//            btn.setBorderPainted(false);
//
//            // **เพิ่ม Hover Effect**
//            btn.addMouseListener(new MouseAdapter() {
//                public void mouseEntered(MouseEvent e) { btn.setBackground(new Color(80, 120, 200)); }
//                public void mouseExited(MouseEvent e) { btn.setBackground(new Color(30, 60, 120)); }
//            });
//
//            // **เปลี่ยนหน้าเมื่อกดปุ่ม**
//            btn.addActionListener(e -> switchPanel(item));
//            sidebar.add(btn);
//        }
//
//        // **ปุ่ม Logout**
//        JButton logoutButton = new JButton("🚪 Logout");
//        logoutButton.setForeground(Color.WHITE);
//        logoutButton.setBackground(new Color(200, 50, 50));
//        logoutButton.setFocusPainted(false);
//        logoutButton.addActionListener(e -> System.exit(0));
//        sidebar.add(logoutButton);
//
//        // **Main Panel**
//        mainPanel = new JPanel(new BorderLayout());
//        switchPanel("Dashboard");
//
//        // **เพิ่ม Sidebar และ Main Panel ลงใน Frame**
//        add(sidebar, BorderLayout.WEST);
//        add(mainPanel, BorderLayout.CENTER);
//
//        setVisible(true);
//    }
//
//    // **เปลี่ยนหน้า**
//    private void switchPanel(String panelName) {
//        mainPanel.removeAll();
//
//        switch (panelName) {
//            case "Dashboard":
//                mainPanel.add(new DashboardPanelA());
//                mainPanel.add(new DashboardPanel());
//                break;
//            case "Card Management":
//                mainPanel.add(new CardManagementPanel());
//                break;
//            case "Access Control":
//                mainPanel.add(new AccessControlPanel());
//                break;
//            case "Logs & Audit Trail":
//                mainPanel.add(new LogsPanel());
//                break;
//            case "Settings":
//                mainPanel.add(new SettingsPanel());
//                break;
//            default:
//                mainPanel.add(new JLabel("Unknown Page", SwingConstants.CENTER));
//        }
//
//        mainPanel.revalidate();
//        mainPanel.repaint();
//    }
//
//    // **Toggle Sidebar (ยุบ/ขยาย)**
//    private void toggleSidebar() {
//        if (isCollapsed) {
//            sidebar.setPreferredSize(new Dimension(200, getHeight()));
//        } else {
//            sidebar.setPreferredSize(new Dimension(50, getHeight()));
//        }
//        isCollapsed = !isCollapsed;
//        sidebar.revalidate();
//    }
//
//    public static void main(String[] args) {
//        new MainFrame();
//    }
//}


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainFrame extends JFrame {
    private JPanel sidebar;
    private JPanel mainPanel;
    private boolean isCollapsed = false;

    public MainFrame() {
        setTitle("🪷 The White Lotus");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // **สร้าง Sidebar**
        sidebar = new JPanel();
        sidebar.setLayout(new GridLayout(8, 1, 5, 5)); // เพิ่มระยะห่างให้ปุ่มอ่านง่ายขึ้น
//        sidebar.setBackground(Color.BLUE); // เปลี่ยน
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
        toggleButton.setForeground(Color.BLACK); //เปลี่ยนสีจากขาวเป็นดำ
        toggleButton.setBackground(Color.DARK_GRAY);
        toggleButton.addActionListener(e -> toggleSidebar());
        sidebar.add(toggleButton);

        // **เมนู**
        String[] menuItems = { "Card Management", "Logs & Audit Trail", "Dashboard", "Access Control", "Settings"};
        for (String item : menuItems) {
            JButton btn = new JButton(item);
            btn.setForeground(Color.WHITE);
            btn.setBackground(Color.DARK_GRAY);
            btn.setFocusPainted(false);
            btn.setHorizontalAlignment(SwingConstants.LEFT);
            btn.setBorderPainted(false);
            btn.setFont(new Font("Arial", Font.BOLD, 14)); // ขยายขนาดตัวอักษรให้อ่านง่าย
            btn.setMargin(new Insets(10, 20, 10, 20));

            // **เพิ่ม Hover Effect**
            btn.addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent e) {
                    btn.setBackground(new Color(80, 120, 200));
                }

                public void mouseExited(MouseEvent e) {
                    btn.setBackground(Color.DARK_GRAY);
                }
            });

            // **เปลี่ยนหน้าเมื่อกดปุ่ม**
            btn.addActionListener(e -> switchPanel(item));
            sidebar.add(btn);
        }

        // **ปุ่ม Logout**
        JButton logoutButton = new JButton("🚪 Logout");
        logoutButton.setForeground(Color.BLACK);
        logoutButton.setBackground(new Color(200, 50, 50));
        logoutButton.setFocusPainted(false);
        logoutButton.setFont(new Font("Arial", Font.BOLD, 14));
        logoutButton.setMargin(new Insets(10, 20, 10, 20));
        logoutButton.addActionListener(e -> System.exit(0));
        sidebar.add(logoutButton);

        // **Main Panel**
        mainPanel = new JPanel(new BorderLayout());
        switchPanel("Dashboard");

        // **เพิ่ม Sidebar และ Main Panel ลงใน Frame**
        add(sidebar, BorderLayout.WEST);
        add(mainPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    // **เปลี่ยนหน้า**
    private void switchPanel(String panelName) {
        mainPanel.removeAll();

        switch (panelName) {
            case "Card Management":
                AccessControlPanel accessControlPanel = null;
                mainPanel.add(new CardManagementPanel(accessControlPanel));
                break;

            case "Logs & Audit Trail":
                mainPanel.add(new LogsAuditTrailPanel());
                break;

            case "Dashboard":
//                mainPanel.add(new DashboardPanel());
                mainPanel.add(new DashboardPanel());
                break;

            case "Access Control":

                mainPanel.add(new AccessControlPanel());
                break;

            case "Settings":
                mainPanel.add(new SettingsPanel());
                break;
            default:
                mainPanel.add(new JLabel("Unknown Page", SwingConstants.CENTER));
        }


        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private JPanel createDashboardPanel() {
        JPanel dashboardPanel = new JPanel(new BorderLayout());
        JLabel statusLabel = new JLabel("🔄 System Status: Normal", SwingConstants.CENTER);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 18));

        String[] columnNames = {"Card ID", "User", "Time", "Status"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable logTable = new JTable(tableModel);
        JScrollPane logScrollPane = new JScrollPane(logTable);

        JPanel buttonPanel = new JPanel();
        JButton addCardBtn = new JButton("➕ Add New Card");
        JButton suspendCardBtn = new JButton("❌ Suspend Card");
        JButton viewLogsBtn = new JButton("📜 View Logs");

        addCardBtn.addActionListener(e -> addNewCard());
        suspendCardBtn.addActionListener(e -> suspendCard());
        viewLogsBtn.addActionListener(e -> JOptionPane.showMessageDialog(this, "Viewing Logs..."));

        buttonPanel.add(addCardBtn);
        buttonPanel.add(suspendCardBtn);
        buttonPanel.add(viewLogsBtn);

        dashboardPanel.add(statusLabel, BorderLayout.NORTH);
        dashboardPanel.add(logScrollPane, BorderLayout.CENTER);
        dashboardPanel.add(buttonPanel, BorderLayout.SOUTH);

        return dashboardPanel;
    }

    private void addNewCard() {
        JTextField cardIdField = new JTextField();
        JTextField userField = new JTextField();
        String[] options = {"Granted", "Denied"};
        JComboBox<String> statusBox = new JComboBox<>(options);

        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(new JLabel("Card ID:"));
        panel.add(cardIdField);
        panel.add(new JLabel("User:"));
        panel.add(userField);
        panel.add(new JLabel("Status:"));
        panel.add(statusBox);

        int result = JOptionPane.showConfirmDialog(this, panel, "Add New Card", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String cardId = cardIdField.getText();
            String user = userField.getText();
            String status = (String) statusBox.getSelectedItem();
            if (!cardId.isEmpty() && !user.isEmpty()) {
                DefaultTableModel tableModel = null;
                tableModel.addRow(new String[]{cardId, user, "Now", status});
            }
        }
    }

    private void suspendCard() {
        JTable logTable = null;

        int selectedRow = logTable.getSelectedRow();
        if (selectedRow != -1) {
            DefaultTableModel tableModel = null;
            tableModel.setValueAt("Denied", selectedRow, 3);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a card to suspend.");
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





//import javax.swing.*;
//import javax.swing.table.DefaultTableModel;
//import java.awt.*;
//import java.awt.event.MouseAdapter;
//import java.awt.event.MouseEvent;
//import java.util.ArrayList;
//
//public class MainFrame extends JFrame {
//    private JPanel sidebar;
//    private JPanel mainPanel;
//    private boolean isCollapsed = false;
//    private DefaultTableModel tableModel;
//    private JTable logTable;
//    private ArrayList<String[]> cardData = new ArrayList<>();
//
//    public MainFrame() {
//        setTitle("Condo Access Control");
//        setSize(900, 600);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setLocationRelativeTo(null);
//        setLayout(new BorderLayout());
//
//        sidebar = new JPanel();
//        sidebar.setLayout(new GridLayout(8, 1, 5, 5));
//        sidebar.setLayout(new BorderLayout());
//        sidebar.setBackground(new Color(50, 80, 160));
//        sidebar.setPreferredSize(new Dimension(200, getHeight()));
//
//
//        JPanel topPanel = new JPanel();
//        topPanel.setBackground(Color.BLACK);
//        topPanel.setPreferredSize(new Dimension(200, 50));
//
//        JLabel userLabel = new JLabel("👤 Admin", SwingConstants.CENTER);
//        userLabel.setForeground(Color.WHITE);
//        userLabel.setFont(new Font("Arial", Font.BOLD, 16));
//        topPanel.add(userLabel);
//
//        JPanel menuPanel = new JPanel();
//        menuPanel.setLayout(new GridLayout(6, 1, 5, 5));
//        menuPanel.setBackground(new Color(50, 80, 160));
//
//        String[][] menuItems = {
//                {"🏠 Dashboard"},
//                {"💳 Card Management"},
//                {"🔒 Access Control"},
//                {"📜 Logs & Audit Trail"},
//                {"⚙️ Settings"}
//        };
//        for (String[] item : menuItems) {
//            JButton btn = new JButton(item[0]);
//            btn.setForeground(Color.WHITE);
//            btn.setBackground(new Color(30, 60, 120));
//            btn.setFocusPainted(false);
//            btn.setHorizontalAlignment(SwingConstants.LEFT);
//            btn.setBorderPainted(false);
//            btn.setFont(new Font("Arial", Font.BOLD, 14));
//            btn.setPreferredSize(new Dimension(200, 40));
//
//            btn.addMouseListener(new MouseAdapter() {
//                public void mouseEntered(MouseEvent e) { btn.setBackground(new Color(80, 120, 200)); }
//                public void mouseExited(MouseEvent e) { btn.setBackground(new Color(30, 60, 120)); }
//            });
//
//            btn.addActionListener(e -> switchPanel(item[0]));
//            menuPanel.add(btn);
//        }
//
//        JPanel bottomPanel = new JPanel();
//        bottomPanel.setBackground(Color.BLACK);
//        bottomPanel.setPreferredSize(new Dimension(200, 50));
//
//        JButton toggleButton = new JButton("☰");
//        toggleButton.setFont(new Font("Arial", Font.BOLD, 16));
//        toggleButton.setForeground(Color.WHITE);
//        toggleButton.setBackground(Color.BLACK);
//        toggleButton.addActionListener(e -> toggleSidebar());
//        bottomPanel.add(toggleButton);
//
//        sidebar.add(topPanel, BorderLayout.NORTH);
//        sidebar.add(menuPanel, BorderLayout.CENTER);
//        sidebar.add(bottomPanel, BorderLayout.SOUTH);
//
//        mainPanel = new JPanel(new BorderLayout());
//        switchPanel("🏠 Dashboard");
//
//        add(sidebar, BorderLayout.WEST);
//        add(mainPanel, BorderLayout.CENTER);
//
//        setVisible(true);
//    }
//
//    private void switchPanel(String panelName) {
//        mainPanel.removeAll();
//
//        if (panelName.equals("🏠 Dashboard")) {
//            mainPanel.add(createDashboardPanel(), BorderLayout.CENTER);
//        } else {
//            mainPanel.add(new JLabel("Page: " + panelName, SwingConstants.CENTER));
//        }
//
//        mainPanel.revalidate();
//        mainPanel.repaint();
//    }
//
//    private JPanel createDashboardPanel() {
//        JPanel dashboardPanel = new JPanel(new BorderLayout());
//        JLabel statusLabel = new JLabel("🔄 System Status: Normal", SwingConstants.CENTER);
//        statusLabel.setFont(new Font("Arial", Font.BOLD, 18));
//
//        String[] columnNames = {"Card ID", "User", "Time", "Status"};
//        tableModel = new DefaultTableModel(columnNames, 0);
//        logTable = new JTable(tableModel);
//        JScrollPane logScrollPane = new JScrollPane(logTable);
//
//        JPanel buttonPanel = new JPanel();
//        JButton addCardBtn = new JButton("➕ Add New Card");
//        JButton suspendCardBtn = new JButton("❌ Suspend Card");
//        JButton viewLogsBtn = new JButton("📜 View Logs");
//
//        addCardBtn.addActionListener(e -> addNewCard());
//        suspendCardBtn.addActionListener(e -> suspendCard());
//        viewLogsBtn.addActionListener(e -> JOptionPane.showMessageDialog(this, "Viewing Logs..."));
//
//        buttonPanel.add(addCardBtn);
//        buttonPanel.add(suspendCardBtn);
//        buttonPanel.add(viewLogsBtn);
//
//        dashboardPanel.add(statusLabel, BorderLayout.NORTH);
//        dashboardPanel.add(logScrollPane, BorderLayout.CENTER);
//        dashboardPanel.add(buttonPanel, BorderLayout.SOUTH);
//
//        return dashboardPanel;
//    }
//
//    private void addNewCard() {
//        JTextField cardIdField = new JTextField();
//        JTextField userField = new JTextField();
//        String[] options = {"Granted", "Denied"};
//        JComboBox<String> statusBox = new JComboBox<>(options);
//
//        JPanel panel = new JPanel(new GridLayout(3, 2));
//        panel.add(new JLabel("Card ID:"));
//        panel.add(cardIdField);
//        panel.add(new JLabel("User:"));
//        panel.add(userField);
//        panel.add(new JLabel("Status:"));
//        panel.add(statusBox);
//
//        int result = JOptionPane.showConfirmDialog(this, panel, "Add New Card", JOptionPane.OK_CANCEL_OPTION);
//        if (result == JOptionPane.OK_OPTION) {
//            String cardId = cardIdField.getText();
//            String user = userField.getText();
//            String status = (String) statusBox.getSelectedItem();
//            if (!cardId.isEmpty() && !user.isEmpty()) {
//                tableModel.addRow(new String[]{cardId, user, "Now", status});
//            }
//        }
//    }
//
//    private void suspendCard() {
//        int selectedRow = logTable.getSelectedRow();
//        if (selectedRow != -1) {
//            tableModel.setValueAt("Denied", selectedRow, 3);
//        } else {
//            JOptionPane.showMessageDialog(this, "Please select a card to suspend.");
//        }
//    }
//
//    private void toggleSidebar() {
//        if (isCollapsed) {
//            sidebar.setPreferredSize(new Dimension(200, getHeight()));
//        } else {
//            sidebar.setPreferredSize(new Dimension(50, getHeight()));
//        }
//        isCollapsed = !isCollapsed;
//        sidebar.revalidate();
//    }
//
//    public static void main(String[] args) {
//        new MainFrame();
//    }
//}
