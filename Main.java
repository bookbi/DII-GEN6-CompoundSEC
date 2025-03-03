
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Main extends JFrame {
    public Main() {
        setTitle("The White Lotus");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setVisible(true);
        System.out.println("UI is launching...");

        // Background panel with light blue color
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(new Color(200, 220, 255)); // Light blue
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        backgroundPanel.setLayout(null);
        add(backgroundPanel);

        // Dark blue vertical rectangle in the center
        JPanel verticalPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(new Color(50, 80, 160)); // Modern Blue
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

        // Title Label
        JLabel titleLabel = new JLabel("\uD83E\uDEB7 The White Lotus");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        gbc.gridy++;
        verticalPanel.add(titleLabel, gbc);

        // White horizontal bars
        JPanel topBar = createRoundedPanel(280, 60);
        JPanel bottomBar = createRoundedPanel(280, 60);

        gbc.gridy++;
        verticalPanel.add(topBar, gbc);



        // Buttons panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 1, 10, 10));
        buttonPanel.setOpaque(false);

        JButton customerButton = new JButton("For Customers");
        JButton adminButton = new JButton("For Admin");



        // Button styling (rounded corners)
        styleRoundedButton(customerButton);
        styleRoundedButton(adminButton);

        buttonPanel.add(customerButton);
        buttonPanel.add(adminButton);

        gbc.gridy++;
        verticalPanel.add(buttonPanel, gbc);

        gbc.gridy++;
        verticalPanel.add(bottomBar, gbc);

        // Add action listeners to buttons
        customerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CustomersFrame(); // Open CustomersFrame
                dispose(); // Close Main frame
            }
        });

        adminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AdminLogin().setVisible(true);
                dispose(); // Close Main frame
            }
        });

        setVisible(true);
    }



    private void styleRoundedButton(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setBackground(new Color(20, 60, 120)); // Dark blue background
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setPreferredSize(new Dimension(220, 60));
        button.setUI(new RoundedButtonUI());
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::new);
    }
}

class RoundedButtonUI extends javax.swing.plaf.basic.BasicButtonUI {
    @Override
    public void paint(Graphics g, JComponent c) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(c.getBackground());
        g2.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), 30, 30);
        super.paint(g2, c);
    }
}
