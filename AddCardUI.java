
import javax.swing.*;
        import java.awt.*;
        import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddCardUI {
    public static void main(String[] args) {
        // Create the main frame
        JFrame frame = new JFrame("Add New Card");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a panel for the form
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 2, 10, 10));

        // Add form elements
        JLabel cardIdLabel = new JLabel("Card ID:");
        JTextField cardIdField = new JTextField();

        JLabel nameLabel = new JLabel("Cardholder Name:");
        JTextField nameField = new JTextField();

        JLabel permissionsLabel = new JLabel("Permissions:");
        JCheckBox floor1CheckBox = new JCheckBox("Floor 1");
        JCheckBox floor2CheckBox = new JCheckBox("Floor 2");
        JCheckBox floor3CheckBox = new JCheckBox("Floor 3");

        JPanel permissionsPanel = new JPanel();
        permissionsPanel.setLayout(new GridLayout(1, 3));
        permissionsPanel.add(floor1CheckBox);
        permissionsPanel.add(floor2CheckBox);
        permissionsPanel.add(floor3CheckBox);

        JLabel encryptionLabel = new JLabel("Time-based Encryption:");
        JTextField encryptionField = new JTextField("Start-End (HH:MM-HH:MM)");

        // Add buttons
        JButton addButton = new JButton("Add Card");
        JButton resetButton = new JButton("Reset");

        // Add action listeners for buttons
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cardId = cardIdField.getText();
                String cardholderName = nameField.getText();
                String permissions = "";

                if (floor1CheckBox.isSelected()) permissions += "Floor 1, ";
                if (floor2CheckBox.isSelected()) permissions += "Floor 2, ";
                if (floor3CheckBox.isSelected()) permissions += "Floor 3, ";

                String encryption = encryptionField.getText();

                if (!cardId.isEmpty() && !cardholderName.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Card Added:\n" +
                            "ID: " + cardId + "\n" +
                            "Name: " + cardholderName + "\n" +
                            "Permissions: " + permissions + "\n" +
                            "Encryption: " + encryption);
                } else {
                    JOptionPane.showMessageDialog(frame, "Please fill out all required fields.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardIdField.setText("");
                nameField.setText("");
                floor1CheckBox.setSelected(false);
                floor2CheckBox.setSelected(false);
                floor3CheckBox.setSelected(false);
                encryptionField.setText("Start-End (HH:MM-HH:MM)");
            }
        });

        // Add elements to the panel
        panel.add(cardIdLabel);
        panel.add(cardIdField);
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(permissionsLabel);
        panel.add(permissionsPanel);
        panel.add(encryptionLabel);
        panel.add(encryptionField);
        panel.add(addButton);
        panel.add(resetButton);

        // Add panel to frame
        frame.add(panel);
        frame.setVisible(true);
    }
}
