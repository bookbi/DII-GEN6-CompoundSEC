////
////import javax.swing.*;
////        import java.awt.*;
////
////public class CardManagementPanel extends JPanel {
////    public CardManagementPanel() {
////        setLayout(new BorderLayout());
////        setBackground(new Color(220, 230, 250)); // สีพื้นหลังฟ้าอ่อน
////
////        setLayout(new BorderLayout());
////        JLabel title = new JLabel("🔑 Card Management", SwingConstants.CENTER);
////        title.setFont(new Font("Arial", Font.BOLD, 24));
////        add(title, BorderLayout.NORTH);
////        add(new JLabel("🔑 Card Management", SwingConstants.CENTER), BorderLayout.CENTER);
////
////        // กล่องเนื้อหาหลัก
////        JPanel contentPanel = new JPanel();
////        contentPanel.setBackground(new Color(220, 230, 250));
////        contentPanel.setLayout(new GridBagLayout());
////        GridBagConstraints gbc = new GridBagConstraints();
////        gbc.insets = new Insets(5, 5, 5, 5);
////        gbc.fill = GridBagConstraints.HORIZONTAL;
////        gbc.gridx = 0;
////        gbc.gridy = 0;
////
////
////        // ฟอร์มข้อมูล
////        gbc.gridy++;
////        contentPanel.add(new JLabel("Card ID :"), gbc);
////        gbc.gridx++;
////        JTextField cardIdField = new JTextField(15);
////        contentPanel.add(cardIdField, gbc);
////        gbc.gridx = 0;
////        gbc.gridy++;
////        contentPanel.add(new JLabel("Cardholder Name:"), gbc);
////        gbc.gridx++;
////        JTextField cardholderNameField = new JTextField(15);
////        contentPanel.add(cardholderNameField, gbc);
////
////        // Permissions
////        gbc.gridx = 0;
////        gbc.gridy++;
////        contentPanel.add(new JLabel("Permissions:"), gbc);
////        gbc.gridx++;
////        JPanel permissionsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
////        JButton floor1Btn = new JButton("Floor 1");
////        JButton floor2Btn = new JButton("Floor 2");
////        JButton floor3Btn = new JButton("Floor 3");
////        permissionsPanel.add(floor1Btn);
////        permissionsPanel.add(floor2Btn);
////        permissionsPanel.add(floor3Btn);
////        contentPanel.add(permissionsPanel, gbc);
////
////        // Password Fields
////        gbc.gridx = 0;
////        gbc.gridy++;
////        contentPanel.add(new JLabel("Password:"), gbc);
////        gbc.gridx++;
////        JPasswordField passwordField = new JPasswordField(15);
////        contentPanel.add(passwordField, gbc);
////
////        gbc.gridx = 0;
////        gbc.gridy++;
////        contentPanel.add(new JLabel("Confirm Password:"), gbc);
////        gbc.gridx++;
////        JPasswordField confirmPasswordField = new JPasswordField(15);
////        contentPanel.add(confirmPasswordField, gbc);
////
////        // ปุ่ม Add และ Revoke
////        gbc.gridx = 0;
////        gbc.gridy++;
////        gbc.gridwidth = 2;
////        JPanel buttonPanel = new JPanel();
////        JButton addButton = new JButton("Add");
////        JButton revokeButton = new JButton("Revoke cards");
////        addButton.setBackground(new Color(128, 0, 128));
////        addButton.setForeground(Color.WHITE);
////        revokeButton.setBackground(new Color(128, 0, 128));
////        revokeButton.setForeground(Color.WHITE);
////        buttonPanel.add(addButton);
////        buttonPanel.add(revokeButton);
////        contentPanel.add(buttonPanel, gbc);
////
////        // ไอคอนการตั้งค่า
////        gbc.gridy++;
////        gbc.anchor = GridBagConstraints.EAST;
////        JButton settingsButton = new JButton("⚙");
////        settingsButton.setBorderPainted(false);
////        settingsButton.setContentAreaFilled(false);
////        contentPanel.add(settingsButton, gbc);
////
////        add(contentPanel, BorderLayout.CENTER);
////    }
////}
//import javax.swing.*;
//import java.awt.*;
//
//public class CardManagementPanel extends JPanel {
//    public CardManagementPanel() {
//        setLayout(new GridBagLayout());
//        setBackground(new Color(220, 230, 250)); // สีพื้นหลังตาม UI
//
//        GridBagConstraints gbc = new GridBagConstraints();
//        gbc.insets = new Insets(10, 10, 10, 10);
//        gbc.anchor = GridBagConstraints.WEST;
//
//        JLabel title = new JLabel("\uD83D\uDD11 Card Management");
//        title.setFont(new Font("Arial", Font.BOLD, 26));
//        gbc.gridx = 0;
//        gbc.gridy = 0;
//        gbc.gridwidth = 2;
//        add(title, gbc);
//
//        gbc.gridwidth = 1;
//        gbc.gridy++;
//        add(new JLabel("Card ID :"), gbc);
//        gbc.gridx = 1;
//        add(new JTextField(20), gbc);
//
//        gbc.gridx = 0;
//        gbc.gridy++;
//        add(new JLabel("Cardholder Name:"), gbc);
//        gbc.gridx = 1;
//        add(new JTextField(20), gbc);
//
//        gbc.gridx = 0;
//        gbc.gridy++;
//        add(new JLabel("Permissions:"), gbc);
//
//        JPanel permissionsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
//        permissionsPanel.setBackground(new Color(220, 230, 250));
//        permissionsPanel.add(new JButton("Floor 1"));
//        permissionsPanel.add(new JButton("Floor 2"));
//        permissionsPanel.add(new JButton("Floor 3"));
//
//        gbc.gridx = 1;
//        add(permissionsPanel, gbc);
//
//        gbc.gridx = 0;
//        gbc.gridy++;
//        add(new JLabel("Password:"), gbc);
//        gbc.gridx = 1;
//        add(new JPasswordField(20), gbc);
//
//        gbc.gridx = 0;
//        gbc.gridy++;
//        add(new JLabel("Confirm Password:"), gbc);
//        gbc.gridx = 1;
//        add(new JPasswordField(20), gbc);
//
//        // ปุ่ม Add และ Revoke Cards
//        gbc.gridy++;
//        gbc.gridx = 0;
//        gbc.gridwidth = 2;
//        gbc.anchor = GridBagConstraints.CENTER;
//
//        JPanel buttonPanel = new JPanel();
//        JButton addButton = new JButton("Add");
//        JButton revokeButton = new JButton("Revoke cards");
//        addButton.setPreferredSize(new Dimension(150, 40));
//        revokeButton.setPreferredSize(new Dimension(150, 40));
//
//        buttonPanel.add(addButton);
//        buttonPanel.add(revokeButton);
//        add(buttonPanel, gbc);
//
//        // ปุ่มฟันเฟือง
//        gbc.gridy++;
//        gbc.anchor = GridBagConstraints.SOUTHEAST;
//        JButton settingsButton = new JButton("⚙");
//        settingsButton.setPreferredSize(new Dimension(40, 40));
//        add(settingsButton, gbc);
//    }
//}

// last fi
//import javax.swing.*;
//import java.awt.*;
//
//public class CardManagementPanel extends JPanel {
//    public CardManagementPanel() {
//        setLayout(new GridBagLayout());
//        setBackground(new Color(220, 230, 250)); // สีพื้นหลังตาม UI
//
//        GridBagConstraints gbc = new GridBagConstraints();
//        gbc.insets = new Insets(10, 10, 10, 10);
//        gbc.anchor = GridBagConstraints.WEST;
//
//        JLabel title = new JLabel("\uD83D\uDD11 Card Management");
//        title.setFont(new Font("Arial", Font.BOLD, 26));
//        gbc.gridx = 0;
//        gbc.gridy = 0;
//        gbc.gridwidth = 2;
//        add(title, gbc);
//
//        gbc.gridwidth = 1;
//        gbc.gridy++;
//        add(new JLabel("Card ID :"), gbc);
//        gbc.gridx = 1;
//        JTextField cardIdField = new JTextField(20);
//        add(cardIdField, gbc);
//
//        gbc.gridx = 0;
//        gbc.gridy++;
//        add(new JLabel("Cardholder Name:"), gbc);
//        gbc.gridx = 1;
//        JTextField cardholderNameField = new JTextField(20);
//        add(cardholderNameField, gbc);
//
//        gbc.gridx = 0;
//        gbc.gridy++;
//        add(new JLabel("Permissions:"), gbc);
//
//        JPanel permissionsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
//        permissionsPanel.setBackground(new Color(220, 230, 250));
//        permissionsPanel.add(new JButton("Floor 1"));
//        permissionsPanel.add(new JButton("Floor 2"));
//        permissionsPanel.add(new JButton("Floor 3"));
//
//        gbc.gridx = 1;
//        add(permissionsPanel, gbc);
//
//        // Time-Based Encryption
//        gbc.gridx = 0;
//        gbc.gridy++;
//        add(new JLabel("Start Time (HH:mm):"), gbc);
//        gbc.gridx = 1;
//        JTextField startTimeField = new JTextField(10);
//        add(startTimeField, gbc);
//
//        gbc.gridx = 0;
//        gbc.gridy++;
//        add(new JLabel("Expiry Time (HH:mm):"), gbc);
//        gbc.gridx = 1;
//        JTextField expiryTimeField = new JTextField(10);
//        add(expiryTimeField, gbc);
//
//        // Password Fields
//        gbc.gridx = 0;
//        gbc.gridy++;
//        add(new JLabel("Password:"), gbc);
//        gbc.gridx = 1;
//        add(new JPasswordField(20), gbc);
//
//        gbc.gridx = 0;
//        gbc.gridy++;
//        add(new JLabel("Confirm Password:"), gbc);
//        gbc.gridx = 1;
//        add(new JPasswordField(20), gbc);
//
//        // Buttons
//        gbc.gridy++;
//        gbc.gridx = 0;
//        gbc.gridwidth = 2;
//        gbc.anchor = GridBagConstraints.CENTER;
//
//        JPanel buttonPanel = new JPanel();
//        JButton addButton = new JButton("Add");
//        JButton revokeButton = new JButton("Revoke cards");
//        addButton.setPreferredSize(new Dimension(150, 40));
//        revokeButton.setPreferredSize(new Dimension(150, 40));
//
//        buttonPanel.add(addButton);
//        buttonPanel.add(revokeButton);
//        add(buttonPanel, gbc);
//
//        // Settings Button
//        gbc.gridy++;
//        gbc.anchor = GridBagConstraints.SOUTHEAST;
//        JButton settingsButton = new JButton("⚙");
//        settingsButton.setPreferredSize(new Dimension(40, 40));
//        add(settingsButton, gbc);
//    }
//}


// Test fi
//import javax.swing.*;
//import java.awt.*;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//public class CardManagementPanel extends JPanel {
//    private JSpinner startTimeSpinner;
//    private JSpinner expiryTimeSpinner;
//    private SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
//
//    public CardManagementPanel() {
//        setLayout(new GridBagLayout());
//        setBackground(new Color(220, 230, 250)); // สีพื้นหลังตาม UI
//
//        GridBagConstraints gbc = new GridBagConstraints();
//        gbc.insets = new Insets(10, 10, 10, 10);
//        gbc.anchor = GridBagConstraints.WEST;
//
//        JLabel title = new JLabel("\uD83D\uDD11 Card Management");
//        title.setFont(new Font("Arial", Font.BOLD, 26));
//        gbc.gridx = 0;
//        gbc.gridy = 0;
//        gbc.gridwidth = 2;
//        add(title, gbc);
//
//        gbc.gridwidth = 1;
//        gbc.gridy++;
//        add(new JLabel("Card ID :"), gbc);
//        gbc.gridx = 1;
//        add(new JTextField(20), gbc);
//
//        gbc.gridx = 0;
//        gbc.gridy++;
//        add(new JLabel("Cardholder Name:"), gbc);
//        gbc.gridx = 1;
//        add(new JTextField(20), gbc);
//
//        gbc.gridx = 0;
//        gbc.gridy++;
//        add(new JLabel("Permissions:"), gbc);
//
//        JPanel permissionsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
//        permissionsPanel.setBackground(new Color(220, 230, 250));
//        permissionsPanel.add(new JButton("Floor 1"));
//        permissionsPanel.add(new JButton("Floor 2"));
//        permissionsPanel.add(new JButton("Floor 3"));
//
//        gbc.gridx = 1;
//        add(permissionsPanel, gbc);
//
//        gbc.gridx = 0;
//        gbc.gridy++;
//        add(new JLabel("Start Time:"), gbc);
//        gbc.gridx = 1;
//        startTimeSpinner = new JSpinner(new SpinnerDateModel());
//        JSpinner.DateEditor startEditor = new JSpinner.DateEditor(startTimeSpinner, "HH:mm");
//        startTimeSpinner.setEditor(startEditor);
//        add(startTimeSpinner, gbc);
//
//        gbc.gridx = 0;
//        gbc.gridy++;
//        add(new JLabel("Expiry Time:"), gbc);
//        gbc.gridx = 1;
//        expiryTimeSpinner = new JSpinner(new SpinnerDateModel());
//        JSpinner.DateEditor expiryEditor = new JSpinner.DateEditor(expiryTimeSpinner, "HH:mm");
//        expiryTimeSpinner.setEditor(expiryEditor);
//        add(expiryTimeSpinner, gbc);
//
//        gbc.gridy++;
//        gbc.gridx = 0;
//        gbc.gridwidth = 2;
//        gbc.anchor = GridBagConstraints.CENTER;
//
//        JPanel buttonPanel = new JPanel();
//        JButton addButton = new JButton("Add");
//        JButton revokeButton = new JButton("Revoke cards");
//        JButton validateButton = new JButton("Validate Time");
//
//        addButton.setPreferredSize(new Dimension(120, 40));
//        revokeButton.setPreferredSize(new Dimension(150, 40));
//        validateButton.setPreferredSize(new Dimension(150, 40));
//
//        buttonPanel.add(addButton);
//        buttonPanel.add(revokeButton);
//        buttonPanel.add(validateButton);
//        add(buttonPanel, gbc);
//
//        // ฟังก์ชันตรวจสอบเวลา
//        validateButton.addActionListener(e -> validateTime());
//    }
//
//    private void validateTime() {
//        Date startTime = (Date) startTimeSpinner.getValue();
//        Date expiryTime = (Date) expiryTimeSpinner.getValue();
//
//        if (expiryTime.after(startTime)) {
//            JOptionPane.showMessageDialog(this, "✅ Time is valid!", "Validation", JOptionPane.INFORMATION_MESSAGE);
//        } else {
//            JOptionPane.showMessageDialog(this, "❌ Expiry Time must be later than Start Time!", "Error", JOptionPane.ERROR_MESSAGE);
//        }
//    }
//}

// FINAL 1
//import javax.swing.*;
//import java.awt.*;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//public class CardManagementPanel extends JPanel {
//    private JSpinner startTimeSpinner;
//    private JSpinner expiryTimeSpinner;
//    private SimpleDateFormat dateTimeFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
//
//    public CardManagementPanel() {
//        setLayout(new GridBagLayout());
//        setBackground(new Color(220, 230, 250)); // สีพื้นหลังตาม UI
//
//        GridBagConstraints gbc = new GridBagConstraints();
//        gbc.insets = new Insets(10, 10, 10, 10);
//        gbc.anchor = GridBagConstraints.WEST;
//
//        JLabel title = new JLabel("\uD83D\uDD11 Card Management");
//        title.setFont(new Font("Arial", Font.BOLD, 26));
//        gbc.gridx = 0;
//        gbc.gridy = 0;
//        gbc.gridwidth = 2;
//        add(title, gbc);
//
//        gbc.gridwidth = 1;
//        gbc.gridy++;
//        add(new JLabel("Card ID :"), gbc);
//        gbc.gridx = 1;
//        add(new JTextField(20), gbc);
//
//        gbc.gridx = 0;
//        gbc.gridy++;
//        add(new JLabel("Cardholder Name:"), gbc);
//        gbc.gridx = 1;
//        add(new JTextField(20), gbc);
//
//        gbc.gridx = 0;
//        gbc.gridy++;
//        add(new JLabel("Permissions:"), gbc);
//
//        JPanel permissionsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
//        permissionsPanel.setBackground(new Color(220, 230, 250));
//        permissionsPanel.add(new JButton("Floor 1"));
//        permissionsPanel.add(new JButton("Floor 2"));
//        permissionsPanel.add(new JButton("Floor 3"));
//
//        gbc.gridx = 1;
//        add(permissionsPanel, gbc);
//
//        // Start Time Picker
//        gbc.gridx = 0;
//        gbc.gridy++;
//        add(new JLabel("Start Time (DD-MM-YYYY HH:mm):"), gbc);
//        gbc.gridx = 1;
//        startTimeSpinner = new JSpinner(new SpinnerDateModel());
//        JSpinner.DateEditor startEditor = new JSpinner.DateEditor(startTimeSpinner, "dd-MM-yyyy HH:mm");
//        startTimeSpinner.setEditor(startEditor);
//        add(startTimeSpinner, gbc);
//
//        // Expiry Time Picker
//        gbc.gridx = 0;
//        gbc.gridy++;
//        add(new JLabel("Expiry Time (DD-MM-YYYY HH:mm):"), gbc);
//        gbc.gridx = 1;
//        expiryTimeSpinner = new JSpinner(new SpinnerDateModel());
//        JSpinner.DateEditor expiryEditor = new JSpinner.DateEditor(expiryTimeSpinner, "dd-MM-yyyy HH:mm");
//        expiryTimeSpinner.setEditor(expiryEditor);
//        add(expiryTimeSpinner, gbc);
//
//        // Buttons Panel
//        gbc.gridy++;
//        gbc.gridx = 0;
//        gbc.gridwidth = 2;
//        gbc.anchor = GridBagConstraints.CENTER;
//
//        JPanel buttonPanel = new JPanel();
//        JButton addButton = new JButton("Add");
//        JButton revokeButton = new JButton("Revoke cards");
//        JButton validateButton = new JButton("Validate Time");
//
//        addButton.setPreferredSize(new Dimension(120, 40));
//        revokeButton.setPreferredSize(new Dimension(150, 40));
//        validateButton.setPreferredSize(new Dimension(150, 40));
//
//        buttonPanel.add(addButton);
//        buttonPanel.add(revokeButton);
//        buttonPanel.add(validateButton);
//        add(buttonPanel, gbc);
//
//        // ฟังก์ชันตรวจสอบเวลา
//        validateButton.addActionListener(e -> validateTime());
//    }
//
//    private void validateTime() {
//        Date startTime = (Date) startTimeSpinner.getValue();
//        Date expiryTime = (Date) expiryTimeSpinner.getValue();
//
//        if (expiryTime.after(startTime)) {
//            JOptionPane.showMessageDialog(this, "✅ Time is valid!", "Validation", JOptionPane.INFORMATION_MESSAGE);
//        } else {
//            JOptionPane.showMessageDialog(this, "❌ Expiry Time must be later than Start Time!", "Error", JOptionPane.ERROR_MESSAGE);
//        }
//    }
//}

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CardManagementPanel extends JPanel {
    private JSpinner startTimeSpinner;
    private JSpinner expiryTimeSpinner;
    private JTextField cardIdField;
    private JTextField cardholderNameField;
    private List<String> selectedRooms = new ArrayList<>();
    private SimpleDateFormat dateTimeFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");

    public CardManagementPanel() {
        setLayout(new GridBagLayout());
        setBackground(new Color(220, 230, 250));
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
        cardIdField = new JTextField(20);
        add(cardIdField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Cardholder Name:"), gbc);
        gbc.gridx = 1;
        cardholderNameField = new JTextField(20);
        add(cardholderNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Permissions:"), gbc);

        JPanel permissionsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        permissionsPanel.setBackground(new Color(220, 230, 250));

        JButton floor1Btn = new JButton("Floor 1");
        JButton floor2Btn = new JButton("Floor 2");
        JButton floor3Btn = new JButton("Floor 3");

        permissionsPanel.add(floor1Btn);
        permissionsPanel.add(floor2Btn);
        permissionsPanel.add(floor3Btn);

        gbc.gridx = 1;
        add(permissionsPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Start Time:"), gbc);
        gbc.gridx = 1;
        startTimeSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor startEditor = new JSpinner.DateEditor(startTimeSpinner, "dd-MM-yyyy HH:mm");
        startTimeSpinner.setEditor(startEditor);
        add(startTimeSpinner, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Expiry Time:"), gbc);
        gbc.gridx = 1;
        expiryTimeSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor expiryEditor = new JSpinner.DateEditor(expiryTimeSpinner, "dd-MM-yyyy HH:mm");
        expiryTimeSpinner.setEditor(expiryEditor);
        add(expiryTimeSpinner, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;

        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add");
        JButton validateButton = new JButton("Validate Time");

        buttonPanel.add(addButton);
        buttonPanel.add(validateButton);
        add(buttonPanel, gbc);

        validateButton.addActionListener(e -> validateTime());
        addButton.addActionListener(e -> saveData());

        floor1Btn.addActionListener(e -> openRoomSelection(1));
        floor2Btn.addActionListener(e -> openRoomSelection(2));
        floor3Btn.addActionListener(e -> openRoomSelection(3));
    }

    private void validateTime() {
        Date startTime = (Date) startTimeSpinner.getValue();
        Date expiryTime = (Date) expiryTimeSpinner.getValue();

        if (expiryTime.after(startTime)) {
            JOptionPane.showMessageDialog(this, "✅ Time is valid!", "Validation", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "❌ Expiry Time must be later than Start Time!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void openRoomSelection(int floor) {
        JFrame roomFrame = new JFrame("Select Rooms - Floor " + floor);
        roomFrame.setSize(400, 300);
        roomFrame.setLayout(new GridLayout(3, 4, 10, 10));
        roomFrame.getContentPane().setBackground(new Color(220, 230, 250));

        int startRoom = floor * 100 + 1;
        for (int i = 0; i < 12; i++) {
            int roomNumber = startRoom + i;
            JButton roomButton = new JButton("Room " + roomNumber);
            roomButton.setBackground(new Color(255, 230, 250));
            roomButton.addActionListener(new RoomSelectionHandler(roomNumber));
            roomFrame.add(roomButton);
        }

        roomFrame.setVisible(true);
    }

    private class RoomSelectionHandler implements ActionListener {
        private int roomNumber;

        public RoomSelectionHandler(int roomNumber) {
            this.roomNumber = roomNumber;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            selectedRooms.add("Room " + roomNumber);
            JOptionPane.showMessageDialog(null, "Added: Room " + roomNumber);
        }
    }

    private void saveData() {
        String cardId = cardIdField.getText();
        String cardholderName = cardholderNameField.getText();
        Date startTime = (Date) startTimeSpinner.getValue();
        Date expiryTime = (Date) expiryTimeSpinner.getValue();

        JOptionPane.showMessageDialog(this, "Data Saved:\nCard ID: " + cardId + "\nCardholder: " + cardholderName + "\nRooms: " + selectedRooms);
    }
}
