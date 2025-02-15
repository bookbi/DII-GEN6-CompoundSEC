import javax.swing.*;
import java.awt.*;

public class AccessControlUI extends JFrame {
    public AccessControlUI() {
        setTitle("Access Control System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Sidebar Menu (Panel ซ้าย)
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new GridLayout(5, 1, 5, 5));
        sidebar.setBackground(new Color(50, 80, 160)); // Modern Blue
        String[] menuItems = {"Dashboard", "Card Management", "Access Control", "Logs & Audit Trail", "Settings"};
        for (String item : menuItems) {
            JButton btn = new JButton(item);
            btn.setForeground(Color.WHITE);
            btn.setBackground(new Color(30, 60, 120)); // Darker Blue
            sidebar.add(btn);
        }
        add(sidebar, BorderLayout.WEST);

        // Main Panel (Form ควบคุมการเข้าถึง)
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(6, 2, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel lblCardID = new JLabel("Card ID:");
        JTextField txtCardID = new JTextField();
        JLabel lblHolder = new JLabel("Cardholder Name:");
        JTextField txtHolder = new JTextField();

        JLabel lblAccess = new JLabel("Permissions (Floors & Rooms):");
        JCheckBox chkFloor1 = new JCheckBox("Floor 1");
        JCheckBox chkFloor2 = new JCheckBox("Floor 2");
        JCheckBox chkFloor3 = new JCheckBox("Floor 3");

        JButton btnGrant = new JButton("Grant Access");
        JButton btnRevoke = new JButton("Revoke Access");
        JButton btnValidate = new JButton("Validate Access");

        btnGrant.setBackground(new Color(50, 200, 100)); // Green for access granted
        btnRevoke.setBackground(new Color(200, 50, 50)); // Red for revoke
        btnValidate.setBackground(new Color(150, 150, 150)); // Grey for neutral

        // Adding Components to Main Panel
        mainPanel.add(lblCardID);
        mainPanel.add(txtCardID);
        mainPanel.add(lblHolder);
        mainPanel.add(txtHolder);
        mainPanel.add(lblAccess);
        mainPanel.add(new JLabel()); // Empty Space
        mainPanel.add(chkFloor1);
        mainPanel.add(chkFloor2);
        mainPanel.add(chkFloor3);
        mainPanel.add(new JLabel()); // Empty Space
        mainPanel.add(btnGrant);
        mainPanel.add(btnRevoke);
        mainPanel.add(btnValidate);

        add(mainPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    public static void main(String[] args) {
        new AccessControlUI();
    }
}


