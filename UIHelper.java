import javax.swing.*;
import java.awt.*;

public class UIHelper {
    // ✅ ฟังก์ชันสร้างปุ่มที่มีสไตล์เดียวกัน
    public static JButton createStyledButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        return button;
    }

    // ✅ ฟังก์ชันสร้าง Sidebar แบบเดียวกัน
    public static JPanel createSidebar() {
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new GridLayout(8, 1, 5, 5));
        sidebar.setBackground(new Color(50, 80, 160));
        return sidebar;
    }

    // ✅ ฟังก์ชันสร้าง Label แบบเดียวกัน
    public static JLabel createTitleLabel(String text) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        label.setForeground(Color.WHITE);
        return label;
    }
}
