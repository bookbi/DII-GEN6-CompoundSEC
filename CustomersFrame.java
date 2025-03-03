import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class CustomersFrame extends JFrame {
    private List<String[]> signupDataList = new ArrayList<>();

    public CustomersFrame() {
        setTitle("The White Lotus");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(new Color(200, 220, 255));
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        backgroundPanel.setLayout(null);
        add(backgroundPanel);

        JPanel verticalPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(new Color(50, 80, 160));
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        verticalPanel.setBounds(350, 0, 300, 600);
        backgroundPanel.add(verticalPanel);
        verticalPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;

        JLabel titleLabel = new JLabel("\uD83E\uDEB7 The White Lotus");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        gbc.gridy++;
        verticalPanel.add(titleLabel, gbc);

        JPanel topBar = createRoundedPanel(280, 60);
        JPanel bottomBar = createRoundedPanel(280, 60);
        gbc.gridy++;
        verticalPanel.add(topBar, gbc);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 1, 10, 10));
        buttonPanel.setOpaque(false);

        JButton signUpButton = new JButton("Sign up");
        JButton loginButton = new JButton("Log in");

        signUpButton.addActionListener(e -> {
            new Signup(this, signupDataList);
            setVisible(false);
        });

        loginButton.addActionListener(e -> {
            new Login();
            dispose();
        });

        styleRoundedButton(signUpButton);
        styleRoundedButton(loginButton);
        buttonPanel.add(signUpButton);
        buttonPanel.add(loginButton);

        gbc.gridy++;
        verticalPanel.add(buttonPanel, gbc);
        gbc.gridy++;
        verticalPanel.add(bottomBar, gbc);

        JButton backButton = createCircularButton("\u2B05", new Color(80, 60, 140));
        backButton.setBounds(20, 500, 50, 50);
        backgroundPanel.add(backButton);
        backButton.addActionListener(e -> {
            new Main();
            dispose();
        });

        JButton settingsButton = createCircularButton("\u2699", new Color(80, 60, 140));
        settingsButton.setBounds(930, 500, 50, 50);
        backgroundPanel.add(settingsButton);
        settingsButton.addActionListener(e -> {
            JFrame settingsFrame = new JFrame("Settings");
            settingsFrame.setSize(600, 400);
            settingsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            settingsFrame.setLocationRelativeTo(null);
            settingsFrame.add(new SettingsPanel(this));
            settingsFrame.setVisible(true);
        });

        JButton dashboardButton = new JButton("Admin Dashboard");
        dashboardButton.setBounds(380, 520, 250, 40);
        backgroundPanel.add(dashboardButton);
        dashboardButton.addActionListener(e -> openDashboard());

        setVisible(true);
    }

    public void addSignupData(String[] data) {
        signupDataList.add(data);
    }

    public List<String[]> getSignupData() {
        return signupDataList;  // ✅ ให้ DashboardPanel และ AccessControlPanel ใช้
    }


    // ✅ เปิด Dashboard พร้อมส่ง CustomersFrame ไปให้มันใช้งาน
    private void openDashboard() {
        new DashboardPanel(this); // ✅ ส่ง CustomersFrame ไปให้ DashboardPanel ใช้ getSignupData()
    }


    private void styleRoundedButton(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setBackground(new Color(20, 60, 120));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setPreferredSize(new Dimension(220, 60));
    }

    private JPanel createRoundedPanel(int width, int height) {
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.WHITE);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 40, 40);
            }
        };
        panel.setPreferredSize(new Dimension(width, height));
        panel.setOpaque(false);
        return panel;
    }

    private JButton createCircularButton(String symbol, Color bgColor) {
        JButton button = new JButton(symbol);
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setForeground(Color.WHITE);
        button.setBackground(bgColor);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setPreferredSize(new Dimension(50, 50));
        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CustomersFrame::new);
    }

    public void updateAdminData() {
    }
}
