//
//import javax.swing.*;
//        import java.awt.*;
//
//public class CardManagementPanel extends JPanel {
//    public CardManagementPanel() {
//        setLayout(new BorderLayout());
//        setBackground(new Color(220, 230, 250)); // สีพื้นหลังฟ้าอ่อน
//
//        setLayout(new BorderLayout());
//        JLabel title = new JLabel("🔑 Card Management", SwingConstants.CENTER);
//        title.setFont(new Font("Arial", Font.BOLD, 24));
//        add(title, BorderLayout.NORTH);
//        add(new JLabel("🔑 Card Management", SwingConstants.CENTER), BorderLayout.CENTER);
//
//        // กล่องเนื้อหาหลัก
//        JPanel contentPanel = new JPanel();
//        contentPanel.setBackground(new Color(220, 230, 250));
//        contentPanel.setLayout(new GridBagLayout());
//        GridBagConstraints gbc = new GridBagConstraints();
//        gbc.insets = new Insets(5, 5, 5, 5);
//        gbc.fill = GridBagConstraints.HORIZONTAL;
//        gbc.gridx = 0;
//        gbc.gridy = 0;
//
//
//        // ฟอร์มข้อมูล
//        gbc.gridy++;
//        contentPanel.add(new JLabel("Card ID :"), gbc);
//        gbc.gridx++;
//        JTextField cardIdField = new JTextField(15);
//        contentPanel.add(cardIdField, gbc);
//        gbc.gridx = 0;
//        gbc.gridy++;
//        contentPanel.add(new JLabel("Cardholder Name:"), gbc);
//        gbc.gridx++;
//        JTextField cardholderNameField = new JTextField(15);
//        contentPanel.add(cardholderNameField, gbc);
//
//        // Permissions
//        gbc.gridx = 0;
//        gbc.gridy++;
//        contentPanel.add(new JLabel("Permissions:"), gbc);
//        gbc.gridx++;
//        JPanel permissionsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
//        JButton floor1Btn = new JButton("Floor 1");
//        JButton floor2Btn = new JButton("Floor 2");
//        JButton floor3Btn = new JButton("Floor 3");
//        permissionsPanel.add(floor1Btn);
//        permissionsPanel.add(floor2Btn);
//        permissionsPanel.add(floor3Btn);
//        contentPanel.add(permissionsPanel, gbc);
//
//        // Password Fields
//        gbc.gridx = 0;
//        gbc.gridy++;
//        contentPanel.add(new JLabel("Password:"), gbc);
//        gbc.gridx++;
//        JPasswordField passwordField = new JPasswordField(15);
//        contentPanel.add(passwordField, gbc);
//
//        gbc.gridx = 0;
//        gbc.gridy++;
//        contentPanel.add(new JLabel("Confirm Password:"), gbc);
//        gbc.gridx++;
//        JPasswordField confirmPasswordField = new JPasswordField(15);
//        contentPanel.add(confirmPasswordField, gbc);
//
//        // ปุ่ม Add และ Revoke
//        gbc.gridx = 0;
//        gbc.gridy++;
//        gbc.gridwidth = 2;
//        JPanel buttonPanel = new JPanel();
//        JButton addButton = new JButton("Add");
//        JButton revokeButton = new JButton("Revoke cards");
//        addButton.setBackground(new Color(128, 0, 128));
//        addButton.setForeground(Color.WHITE);
//        revokeButton.setBackground(new Color(128, 0, 128));
//        revokeButton.setForeground(Color.WHITE);
//        buttonPanel.add(addButton);
//        buttonPanel.add(revokeButton);
//        contentPanel.add(buttonPanel, gbc);
//
//        // ไอคอนการตั้งค่า
//        gbc.gridy++;
//        gbc.anchor = GridBagConstraints.EAST;
//        JButton settingsButton = new JButton("⚙");
//        settingsButton.setBorderPainted(false);
//        settingsButton.setContentAreaFilled(false);
//        contentPanel.add(settingsButton, gbc);
//
//        add(contentPanel, BorderLayout.CENTER);
//    }
//}
import javax.swing.*;
import java.awt.*;

public class CardManagementPanel extends JPanel {
    public CardManagementPanel() {
        setLayout(new GridBagLayout());
        setBackground(new Color(220, 230, 250)); // สีพื้นหลังตาม UI

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel title = new JLabel("\uD83D\uDD11 Card Management");
        title.setFont(new Font("Arial", Font.BOLD, 26));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(title, gbc);

        gbc.gridwidth = 1;
        gbc.gridy++;
        add(new JLabel("Card ID :"), gbc);
        gbc.gridx = 1;
        add(new JTextField(20), gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Cardholder Name:"), gbc);
        gbc.gridx = 1;
        add(new JTextField(20), gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Permissions:"), gbc);

        JPanel permissionsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        permissionsPanel.setBackground(new Color(220, 230, 250));
        permissionsPanel.add(new JButton("Floor 1"));
        permissionsPanel.add(new JButton("Floor 2"));
        permissionsPanel.add(new JButton("Floor 3"));

        gbc.gridx = 1;
        add(permissionsPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Password:"), gbc);
        gbc.gridx = 1;
        add(new JPasswordField(20), gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Confirm Password:"), gbc);
        gbc.gridx = 1;
        add(new JPasswordField(20), gbc);

        // ปุ่ม Add และ Revoke Cards
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;

        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add");
        JButton revokeButton = new JButton("Revoke cards");
        addButton.setPreferredSize(new Dimension(150, 40));
        revokeButton.setPreferredSize(new Dimension(150, 40));

        buttonPanel.add(addButton);
        buttonPanel.add(revokeButton);
        add(buttonPanel, gbc);

        // ปุ่มฟันเฟือง
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.SOUTHEAST;
        JButton settingsButton = new JButton("⚙");
        settingsButton.setPreferredSize(new Dimension(40, 40));
        add(settingsButton, gbc);
    }
}
