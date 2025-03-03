import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsPanel extends JPanel {
    public SettingsPanel(JFrame parentFrame) { // รับ JFrame หลักเข้ามา
        setLayout(new GridBagLayout());
        setBackground(new Color(220, 230, 250));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel title = new JLabel("⚙ Settings");
        title.setFont(new Font("Arial", Font.BOLD, 26));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(title, gbc);


// Setting
        // Theme Toggle
        gbc.gridwidth = 1;
        gbc.gridy++;
        add(new JLabel("Theme:"), gbc);
        gbc.gridx = 1;
        String[] themes = {"Light Mode", "Dark Mode"};
        JComboBox<String> themeSelector = new JComboBox<>(themes);
        add(themeSelector, gbc);

        // Font Size Adjustment
        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Font Size:"), gbc);
        gbc.gridx = 1;
        String[] fontSizes = {"Small", "Medium", "Large"};
        JComboBox<String> fontSizeSelector = new JComboBox<>(fontSizes);
        add(fontSizeSelector, gbc);

        // Logout Button
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        JButton logoutButton = new JButton("Logout");

        // 🛠 เพิ่ม ActionListener ให้ปุ่ม Logout
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(
                        parentFrame,
                        "Are you sure you want to logout?",
                        "Logout Confirmation",
                        JOptionPane.YES_NO_OPTION
                );

                if (confirm == JOptionPane.YES_OPTION) {
                    parentFrame.dispose(); // ปิด JFrame หลัก
                    System.exit(0); // ออกจากโปรแกรม
                }
            }
        });

        add(logoutButton, gbc);
    }


}
